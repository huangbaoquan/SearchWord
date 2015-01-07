/*package ecnu.ica.wordsearch.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ecnu.ica.wordsearch.model.WordInfo;
import ecnu.ica.wordsearch.model.XML;
import ecnu.ica.wordsearch.model.XMLModule;
import ecnu.ica.wordsearch.parse.HtmlParse;
import ecnu.ica.wordsearch.search.SearchWordOnWeb;

*//**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月18日下午12:47:58
 *@Description : TODO
 *//*
public class Entrance {
	
	private final static String PATH = System.getProperty("user.dir");
	private final static String freescale = "http://www.freescale.com/zh-Hans/webapp/sps/site/overview.jsp?code=MCUGLOSSARYTUT_HOME#B";
	
	public Entrance() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		String path  = PATH + File.separator + "freescale";
		File file = new File(path);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
		
		File invaildFile = new File(PATH + File.separator + "invaild.txt");
		
		
		ArrayList<WordInfo> wordInfos = new ArrayList<>();
		wordInfos = HtmlParse.parse(freescale);
		
		for(int i=0;i<wordInfos.size();i++)
		{
			String shortname = wordInfos.get(i).getShortname();
			String name = wordInfos.get(i).getName();
			String pageContent = "";
			XMLModule xmlModule = new XMLModule();
			*//**
			 * becaues shortname and name format is abnormal
			 * There have two formats in the Page
			 *  
			 *//*
			if(shortname.contains(":"))
			{
				String[] word = shortname.split(":");
				String xmlshortname = word[0].trim();
				String searchword = word[1].trim();
				if(searchword.contains("("))
				{
					int point = searchword.indexOf("(");
					searchword = (String) searchword.subSequence(0, point);
					searchword = searchword.trim();
				}
				*//**
				 * search the word on BAIKU 
				 *//*
				//pageContent = SearchWordOnWeb.searchBaike(searchword);
				*//**
				 * pageContent is not null that represent exsits result on BAIKE Page
				 *//*
				if(pageContent != null)
				{
					*//**
					 * Store the data in XML file
					 *//*
					
					xmlModule.titleElement.setText(searchword);
					xmlModule.aliasElement.setText(xmlshortname);
					xmlModule.BAIKEElement.setText(pageContent);
					XML.CreateXMLFileAndWrite(path, xmlModule.doc, searchword);
				}
				else 
				{
					try 
					{
						FileWriter fWriter = new FileWriter(invaildFile,true);
						fWriter.write(shortname + "-------" +name +"\r\n");
						fWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
			else
			{
				//pageContent = SearchWordOnWeb.searchBaike(name);
				if(pageContent != null)
				{
					xmlModule.titleElement.setText(name);
					xmlModule.aliasElement.setText(shortname);
					xmlModule.BAIKEElement.setText(pageContent);
					XML.CreateXMLFileAndWrite(path, xmlModule.doc, name);
				}
				else 
				{
					try 
					{
						FileWriter fWriter = new FileWriter(invaildFile,true);
						fWriter.write(shortname + "-------" +name +"\r\n");
						fWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		System.out.println("Over");
	}
}
*/