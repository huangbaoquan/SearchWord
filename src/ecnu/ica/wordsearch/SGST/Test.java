package ecnu.ica.wordsearch.SGST;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月4日上午1:18:31
 *@Description : TODO
 */
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		/*WebClient webClient = WebConstructor.ConstructWebClient();
		
		SearchPaper searchPaper = new SearchPaper();
		//Enter the login Page and search "集成电路"
		HtmlPage pageHome = searchPaper.searchPaper();
		Downloader downloaderPage = new Downloader();
		ArrayList<String> paperUrl = downloaderPage.fetchPaperUrl(pageHome);
		for(String url:paperUrl)
		{
			HtmlPage page;
			try {
				page = webClient.getPage(url);
				System.out.println(page.asText());
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
		}
		HtmlPage nextPage = downloaderPage.clickNextPage(pageHome);
		System.out.println(nextPage.asText());*/
	}
}
