package ecnu.ica.wordsearch.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月23日下午2:36:09
 *@Description : TODO
 */
public class TheCrawlerUtil {
	private final static Logger LOGGER =Logger.getLogger(TheCrawlerUtil.class);

	/**
	 * get system current time 
	 * @return time
	 */
	public static String GetCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		return time;
	}
	
	/**
	 * log in error or warning informations into logger
	 * @param e
	 * @return error
	 */
	public static String LogErrorInfo(Exception e)
	{
		String error = "";
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		error = writer.toString();
		return error;
	}
	public static String ToStopChar(String word)
	{
		if(word.contains("/"))
		{
			word = word.replaceAll("/", "");
		}
		if(word.contains("&"))
		{
			word = word.replaceAll("&", "");
		}
		return word;
	}
	
	
	/*public static void main(String[] args) {
		try 
		{
			WebClient webClient = WebConstructor.ConstructWebClient();
			HtmlPage page = webClient.getPage("jfladfaldff");
		} catch (Exception e) {
			System.out.println("oh");
			LOGGER.error(TheCrawlerUtil.LogErrorInfo(e));
		}
	}*/
}
