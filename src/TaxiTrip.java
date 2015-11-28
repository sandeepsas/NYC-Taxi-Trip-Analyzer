import org.joda.time.DateTime;


public class TaxiTrip {

	String medallion;
	String pickup_datetime;
	String dropoff_datetime;
	int passenger_count;
	double trip_time_in_secs;
	double trip_distance;
	double pickup_longitude;
	double pickup_latitude;
	double dropoff_longitude;
	double dropoff_latitude;

	public TaxiTrip(String medallion,
			String pickup_datetime,
			String dropoff_datetime,
			int passenger_count,
			double trip_time_in_secs,
			double trip_distance,
			double pickup_longitude,
			double pickup_latitude,
			double dropoff_longitude,
			double dropoff_latitude)
	{
		this.medallion =  medallion;
		this.pickup_datetime =   pickup_datetime;
		this.dropoff_datetime =   dropoff_datetime;
		this.passenger_count =   passenger_count;
		this.trip_time_in_secs =   trip_time_in_secs;
		this.trip_distance =   trip_distance;
		this.pickup_longitude =   pickup_longitude;
		this.pickup_latitude =   pickup_latitude;
		this.dropoff_longitude =   dropoff_longitude;
		this.dropoff_latitude =   dropoff_latitude;
	}
/*For data integrity*/
	
	public static boolean isMedallion(String medallion)
	{
		if(medallion.length()!=10)
		{
			Runner.LOGGER.warning("Medellion String Length Exception");
			return false;
		}
		return true;
	}
	public static boolean isLatitude(double latitude)
	{
		if(latitude == 0)
		{
			//Runner.LOGGER.info("Latitude = 0, Skipped!");
			return false;
		}
		return true;
	}
	public static boolean isLongitude(double longitude)
	{
		if(longitude == 0)
		{
			//Runner.LOGGER.info("Longitude = 0, Skipped!");
			return false;
		}
		return true;
	}
	public static boolean isTripTime(double trip_time_in_secs)
	{
		if(trip_time_in_secs == 0)
		{
			//Runner.LOGGER.info("trip_time_in_secs = 0, Check and Skip!");
			return false;
		}
		if(trip_time_in_secs < 10)
		{
			//Runner.LOGGER.info("trip_time_in_secs"+trip_time_in_secs+", Check and Skip!");
			return false;
		}
		return true;
	}
	
	public static boolean isTripDistance(double trip_distance)
	{
		if(trip_distance == 0)
		{
			//Runner.LOGGER.info("trip_distance = 0, Check and Skip!");
			return false;
		}
		return true;
	}
	
	public static boolean isWeekday(String dateTime)
	{
		DateTime f_date = Constants.dt_formatter.parseDateTime(dateTime);
		int dayOfWeek = f_date.getDayOfWeek();
		
		if(dayOfWeek>5)
			return false;
		
		return true;
	}


}

/*TaxiTrip taxiTrip = new TaxiTrip(file_line_split[0],
		file_line_split[1],
		file_line_split[2],
		Integer.parseInt(file_line_split[3]),
		Double.parseDouble(file_line_split[4]),
		Double.parseDouble(file_line_split[5]),
		Double.parseDouble(file_line_split[6]),
		Double.parseDouble(file_line_split[7]),
		Double.parseDouble(file_line_split[8]),
		Double.parseDouble(file_line_split[9]));*/