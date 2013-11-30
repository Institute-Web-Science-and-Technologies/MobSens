package mobsens.collector;

import java.util.Date;

import mobsens.collector.communications.ConnectedService;
import mobsens.collector.consumers.WFJStreamingConsumer;
import mobsens.collector.drivers.annotations.AnnotationDriver;
import mobsens.collector.drivers.connectivity.ConnectivityDriver;
import mobsens.collector.drivers.locations.LocationDriver;
import mobsens.collector.drivers.messaging.QuitCollectorDriver;
import mobsens.collector.drivers.messaging.QuitCollectorOutput;
import mobsens.collector.drivers.messaging.StartCollectorDriver;
import mobsens.collector.drivers.messaging.StartCollectorOutput;
import mobsens.collector.drivers.messaging.StopCollectorDriver;
import mobsens.collector.drivers.messaging.StopCollectorOutput;
import mobsens.collector.drivers.sensors.SensorDriver;
import mobsens.collector.intents.IntentCollectorStatus;
import mobsens.collector.pipeline.Consumer;
import mobsens.collector.pipeline.basics.Filter;
import mobsens.collector.util.Logging;
import mobsens.collector.wfj.WFJ;
import mobsens.collector.wfj.basics.BasicWFJ;

import org.json.JSONException;
import org.json.JSONStringer;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings.Secure;

public class Collector extends ConnectedService
{
	private final int sid = Float.floatToIntBits((float) Math.random()) % 10000;

	private String did;

	public final CollectorIPC IPC_ENDPOINT = new CollectorIPC.Stub()
	{
		@Override
		public boolean isCollecting() throws RemoteException
		{
			return collecting;
		}
	};

	public final Consumer<StartCollectorOutput> START_COLLECTOR_ENDPOINT = new Consumer<StartCollectorOutput>()
	{
		@Override
		public void consume(final StartCollectorOutput item)
		{
			collecting = true;
			IntentCollectorStatus.sendBroadcast(Collector.this, true);

			startTime = new Date();
			title = item.title;

			Logging.log(Collector.this, "Collector:" + sid, "Starting collection", null);

			wfjStreamer.setLocation("wfj/" + item.title + new Date().getTime() + ".wfj");

			for (SensorDriver sensorDriver : sensorDrivers)
			{
				sensorDriver.start();
			}

			locationDriver.start();

			connectivityDriver.start();

			annotationDriver.start();

		}
	};

	public final Consumer<StopCollectorOutput> STOP_COLLECTOR_ENDPOINT = new Consumer<StopCollectorOutput>()
	{
		@Override
		public void consume(StopCollectorOutput item)
		{
			collecting = false;
			IntentCollectorStatus.sendBroadcast(Collector.this, false);

			final Date endTime = new Date();

			Logging.log(Collector.this, new Date(), "Collector", "Stopping collection", null);

			// Rec-Token schreiben
			wfjStreamer.consume(new BasicWFJ()
			{
				@Override
				public void generateTo(JSONStringer stringer) throws JSONException
				{
					stringer.object().key("rec").object().key("title").value(title).key("did").value(did).key("time_start").value(startTime.getTime()).key("time_stop")
							.value(endTime.getTime()).endObject().endObject();
				}
			});

			for (SensorDriver sensorDriver : sensorDrivers)
			{
				sensorDriver.stop();
			}

			locationDriver.stop();

			connectivityDriver.stop();

			annotationDriver.stop();
		}
	};

	public final Consumer<QuitCollectorOutput> QUIT_COLLECTOR_ENDPOINT = new Consumer<QuitCollectorOutput>()
	{
		@Override
		public void consume(QuitCollectorOutput item)
		{
			Logging.log(Collector.this, "Collector:" + sid, "Qutting", null);

			stopSelf();
		}
	};

	private final StartCollectorDriver startCollectorDriver;

	private final StopCollectorDriver stopCollectorDriver;

	private final QuitCollectorDriver quitCollectorDriver;

	private final SensorDriver[] sensorDrivers;

	private final LocationDriver locationDriver;

	private final ConnectivityDriver connectivityDriver;

	private final AnnotationDriver annotationDriver;

	private final WFJStreamingConsumer wfjStreamer;

	private final Filter<WFJ> wfjFilter;

	private boolean collecting;

	private Date startTime;

	private String title;

	public Collector()
	{
		startCollectorDriver = new StartCollectorDriver(this);
		startCollectorDriver.setConsumer(START_COLLECTOR_ENDPOINT);

		stopCollectorDriver = new StopCollectorDriver(this);
		stopCollectorDriver.setConsumer(STOP_COLLECTOR_ENDPOINT);

		quitCollectorDriver = new QuitCollectorDriver(this);
		quitCollectorDriver.setConsumer(QUIT_COLLECTOR_ENDPOINT);

		sensorDrivers = new SensorDriver[] { new SensorDriver(this, Sensor.TYPE_ACCELEROMETER, 1000 / 50), new SensorDriver(this, Sensor.TYPE_GYROSCOPE, 1000 / 50),
				new SensorDriver(this, Sensor.TYPE_MAGNETIC_FIELD, 1000 / 50), new SensorDriver(this, Sensor.TYPE_LINEAR_ACCELERATION, 1000 / 50),
				new SensorDriver(this, Sensor.TYPE_GRAVITY, 1000 / 50) };

		locationDriver = new LocationDriver(this, 500, 0, true, true, true);

		connectivityDriver = new ConnectivityDriver(this);

		annotationDriver = new AnnotationDriver(this);

		wfjStreamer = new WFJStreamingConsumer(this);

		wfjFilter = new Filter<WFJ>(WFJ.class);
		wfjFilter.setConsumer(wfjStreamer);

		for (SensorDriver sensorDriver : sensorDrivers)
		{
			sensorDriver.setConsumer(wfjFilter);
		}

		locationDriver.setConsumer(wfjFilter);

		connectivityDriver.setConsumer(wfjFilter);

		annotationDriver.setConsumer(wfjFilter);

		collecting = false;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		did = "DEV" + Integer.toHexString((Secure.getString(getContentResolver(), Secure.ANDROID_ID)).hashCode());

		startCollectorDriver.start();
		stopCollectorDriver.start();
		quitCollectorDriver.start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		startCollectorDriver.stop();
		stopCollectorDriver.stop();
		quitCollectorDriver.stop();

		super.onDestroy();
	}

	@Override
	protected void onConnected()
	{
		Logging.log(this, "Collector:" + sid, "Connected", null);
	}

	@Override
	protected void onDisconnected()
	{
		Logging.log(this, "Collector:" + sid, "Disconnected", null);
	}

	@Override
	protected IBinder getBinder()
	{
		return IPC_ENDPOINT.asBinder();
	}
}
