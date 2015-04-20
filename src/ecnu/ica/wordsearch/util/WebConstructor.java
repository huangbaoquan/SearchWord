package ecnu.ica.wordsearch.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月17日下午9:52:05
 *@Description : TODO
 */
public class WebConstructor {
	
	public WebConstructor() 
	{

	}
	/**
	 * 配置模拟浏览器的属性
	 * @return
	 */
	public static WebClient ConstructWebClient()
	{
		// 设置模拟浏览器的版本型号
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
		// 设置支持浏览器是否解析javascript和 css
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 设置浏览器链接超时时间
		webClient.getOptions().setTimeout(120000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setAppletEnabled(false);
		return webClient;
	}
	/*public static void main(String[] args) {
		WebClient webClient = WebConstructor.ConstructWebClient();
		try {
			HtmlPage page = webClient.getPage("http://www.researchgate.net/topic/artificial_intelligence");
			Document doc = Jsoup.parse(page.asXml());
			Element element = doc.select(".c-list").first();
			System.out.println(element.toString());
			//System.out.println(element.asXml());
		} catch (Exception e) {
			e.toString();
		}
	}*/
}
