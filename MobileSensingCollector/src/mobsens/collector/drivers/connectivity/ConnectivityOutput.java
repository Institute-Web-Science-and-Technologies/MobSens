package mobsens.collector.drivers.connectivity;

import java.util.Date;

import android.net.NetworkInfo.State;

public class ConnectivityOutput
{
	/**
	 * Zeit
	 */
	public final Date time;

	/**
	 * Typ des Netzes
	 */
	public final int type;

	/**
	 * Untertyp des Netzes
	 */
	public final int subtype;

	/**
	 * Status
	 */
	public final State state;

	public ConnectivityOutput(Date time, int type, int subtype, State state)
	{
		this.time = time;
		this.type = type;
		this.subtype = subtype;
		this.state = state;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + subtype;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ConnectivityOutput other = (ConnectivityOutput) obj;
		if (state != other.state) return false;
		if (subtype != other.subtype) return false;
		if (time == null)
		{
			if (other.time != null) return false;
		}
		else if (!time.equals(other.time)) return false;
		if (type != other.type) return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "ConnectivityOutput [time=" + time + ", type=" + type + ", subtype=" + subtype + ", state=" + state + "]";
	}

}
