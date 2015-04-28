package ecnu.ica.wordsearch.SGST;

import java.io.File;
import java.util.ArrayList;

import javax.naming.ConfigurationException;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.job.SGSTJob;
import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.task.BreakpointTask;
import ecnu.ica.wordsearch.util.Configuration;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@ClassName   : BreakpointCrawler.java
 *@Package     : ecnu.ica.wordsearch.SGST
 *@Author      : baoquanhuang 
 *@Email       : baoquanhuang@gmail.com
 *@Date        : 2015年4月23日下午10:59:41
 *@Description : TODO breakPoint Crawler
 */
public class BreakpointCrawler {
	private static final Logger LOGGER = Logger.getLogger(BreakpointCrawler.class);
	private static Configuration conf;
	private final static String CONFIG_FILE_NAME = System.getProperty("user.dir") + File.separator + "config" + File.separator + "conf.properties";
	public static String PATH = System.getProperty("user.dir");
	private static int COUNT;
	/**
	 * @Fields hostUrl : TODO Search Home Page
	 */
	final private String hostUrl;
	/**
	 * @Fields searchWord : TODO Search Word
	 */
	final private String searchWord;
	public void init()
	{
		try 
		{
			conf = new Configuration(CONFIG_FILE_NAME);
			COUNT = Integer.parseInt(conf.getValue("NumPage"));
			System.err.println("BreakpointCrawler init");
		} catch (ConfigurationException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/** 
	* init 
	*/
	public BreakpointCrawler(String url,String word) {
		init();
		this.hostUrl = url;
		this.searchWord = word;
	}

	public void parse(ArrayList<String> URLS)
	{
		
	}
	public void breakpoint()
	{
		//search word and get result page
		WebClient webClient = WebConstructor.ConstructWebClient();
		Downloader downloader = new Downloader();
		HtmlPage nextPage = null;
		try 
		{
			/**
			 * implement Search Word and Fetch Result Page
			 */
			HtmlPage homePage = webClient.getPage(hostUrl);
			HtmlInput inputPage = homePage.getElementByName("exp");
			inputPage.setValueAttribute(searchWord);
			HtmlPage resultPage = ((HtmlElement) homePage.getElementById("s-btn")).click();
			/**
			 * Get Page Count
			 */
			int CountPage = downloader.fetchPaperNum(resultPage);
			/**
			 * Get Have Done Page Count
			 */
			Configuration configuration = new Configuration(CONFIG_FILE_NAME);
			int num = Integer.parseInt(configuration.getValue("NumPage"));
			COUNT = num;
			/**
			 * The First Time To Load "NumPage"
			 * Scroll Specific Number of Page
			 */
			HtmlPage gotoPage = downloader.scrollPage(resultPage, num);
			
			String fileFloderName = TheCrawlerUtil.ToStopChar(searchWord);
			String filepath = "";
			if(CreateFloderFile.CreateFolder(PATH, fileFloderName))
			{
				filepath = PATH + File.separator + fileFloderName;
			}
			else
			{
				filepath = PATH + File.separator + fileFloderName;
			}
			
			while(COUNT <= CountPage)
			{
				try 
				{
					while(gotoPage != null)
					{
						int i = 5;
						//Crawl URLS
						ArrayList<String> URLS = downloader.fetchPaperUrl(gotoPage);
						// Storage Paper Contents
						BreakpointTask breakpointTask = new BreakpointTask(filepath, URLS);
						breakpointTask.run();
						HtmlPage tempNextPage = gotoPage;
						gotoPage = downloader.clickNextPage(gotoPage);
						/**
						 * try 3 times to get nextPage in order to exception
						 */
						while(nextPage == null && i > 0)
						{
							gotoPage = downloader.clickNextPage(tempNextPage);
							if(gotoPage != null)
							{
								break;
							}
							i--;
						}
						System.out.println(TheCrawlerUtil.GetCurrentDate() + 
								"Downloading URL Page Numbers: " + COUNT*10);
						COUNT ++;
					}
				} catch (Exception e) {
					conf.setValue(COUNT);
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String url = "http://lib-wf.sgst.cn/";
		String word = "CLCNumber:TP33";
		BreakpointCrawler breakpointCrawler = new BreakpointCrawler(url, word);
		breakpointCrawler.breakpoint();
	}
}
