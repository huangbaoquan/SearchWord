package ecnu.ica.wordsearch.util;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 *@ClassName   : SingletonWebClient.java
 *@Package     : ecnu.ica.wordsearch.util
 *@Author      : baoquanhuang 
 *@Email       : baoquanhuang@gmail.com
 *@Date        : 2015年4月16日上午9:20:47
 *@Description : TODO Singleton Model
 */
public class SingletonWebClient {

	public final WebClient webClient;
	private static class SingletonHolder
	{
		private final static SingletonWebClient INSTANCE_CLIENT  = new SingletonWebClient();
	}
	public SingletonWebClient() {
		System.out.println("进行实例化");
		// 设置模拟浏览器的版本型号
		webClient = new WebClient(BrowserVersion.getDefault());
		// 设置支持浏览器是否解析javascript和 css
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 设置浏览器链接超时时间
		webClient.getOptions().setTimeout(120000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setAppletEnabled(false);
	}

	public static SingletonWebClient getInstance()
	{
		return SingletonHolder.INSTANCE_CLIENT;
	}
	/*public static void main(String[] args) {
		SingletonWebClient singletonWebClient1 = SingletonWebClient.getInstance();
		SingletonWebClient singletonWebClient2 = SingletonWebClient.getInstance();
		
		if(singletonWebClient1.webClient ==null)
		{
			System.out.println("1 null");
		}
		else {
			System.out.println("1 not null");
		}
		if(singletonWebClient2.webClient == null)
		{
			System.out.println("2 null");
		}
		else {
			System.out.println("2 not null");
		}
		if(singletonWebClient1.webClient == singletonWebClient2.webClient)
			System.out.println("1 = 2 true");
		else
			System.out.println("false");
	}*/
}
