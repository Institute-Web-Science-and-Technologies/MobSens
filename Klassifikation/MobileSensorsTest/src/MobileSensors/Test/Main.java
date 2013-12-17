package MobileSensors.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

import com.sun.jersey.api.client.Client;

import MobileSensors.Calculation.GPS;
import MobileSensors.Calculation.LocationCalc;
import MobileSensors.Classifiers.DetectBreaking;
import MobileSensors.Classifiers.DetectJerk;
import MobileSensors.Classifiers.DetectStanding;
import MobileSensors.Storage.Event.Event;
import MobileSensors.Storage.Sensors.Accelerometer;
import MobileSensors.Storage.Sensors.Annotation;
import MobileSensors.Storage.Sensors.Location;
import MobileSensors.Storage.Sensors.Sensor.Sensor;
import MobileSensors.Test.Data.Recording;
import MobileSensors.Test.Data.SensorE;
import MobileSensors.Test.Data.URLS;
import MobileSensors.Test.Input.CSV;
import MobileSensors.Test.Input.RESTful;
import MobileSensors.Test.Output.Chart;

public class Main {

	private static final String username = "mlukas@gmx.net";
	private static final String password = "12345678";

	public static void main(String[] args) throws IOException {

		// Auf Server einloggen
		Client client = RESTful.login(URLS.LOGIN.getURL(), username, password);
		ArrayList<Recording> recordings = RESTful.recordingOutput(client,
				URLS.LIST_RECORDINGS.getURL());

		Chart.drawAllRecordings(recordings, username, password);

		System.out.println("done");

	}

	public static void printCalcData(Collection<Location> locations) {
		for (Location location : locations) {
			System.out.println(location.getJerkCalc() + "jerk vs jerkfusion "
					+ location.getJerkFusion());
		}

		for (Location location : locations) {
			System.out.println(location.getSpeed() + " Sensor vs Calc "
					+ location.getSpeedCalcCo());
		}

		for (Location location : locations) {
			System.out
					.println(location.getDistanceCalcCo()
							+ " SensorDist vs CalcDist "
							+ location.getDistanceFusion());
		}

		for (Location location : locations) {
			System.out.println(location.getDistanceSumCalcCo()
					+ " SensorDistSum vs CalcDistSum "
					+ location.getDistanceFusionSum());
		}
	}

	public static void printStartStop(ArrayList<Location> locations) {
		System.out.println("start: "
				+ DateFormatUtils.format(new Date(locations.get(0).getTime()),
						"MM-dd HH:mm:ss"));
		System.out
				.println("stop: "
						+ DateFormatUtils.format(
								new Date(locations.get(locations.size() - 1)
										.getTime()), "HH:mm:ss"));
	}
}
// ALTER CODE

// Die CSV-Dateien zu ArrayLists

// ArrayList<Location> locations = CSV.csvToLocation(new
// File("/Users/henny/Downloads/locspazieren.csv"));
