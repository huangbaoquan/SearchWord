package ecnu.ica.wordsearch.SGST;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.task.DownloadpdfTask;
import ecnu.ica.wordsearch.util.Configuration;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;

/**
 *@ClassName   : DownloadPDF.java
 *@Package     : ecnu.ica.wordsearch.SGST
 *@Author      : baoquanhuang 
 *@Email       : baoquanhuang@gmail.com
 *@Date        : 2015年4月30日下午5:02:31
 *@Description : TODO DownLoad SGST.cn paper
 */
public class DownloadPDF {
	
	private static Logger logger = Logger.getLogger(DownloadPDF.class);
	private static String PATH = System.getProperty("user.dir");
	private HtmlPage loginPage;
	private static Configuration conf;
	private final static String CONFIG_FILE_NAME = System.getProperty("user.dir") + File.separator + "config" + File.separator + "conf.properties";
	private static int COUNT;
	/** 
	* @Title       : DownloadPDF
	* @Description : TODO initializer DownLoad
	* @return      : 
	*/
	private void init()
	{
		//Login in SGST.cn
		SearchPaper sPaper = new SearchPaper();
		loginPage = sPaper.searchPaper();
	}
	public DownloadPDF() 
	{
		init();
	}
	public void download(HtmlPage page)
	{
		if(page != null)
		{
			Downloader downloader = new Downloader();
			try 
			{
				/**
				 * get page count
				 */
				int CountPage = downloader.fetchPaperNum(page);
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
				HtmlPage gotoPage = downloader.scrollPage(page, num);
				
				String fileFloderName = "集成电路PDF";
				String filepath = "";
				if(CreateFloderFile.CreateFolder(PATH, fileFloderName))
				{
					filepath = PATH + File.separator + fileFloderName;
					System.out.println("CreatFloderFile Success" + fileFloderName);
				}
				else
				{
					filepath = PATH + File.separator + fileFloderName;
				}
				while(COUNT < CountPage)
				{
					try 
					{	
						while(gotoPage != null)
						{
							int i = 5;
							//Crawl URLS
							ArrayList<String> URLS = downloader.fetchPaperUrl(gotoPage);
							DownloadpdfTask downloadpdfTask = new DownloadpdfTask(filepath, URLS);
							downloadpdfTask.run();
							HtmlPage tempNextPage = gotoPage;
							gotoPage = downloader.clickNextPage(gotoPage);
							/**
							 * try 3 times to get nextPage in order to exception
							 */
							while(gotoPage == null && i > 0)
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
							COUNT++;
						}
						conf.setValue(COUNT);
						System.out.println(COUNT);
						break;
					} catch (Exception e) {
						conf.setValue(COUNT);
					}
				}
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		DownloadPDF downloadPDF = new DownloadPDF();
		downloadPDF.download(downloadPDF.loginPage);
	}
}
