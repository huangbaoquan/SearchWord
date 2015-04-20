package ecnu.ica.wordsearch.SGST;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.job.SGSTJob;
import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@ClassName   : Search.java
 *@Package     : ecnu.ica.wordsearch.SGST
 *@Author      : baoquanhuang 
 *@Email       : baoquanhuang@gmail.com
 *@Date        : 2015年4月16日下午7:37:28
 *@Description : TODO Typing key word On the search Web
 */
public class Search {
	private static Logger logger = Logger.getLogger(Search.class);
	/**
	 * @Fields hostUrl : TODO Search Home Page
	 */
	final private String hostUrl;
	/**
	 * @Fields searchWord : TODO Search Word
	 */
	final private String searchWord;
	public static ArrayList<String>  URLS = new ArrayList<>();
	public static String PATH = System.getProperty("user.dir");
	
	public Search(String url,String word) {
		this.hostUrl = url;
		this.searchWord = word;
	}
	/** 
	* @Title       : Search
	* @Description : TODO Search Word And Return Page
	* @return      : 
	*/
	public void SearchResult()
	{
		WebClient webClient = WebConstructor.ConstructWebClient();
		Downloader downloader = new Downloader();
		HtmlPage nextPage = null;
		try 
		{
			HtmlPage homePage = webClient.getPage(hostUrl);
			HtmlInput inputPage = homePage.getElementByName("exp");
			inputPage.setValueAttribute(searchWord);
			
			HtmlPage resultPage = ((HtmlElement) homePage.getElementById("s-btn")).click();
			/**
			 * Crawl URLS
			 */
			URLS = downloader.fetchPaperUrl(resultPage);
			if(resultPage != null)
			{
				nextPage = downloader.clickNextPage(resultPage);
				while(nextPage != null)
				{
					int i = 5;
					ArrayList<String> tempURL = downloader.fetchPaperUrl(nextPage);
					URLS.addAll(tempURL);
					HtmlPage tempNextPage = nextPage;
					nextPage = downloader.clickNextPage(nextPage);
					/**
					 * try 3 times to get nextPage in order to exception
					 */
					while(nextPage == null && i > 0)
					{
						nextPage = downloader.clickNextPage(tempNextPage);
						if(nextPage != null)
						{
							break;
						}
						i--;
					}
					System.out.println(TheCrawlerUtil.GetCurrentDate() + 
							" Waiting For Downloading URL's Numbers: " + URLS.size());
				}
			}
			webClient.closeAllWindows();
			
			/**
			 * Get All URL And Parse Those URL
			 * Start The JOB Of URL
			 */
			String fileFloderName = TheCrawlerUtil.ToStopChar(searchWord);
			if(CreateFloderFile.CreateFolder(PATH, fileFloderName))
			{
				String filepath = PATH + File.separator + fileFloderName;
				SGSTJob sJob = new SGSTJob(filepath, URLS);
				sJob.excute();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	public static void main(String[] args) {
		String url = "http://lib-wf.sgst.cn/";
		String word = "CLCNumber:TP30";
		Search search = new Search(url,word);
		search.SearchResult();
	}
}
