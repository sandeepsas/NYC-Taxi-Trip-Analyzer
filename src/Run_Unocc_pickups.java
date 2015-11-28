import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.joda.time.DateTime;


public class Run_Unocc_pickups {
	
	public static void main(String[] args) throws IOException
	{
		//PrintWriter writer = new PrintWriter("F:/Occupied_inB.csv");
		PrintStream out = new PrintStream(new FileOutputStream("F:/LaGUnOcc_inB_v5_2.csv"));
		System.setOut(out);
		//Input file of pickups
		File PU_file = new File("F:/LaGPickUps_v5.csv");
		File DR_file = new File("F:/LaGDropoff_v5.csv");
		BufferedReader PU_file_reader = new BufferedReader(new FileReader(PU_file));
		String PU_file_line = new String();
		String DR_file_line = new String();
		while((PU_file_line=PU_file_reader.readLine())!=null 
				&& PU_file_line.length()!=0)
		{
			String[] PU_line_split = PU_file_line.split(",");
			String PU_medallion = PU_line_split[0];
			DateTime PU_time = Constants.dt_formatter.parseDateTime(PU_line_split[5]);
			String PU_date = Constants.date_formatter.print(PU_time);
			//Runner.LOGGER.info("Pick up Date =  "+PU_date+"\n");
		
			//Check if this medallion has a drop-off on the same day
			int check_flag = 0;
			BufferedReader DR_file_reader = new BufferedReader(new FileReader(DR_file));
			while((DR_file_line=DR_file_reader.readLine())!=null 
					&& DR_file_line.length()!=0)
			{
				String[] DR_line_split = DR_file_line.split(",");
				String DR_medallion = DR_line_split[0];
				DateTime DR_time = Constants.dt_formatter.parseDateTime(DR_line_split[6]);
				String DR_date = Constants.date_formatter.print(DR_time);
				//Runner.LOGGER.info("Pick up Date =  "+PU_line_split[5]+"\t Drop-off Dates Date =  "+DR_line_split[6]);
				
				if(DR_time.compareTo(PU_time)>0)
				{
					break;
				}
				//
				if(DR_date.equals(PU_date))
				{
					//if(DR_medallion.equals(PU_medallion))
					//if((DR_time.compareTo(PU_time)<0))
					if((DR_time.compareTo(PU_time)<0)&&
							(DR_medallion.equals(PU_medallion)))
					{
						check_flag = 1;
						//writer.println(DR_file_line);
						//writer.println(PU_file_line);
						System.out.println("Match Found");
						System.out.println(DR_file_line+"\n"+PU_file_line+"\n");
						//writer.println("\n");
						break;
					}
					
				}
				else
				{
					continue;
				}
			}
			//if(check_flag==0)
				//System.out.println(PU_file_line);//Un-occupied pick up
		}
		//writer.close();
	}
	


}


