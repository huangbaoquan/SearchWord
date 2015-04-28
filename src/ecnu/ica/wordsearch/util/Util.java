package ecnu.ica.wordsearch.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public Util() {
	}
	
	public static String GetCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		return time;
	}
}
