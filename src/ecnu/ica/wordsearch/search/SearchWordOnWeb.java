package ecnu.ica.wordsearch.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import ecnu.ica.wordsearch.model.WordInfo;
import ecnu.ica.wordsearch.parse.HtmlParse;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月18日下午1:25:59
 *@Description : TODO
 */
public class SearchWordOnWeb  {
	
	public final static String BAIKE = "http://baike.baidu.com/";
	public final static String WIKI = "http://zh.wikipedia.org/wiki/Wikipedia:%E9%A6%96%E9%A1%B5";
	
	public SearchWordOnWeb() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * search on WIKI
	 * @param word
	 * @return
	 */
	public static String searchWiki(String word) {
		//WordInfo wordInfo = new WordInfo();
		String pageContents = "";
		//String reference ="";
		WebClient webClient = WebConstructor.ConstructWebClient();
		HtmlPage resultPage;
		try 
		{
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(word);
			word = m.replaceAll("");
			HtmlPage page = webClient.getPage(WIKI);
			/**
			 * Search the word on WIKI 
			 */
			HtmlForm form = page.getForms().get(0);
			HtmlTextInput htmlTextInput = form.getInputByName("search");
			htmlTextInput.setValueAttribute(word);
			HtmlSubmitInput go = form.getInputByName("go");
			resultPage = go.dblClick();
			Document doc = Jsoup.parse(resultPage.asXml());
			
			/*Element refElement = doc.getElementsByClass("reflist").first();
			if(refElement != null)
			{
				reference = refElement.text();
				wordInfo.setReference(reference);
			}*/
			
			System.out.println(doc.text());
			/**
			 * 0 represent html page
			 * 1 represent page contents by my parse
			 */
			/*wordInfo.setHtml(resultPage.asXml());
			wordInfo.setTitle(word);
			wordInfo.setDescription(pageContents);
			wordInfo.setReference(reference);
		    return wordInfo;*/
			return resultPage.asXml();
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
	public static String searchBaike(String word)
	{
		//WordInfo wordInfo = new WordInfo();
		String pageContents = "";
		/*String reference = "";
		String history = "";*/
		WebClient webClient = WebConstructor.ConstructWebClient();
		HtmlPage resultPage;
		try 
		{
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(word);
			word = m.replaceAll("");
			HtmlPage page = webClient.getPage(BAIKE);
			/**
			 * Enter the word on search 
			 */
			HtmlForm form = page.getForms().get(0);
			HtmlTextInput htmlTextInput = form.getInputByName("word");
			htmlTextInput.setValueAttribute(word);
			HtmlButton button = (HtmlButton) page.getElementById("search");
			resultPage = button.click();
			Document doc = Jsoup.parse(resultPage.asXml());
			/**
			 * 此处网页有两种结构mod-top|lemmaContent-0
			 */
			Element textElement = doc.getElementsByClass("mod-top").first();
			if(textElement == null)
			{
				textElement = doc.getElementById("lemmaContent-0");
				if(textElement != null)
				{
					pageContents = textElement.text();
				}
				else 
				{
					return null;
				}
			}
			else 
			{
				//pageContents == description
				pageContents = textElement.text();
				/*Element histElement = doc.getElementById("lemmaContent-0");
				history = histElement.text();*/
			}
			/*Elements refsElements = doc.getElementsByClass("reference");
			reference = refsElements.text();*/
			
			/**
			 * 0 represent html page
			 * 1 represent page contents by my parse
			 */
			
			/*wordInfo.setTitle(word);
		    wordInfo.setHtml(resultPage.asXml());
		    wordInfo.setReference(reference);
		    wordInfo.setDescription(pageContents);
		    wordInfo.setHistory(history);
		    System.out.println(wordInfo.toString());
		    return wordInfo;*/
			return resultPage.asXml();
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
	
	public static void main(String[] args) {
		SearchWordOnWeb searchWordOnWeb = new SearchWordOnWeb();
		//searchWordOnWeb.searchBaike("计算机");
		searchWordOnWeb.searchWiki("计算机");
	}
	
}
