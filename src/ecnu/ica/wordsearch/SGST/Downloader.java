package ecnu.ica.wordsearch.SGST;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月4日上午1:14:04
 *@Description : TODO
 */
public class Downloader {

	private static Logger logger = Logger.getLogger(Downloader.class);
	final private static String URL = "http://lib-wf.sgst.cn";
	public Downloader() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * find the url paper of Page
	 * @param page
	 * @return
	 */
	public ArrayList<String> fetchPaperUrl(HtmlPage page)
	{
 		ArrayList<String> paperUrl = new ArrayList<>();
		try 
		{
			/*
			 * version:0.0.1 
			 * 
			 * Document doc = Jsoup.parse(page.asXml());
			Elements tabElements =  doc.getElementsByClass("tabso_lb");
			for(Element ele : tabElements)
			{
				//find tabso_1
				Element tabso_1 = ele.getElementsByClass("tabso_l").first();
				//find tabso_1_bt
				Element tabso_1_bt = tabso_1.getElementsByClass("tabso_l_bt").first();
				System.out.println(tabso_1_bt.getElementsByTag("a").attr("href"));
				paperUrl.add(tabso_1_bt.getElementsByTag("a").attr("href"));
			}*/
			/**
			 * Version: 0.0.2 易文献
			 */
			Document doc = Jsoup.parse(page.asXml());
			Elements tabElements =  doc.getElementsByClass("list_ul");
			for(Element ele : tabElements)
			{
				Element e = ele.getElementsByTag("a").first();
				String relativeURL = e.attr("href").toString();
				relativeURL = URL + relativeURL;
				//System.out.println(relativeURL.toString());
				paperUrl.add(relativeURL.toString());
			}
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return paperUrl;
	}
	
	/**
	 * click next page
	 */
	public HtmlPage clickNextPage(HtmlPage page)
	{
		HtmlAnchor nextpageAnchor = null;
		try 
		{
			nextpageAnchor = page.getAnchorByText("下一页");
			
		} catch (ElementNotFoundException e1) {
			logger.error(e1.toString());
			e1.printStackTrace();
		}
		if(nextpageAnchor != null)
		{
			try 
			{
				HtmlPage htmlPage = nextpageAnchor.click();
				return htmlPage;
			} catch (IOException e) {
				logger.error(TheCrawlerUtil.LogErrorInfo(e));
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Scroll assign Page
	 */
	public HtmlPage scrollPage(HtmlPage page ,int num)
	{
		//set goto page num
		HtmlInput GoToPageBoxPagerControl1 = (HtmlInput) page.getElementById("GoToPageBoxPagerControl1");
		GoToPageBoxPagerControl1.setValueAttribute(num+"");
		//click button
		HtmlInput jumpbutton = page.getFirstByXPath("//*[@id=\"content_paper\"]/div/p[3]/input[2]");
		HtmlPage gotopage;
		try {
			gotopage = jumpbutton.click();
			return gotopage;
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	public int fetchPaperNum(HtmlPage page)
	{
		Document doc = Jsoup.parse(page.asXml());
		String count = doc.getElementsByClass("page_link").first().text();
		String number = "";
		for(int i=0;i<count.length();i++)
		{
			if(count.charAt(i)>='0' && count.charAt(i)<='9')
			{
				number+=count.charAt(i);
			}
		}
		return Integer.parseInt(number);
	}
}
