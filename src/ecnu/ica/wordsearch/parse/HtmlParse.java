package ecnu.ica.wordsearch.parse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.model.WordInfo;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月17日下午9:54:16
 *@Description : TODO
 */
public class HtmlParse {
	private final static Logger log = Logger.getLogger(HtmlParse.class);

	public HtmlParse() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * parse the freesacle Page to fetch corresponing word 
	 * @param url
	 */
	/*public static ArrayList<WordInfo> parse(String url)
	{
		WebClient webClient ;
		ArrayList<WordInfo> wordInfos = new ArrayList<>();
		try 
		{
			webClient = WebConstructor.ConstructWebClient();
			HtmlPage page = webClient.getPage(url);
			Document doc = Jsoup.parse(page.asXml());
			Element root = doc.getElementsByClass("lc").first();
			Elements shorthands = root.getElementsByTag("strong");
			Elements vocabularys = root.getElementsByClass("intro");
			
			*//**
			 * This is why I　write "for loop" a bug 
			 * for the Page Struct,So Bad Page
			 *//*
			for(int i=0,j=0;i<shorthands.size();i++,j++)
			{
				WordInfo wordInfo = new WordInfo();
				if(shorthands.get(i).text().equals("MCU：微控制器"))
				{
					j--;
					continue;
				}
				System.out.println(shorthands.get(i).text() + "---" +vocabularys.get(j).text());
				wordInfo.setShortname(shorthands.get(i).text());
				wordInfo.setName(vocabularys.get(j).text());
				wordInfos.add(wordInfo);
			}
			return wordInfos;
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}*/
	/*public static void main(String[] args) {
		HtmlParse.parse("http://www.freescale.com/zh-Hans/webapp/sps/site/overview.jsp?code=MCUGLOSSARYTUT_HOME#B");
	}*/
}
