package ecnu.ica.wordsearch.SGST;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import ecnu.ica.wordsearch.util.SingletonWebClient;
import ecnu.ica.wordsearch.util.WebConstructor;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月3日上午1:38:39
 *@Description : TODO
 */
public class Login {
	private static Logger log = Logger.getLogger(Login.class);
	final private static String LOGIN_STRING = "huangbaoquan2015";
	final private static String PASS_STRING = "12345ecnu";
	final private static String LOGINURL_STRING = "http://116.236.205.179/ids/LoginServlet?coAppName=VVVNUw%3D%3D&coSessionId=VkQxTnRQOGI3bkZoZkJqMzZMVEN2TXBtbTdOR2trcHN4cjF0a1J4NkwybEZMalJYNHlaRyEtMTE1MjkwNjkzMyExNDMwNDg2ODI5ODQ0&surl=aHR0cDovL21lbWJlci5zZ3N0LmNuOjgwL3V1bXMvbG9naW4uanNwP2JhY2tVcmw9aHR0cDovL2xpYi5zZ3N0LmNuLyZjb20udHJzLnVzZUlEU1NTT1BhZ2U9MQ==";

	public Login() {}
	public HtmlPage login()
	{
		WebClient webClient = SingletonWebClient.getInstance().webClient;
		try 
		{
			HtmlPage loginPage = webClient.getPage(LOGINURL_STRING);
			HtmlForm form = loginPage.getFormByName("logon");
			
			HtmlTextInput htmlTextInput_name = form.getInputByName("userName");
			htmlTextInput_name.setValueAttribute(LOGIN_STRING);
			
			HtmlPasswordInput htmlTextInput_passwd = form.getInputByName("password");
			htmlTextInput_passwd.setValueAttribute(PASS_STRING);
			
			HtmlPage LoginPage = loginPage.getAnchorByHref("javascript:doLogin();").click();
			//get the Paper li from all
//			System.out.println(LoginPage.asXml());
			System.out.println("Login Success!");
			return LoginPage;
			
		} catch (FailingHttpStatusCodeException e) {
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
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.login();
	}*/
}
