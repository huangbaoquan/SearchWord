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
	public static String GetTimeSpan(long startTime,long endTime)
	{
		long l = endTime - startTime;
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");  
		return (""+day+"天"+hour+"小时"+min+"分"+s+"秒");
	}
	
	/**
	 * log in error or warning informations into logger
	 * @param e
	 * @return error
	 */
	public static String LogErrorInfo(Exception e)
	{
		try 
		{
			String error = "";
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			error = writer.toString();
			return error;
		} catch (Exception e1) {
			LOGGER.error(e1.toString());
			e1.printStackTrace();
		}
		return null;
	}
	public static String ToStopChar(String word)
	{
		String string = word;
		if(string.contains("/"))
		{
			string = string.replaceAll("/", "");
		}
		if(string.contains("&"))
		{
			string = string.replaceAll("&", "");
		}
		if(string.contains(":"))
		{
			string = string.replaceAll(":", "");
		}
		if(string.contains("-"))
		{
			string = string.replaceAll("-", "");
		}
		return string;
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
