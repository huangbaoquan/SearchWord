package ecnu.ica.wordsearch.SGST;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.job.SGSTJob;
import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;
import ecnu.ica.wordsearch.util.WebConstructor;


/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月3日上午1:38:33
 *@Description : TODO
 */
public class SearchPaper {
	private static Logger log = Logger.getLogger(SearchPaper.class);
	final private static String KEY_WORD__STRING= "集成电路"; 
	final private static String YIWENXIAN = "http://lib.sgst.cn/";
	public static String PATH = System.getProperty("user.dir");
	final private String FilePath =PATH + File.separator + KEY_WORD__STRING;
	public SearchPaper() {
	}
	
	/**
	 * request 集成电路  and return the paper page 
	 * @return
	 */
	/*public HtmlPage searchPaper()
	{
		//Login the 
		Login login = new Login();
		HtmlPage page = login.login();
		
		HtmlInput htmlInput = page.getHtmlElementById("top_search_keyword");
		htmlInput.setValueAttribute(KEY_WORD__STRING);		
		try 
		{
			HtmlPage searchResultPage = page.getAnchorByHref("javascript:topSearch();").click();
			HtmlPage paperPage = searchResultPage.getAnchorByHref("search/s_list?keyword=%E9%9B%86%E6%88%90%E7%94%B5%E8%B7%AF&type=%E8%AE%BA%E6%96%87").click();
			System.out.println(paperPage.asText());
			return paperPage;
		} catch (ElementNotFoundException e) {
			log.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}*/
	
	/**
	 *use yi wen xian Entrance to Search Paper 
	 */
	public void searchPaperOnlyHTML()
	{
		ArrayList<String> TotalURL = new ArrayList<>();
		WebClient webClient = WebConstructor.ConstructWebClient();
		Downloader downloader = new Downloader();
		try 
		{
			HtmlPage page =  webClient.getPage(YIWENXIAN);
			// Typing the Key Word and 
			HtmlInput hInput = page.getHtmlElementById("paperField");
			hInput.setValueAttribute(KEY_WORD__STRING);
			HtmlPage nextPage = null;
			try 
			{
				HtmlPage searchPage = page.getAnchorByHref("javaScript:searchPapers('paper','paperField');").click();
				//System.out.println(searchPage.asText());
				TotalURL = downloader.fetchPaperUrl(searchPage);
				if(searchPage != null)
				{
					nextPage = downloader.clickNextPage(searchPage);
					while(nextPage != null)
					{
						ArrayList<String> tempURL = downloader.fetchPaperUrl(nextPage);
						TotalURL.addAll(tempURL);
						nextPage = downloader.clickNextPage(nextPage);
						System.out.println(TheCrawlerUtil.GetCurrentDate() + 
								" Waiting For Downloading URL's Numbers: " + TotalURL.size());
					}
				}
				webClient.closeAllWindows();
				if(CreateFloderFile.CreateFolder(PATH, KEY_WORD__STRING))
				{
					String filepath = PATH + File.separator + KEY_WORD__STRING;
					SGSTJob sJob = new SGSTJob(filepath, TotalURL);
					sJob.excute();
				}
				else
				{
					SGSTJob sJob = new SGSTJob(FilePath, TotalURL);
					sJob.excute();
				}
			} catch (Exception e) {
				log.error(e.toString());
				e.printStackTrace();
			}
		} catch (Exception e){
			log.error(e.toString());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SearchPaper searchPaper = new SearchPaper();
		searchPaper.searchPaperOnlyHTML();
	}
}
