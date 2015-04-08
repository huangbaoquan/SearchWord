package ecnu.ica.wordsearch.SGST;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.parse.parse;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月8日上午10:02:41
 *@Description : TODO
 */
public class parseSGST implements parse{

	public parseSGST() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * parse SGST of page into HTML
	 */
	@Override
	public String Imparse(String url) {
		try 
		{
			WebClient webClient = WebConstructor.ConstructWebClient();
			HtmlPage page = webClient.getPage(url);
			if(page != null)
			{
				webClient.closeAllWindows();
//				System.out.println(page.asText());
				return page.asXml();
			}
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
