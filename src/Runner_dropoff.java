import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.joda.time.DateTimeZone;


public class Runner_dropoff {
	static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

	static long non_14 = 0;
	static long non_dist = 0;


	public static void main(String[] args) throws IOException
	{
		PrintStream out = new PrintStream(new FileOutputStream("F:/LaGDropoff_v6.csv"));
		System.setOut(out);
		DateTimeZone.setDefault(DateTimeZone.forID("EST") );
		PrintWriter skipped_writer = new PrintWriter("F:/LaGDropoff_skipped_v5.csv");

		for(File idx_file:Constants.csv_files)
		{
			Runner.LOGGER.info("Processing"+idx_file.toString());
			long ctr = 0;
			BufferedReader file_reader = new BufferedReader(new FileReader(idx_file));
			String file_line = file_reader.readLine();
			while((file_line=file_reader.readLine())!=null 
					&& file_line.length()!=0)
			{
				ctr++;
				String[] file_line_split = file_line.split(",");

				if(file_line_split.length!=14)
				{
					non_14++;
					skipped_writer.println(file_line);
					continue;
				}

				boolean isMedallion = TaxiTrip.isMedallion(file_line_split[0]);
				boolean isWeekend = TaxiTrip.isWeekday(file_line_split[5]);

				boolean check1 = isMedallion && isWeekend;

				if(check1)
				{
					double longitudeP = Double.parseDouble(file_line_split[10]);
					double latitudeP = Double.parseDouble(file_line_split[11]);
					double longitudeD = Double.parseDouble(file_line_split[12]);
					double latitudeD = Double.parseDouble(file_line_split[13]);

					boolean isLongitudeP = TaxiTrip.isLongitude(longitudeP);
					boolean isLatitudeP = TaxiTrip.isLatitude(latitudeP);
					boolean isLongitudeD = TaxiTrip.isLongitude(longitudeD);
					boolean isLatitudeD = TaxiTrip.isLatitude(latitudeD);

					boolean check2 = (isLongitudeP && isLatitudeP) && (isLongitudeD && isLatitudeD) ;
					if(check2)
					{
						double travel_dist = FilterFunctions.distFrom(latitudeP,
								longitudeP, latitudeD, longitudeD );
						if(travel_dist < 0.1)
						{
							//Runner.LOGGER.info(ctr+"trip_distance < 0.1 miles, Skipped!");
							non_dist++;
							skipped_writer.println(file_line);
							continue;
						}
						else
						{
							if(FilterFunctions.inLaG(latitudeD,longitudeD))
							{
								System.out.println(file_line);
							}
						}

					}
					else
					{
						//Runner.LOGGER.info(ctr+" Co-ordinates are zero, Skipped!");
						skipped_writer.println(file_line);
						continue;
					}
				}
				else
				{
					continue;
				}

			}

			file_reader.close();
		}
		Runner.LOGGER.info(non_14+" Number of No 14 lines Skipped!");
		Runner.LOGGER.info(non_dist+" Number of Non distance lines Skipped!");
	}

}
