package ecnu.ica.wordsearch.SGST;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BinaryPage;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.parse.parse;
import ecnu.ica.wordsearch.util.SingletonWebClient;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月8日上午10:02:41
 *@Description : TODO
 */
public class parseSGST implements parse{
	final private static Logger LOGGER = Logger.getLogger(parseSGST.class);   
	public parseSGST() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * parse SGST of page into HTML
	 */
	@Override
	public String Imparse(String url) 
	{
		try 
		{
			//WebClient webClient = WebConstructor.ConstructWebClient();
			WebClient webClient = SingletonWebClient.getInstance().webClient;
			HtmlPage page = webClient.getPage(url);
			if(page != null)
			{
				webClient.closeAllWindows();
//				System.out.println(page.asText());
				return page.asXml();
			}
		} catch(Exception e){
			LOGGER.error(url);
		}
		return null;
	}
	/** 
	* @Title       : parseSGST
	* @Description : Set Wait Time 
	* @return      : @param url
	* @return      : @param waitTime
	* @return      : @return Page's HTML
	*/
	public String Imparse(String url,int waitTime)
	{
		try 
		{
			WebClient webClient = WebConstructor.ConstructWebClient();
			HtmlPage page = webClient.getPage(url);
			Thread.sleep(waitTime);
			if(page != null)
			{
				webClient.closeAllWindows();
				return page.asXml();
			}
		} catch(Exception e){
			LOGGER.error(url);
		}
		return null;
	}
	/** 
	* @Title       : parseSGST
	* @Description : TODO download PDF
	* @return      : @param url
	*/
	public void downloadpdf(String url,String path)
	{
		try 
		{
			WebClient webClient = SingletonWebClient.getInstance().webClient;
			HtmlPage page = webClient.getPage(url);
			if(page != null)
			{
				HtmlAnchor downAnchor = page.getFirstByXPath("//*[@id=\"fulltext\"]/a[2]");
				if(downAnchor != null)
				{
					/*HtmlPage p = downAnchor.click();
					System.out.println(p.asText());*/
					
					InputStream is = downAnchor.click().getWebResponse().getContentAsStream();
					FileOutputStream os = new FileOutputStream(new File(path + File.separator + CreateFloderFile.GenerateRandomFilename()+".pdf"));
					byte[] by = new byte[1024];
					int read = 0;
					while((read = is.read(by))!= -1)
					{
						os.write(by,0,read);
					}
					os.flush();
					os.close();
					is.close();
					System.out.println("downloading PDF...\n");
				}
				else
				{
					LOGGER.info(url + " download failed");
				}
			}
			webClient.closeAllWindows();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString());
		}
	}
}
