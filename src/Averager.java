import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Averager {

	public static int compareTimes(String t1, String t2)
	{
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int rt=0;

		try {

			rt = f.parse(t1).compareTo(f.parse(t2));
		} 
		catch (ParseException e) 
		{
			throw new IllegalArgumentException(e);
		}

		return rt;

	}

	public static void main(String[] args) throws IOException, ParseException
	{
		//PrintStream out = new PrintStream(new FileOutputStream("dropoffs_LaG_avg.csv"));
		//System.setOut(out);
		File csv_files = new File("F:/Processed/LaGPickUps_v5.csv");
		BufferedReader file_reader = new BufferedReader(new FileReader(csv_files));
		String file_lines = new String();
		DateTimeZone.setDefault(DateTimeZone.forID("EST") );
		DateTimeFormatter time_formatter = DateTimeFormat.forPattern("HH:mm:ss");
		
		DateTime start_time = time_formatter.parseDateTime("00:00:01");
		DateTime end_time = time_formatter.parseDateTime("23:59:59");

		int[] sum_arr = new int[24];
		
		while((file_lines=file_reader.readLine())!=null 
				&& file_lines.length()!=0)
		{
			String[] next_line_split = file_lines.split(",");
			DateTime pickup_date1 = Constants.dt_formatter.parseDateTime(next_line_split[5]);
			String pickup_date_s = time_formatter.print(pickup_date1);
			DateTime pickup_date = time_formatter.parseDateTime(pickup_date_s);
			DateTime drop_off_date1 = Constants.dt_formatter.parseDateTime(next_line_split[6]);
			String drop_off_date_s = time_formatter.print(drop_off_date1);
			DateTime drop_off_date = time_formatter.parseDateTime(drop_off_date_s);
			int ctr =0;
			
			DateTime dt_idx = start_time; 
			
			while(dt_idx.compareTo(end_time)<0)
			{
				DateTime dt_idx_interval = dt_idx.plusHours(1);
				
				if( (pickup_date.compareTo(dt_idx)>0)&&
						(pickup_date.compareTo(dt_idx_interval)<0) )
				{
					int time = pickup_date.getHourOfDay();
					sum_arr[time] +=1;
				}
				ctr++;
				dt_idx = dt_idx.plusHours(1);
			}
		}
		for (int j=0;j<sum_arr.length;j++)
			System.out.println(sum_arr[j]);
	}

}
