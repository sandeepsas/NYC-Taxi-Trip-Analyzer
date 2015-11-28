
public class KML_gen {
	
	public static void kml_header()
	{
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
        System.out.println("<Document>");
	}
	public static void kml_footer()
	{
		System.out.println("</Document>");
        System.out.println("</kml>");
	}
	public static void place_mark(String longitude, String  latitude)
	{
		System.out.println("<Placemark>");
		System.out.println("<Point>");
        System.out.println("<coordinates>");
        System.out.println(longitude+","+latitude);
        System.out.println("</coordinates>");
        System.out.println("</Point>");
		System.out.println("</Placemark>");
	}

}
