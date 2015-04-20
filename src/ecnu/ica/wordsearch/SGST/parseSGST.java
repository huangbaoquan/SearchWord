package ecnu.ica.wordsearch.SGST;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.parse.parse;
import ecnu.ica.wordsearch.util.SingletonWebClient;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月8日上午10:02:41
 *@Description : TODO
 */
public class parseSGST implements parse{
	final private static Logger LOGGER = Logger.getLogger(parseSGST.class);   
	public parseSGST() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * parse SGST of page into HTML
	 */
	@Override
	public String Imparse(String url) 
	{
		try 
		{
			//WebClient webClient = WebConstructor.ConstructWebClient();
			WebClient webClient = SingletonWebClient.getInstance().webClient;
			HtmlPage page = webClient.getPage(url);
			if(page != null)
			{
				webClient.closeAllWindows();
//				System.out.println(page.asText());
				return page.asXml();
			}
		} catch(Exception e){
			LOGGER.error(url);
		}
		return null;
	}
	/** 
	* @Title       : parseSGST
	* @Description : Set Wait Time 
	* @return      : @param url
	* @return      : @param waitTime
	* @return      : @return Page's HTML
	*/
	public String Imparse(String url,int waitTime)
	{
		try 
		{
			WebClient webClient = WebConstructor.ConstructWebClient();
			HtmlPage page = webClient.getPage(url);
			Thread.sleep(waitTime);
			if(page != null)
			{
				webClient.closeAllWindows();
				return page.asXml();
			}
		} catch(Exception e){
			LOGGER.error(url);
		}
		return null;
	}
}
