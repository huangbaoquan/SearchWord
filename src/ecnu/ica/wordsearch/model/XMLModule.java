package ecnu.ica.wordsearch.model;

import org.jdom.Document;
import org.jdom.Element;


/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月18日下午1:26:49
 *@Description : TODO
 */
public class XMLModule extends XML{

	public String alias;
	public String title;
	public String BAIKEContents;
	public String time;
	/**
	 * define BAIKE Elements
	 */
	public Element BAIKE;
	public Element BaikeAliasElement;
	public Element BaikeTitleElement;
	public Element BaikeDescElement;
	public Element BaikeHistElement;
	public Element BaikeRefElement;
	/**
	 * define Wiki Elements
	 */
	public Element WIKI;
	public Element WikiAliasElement;
	public Element WikiTitleElement;
	public Element WikiDescElement;
	public Element WikiHistElement;
	public Element WikiRefElement;
	
	public Element crawlTimeElement;
	public Document doc;
	
	private void init()
	{
		doc = new Document();
		
		Element Root = new Element("doc");
		doc.addContent(Root);
		
		Element BAIKE = new Element("BAIKE");
		Element WIKI = new Element("WIKI");
		
		Root.addContent(BAIKE);
		Root.addContent(WIKI);
		/**
		 * add baike node
		 */
		WikiTitleElement = new Element("title");
		WIKI.addContent(WikiTitleElement);
		
		WikiAliasElement = new Element("alias");
		WIKI.addContent(WikiAliasElement);
		
		WikiDescElement = new Element("description");
		WIKI.addContent(WikiDescElement);
		
		WikiHistElement = new Element("histories");
		WIKI.addContent(WikiHistElement);
		
		WikiRefElement = new Element("references");
		WIKI.addContent(WikiRefElement);
		/**
		 * add baike node 
		 */
		BaikeTitleElement = new Element("title");
		BAIKE.addContent(BaikeTitleElement);
		
		BaikeAliasElement = new Element("alias");
		BAIKE.addContent(BaikeAliasElement);
		
		BaikeDescElement = new Element("description");
		BAIKE.addContent(BaikeDescElement);
		
		BaikeHistElement = new Element("histories");
		BAIKE.addContent(BaikeHistElement);
		
		BaikeRefElement = new Element("references");
		BAIKE.addContent(BaikeRefElement);
		
		crawlTimeElement = new Element("crawlTime");
		Root.addContent(crawlTimeElement);
	}
	public XMLModule() {
		init();
	}
	/*public static void main(String[] args) {
		XMLModule xmlModule = new XMLModule();
		xmlModule.BaikeTitleElement.setText("dafd");
		xmlModule.BaikeAliasElement.setText("asdfa");
		xmlModule.BaikeDescElement.setText("dfa");
		xmlModule.BaikeHistElement.setText("dada");
		xmlModule.BaikeRefElement.setText("daa");
		
		xmlModule.WikiAliasElement.setText("wiki");
		xmlModule.WikiDescElement.setText("wiki");
		xmlModule.WikiHistElement.setText("wiki");
		xmlModule.WikiRefElement.setText("wiki");
		xmlModule.WikiTitleElement.setText("wiki");
		
		xmlModule.crawlTimeElement.setText("2015");
		System.out.print(System.getProperty("user.dir"));
		XML.CreateXMLFileAndWrite(System.getProperty("user.dir"), xmlModule.doc,"集成电路");
	}*/
}
