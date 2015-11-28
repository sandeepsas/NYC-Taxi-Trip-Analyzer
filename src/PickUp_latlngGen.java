import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class PickUp_latlngGen {
	
	public static final File data_file = new File("F:/LaGDropoff_v5.csv");
	
	public static void main(String[] args) throws IOException
	{
		PrintStream out = new PrintStream(new FileOutputStream("F:/LaGDropoff_v5.kml"));
		System.setOut(out);
		
		String file_line = new String();
		BufferedReader file_reader = new BufferedReader(new FileReader(data_file));
		KML_gen.kml_header();
		while((file_line=file_reader.readLine())!=null 
				&& file_line.length()!=0)
		{
			String[] file_line_split = file_line.split(",");
			KML_gen.place_mark(file_line_split[12], file_line_split[13]);
		}
		KML_gen.kml_footer();
	}
	


}
