package ecnu.ica.wordsearch.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.model.XML;
import ecnu.ica.wordsearch.model.XMLModule;
import ecnu.ica.wordsearch.search.SearchWordOnWeb;
import ecnu.ica.wordsearch.util.Util;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月25日上午9:03:08
 *@Description : TODO
 */
public class NewEntrance {
	public final static String PATH = System.getProperty("user.dir");

	public NewEntrance() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String datapath = PATH + File.separator + "分类";
		ArrayList<String> filepath = new ArrayList<>();
		filepath = CreateFloderFile.TraversalFolds(datapath, filepath);
		
		/**
		 * invildFile file represent word what search nothing on BAIKE
		 */
		
		
		
		if(filepath != null && filepath.size() != 0)
		{
			for(int i=0; i < filepath.size();i++)
			{
				File file = new File(filepath.get(i));

				System.out.println(file.getName());
				
				if(CreateFloderFile.CreateFolder(datapath, file.getName().substring(0, file.getName().lastIndexOf("."))))
				{
					/**
					 * eachfilepath represent classifier fold 
					 */
					String eachfilepath = datapath + File.separator + file.getName().substring(0, file.getName().lastIndexOf("."));
					/**
					 * wrong word into invaild.txt
					 */
					File invaildFile = new File(eachfilepath + File.separator + "invaild.txt");
					try 
					{
						/**
						 * word represent word what need to search on baiku or wiki
						 */
						String word = "";
						/**
						 * read the data file
						 */
						InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"gb2312");
						BufferedReader fReader = new BufferedReader(inputStreamReader);
						while((word = fReader.readLine().trim()) != null)
						{
							System.out.println(word);
							//String[] result = new String[2];
							String resultbaike = "";
							String resultwiki = "";
							String time = Util.GetCurrentDate();
							//XMLModule xmlModule = new XMLModule();
							/**
							 * search the word on web
							 */
							resultbaike = SearchWordOnWeb.searchBaike(word);
							resultwiki = SearchWordOnWeb.searchWiki(word);
							if(resultbaike != null)
							{
								
								//=================================================
								//=================================================
								//               GENERATE XML
								//=================================================
								//=================================================
								
								
								
								/**
								 * if search the word on WEb and return a result
								 * then Store the data in XML file
								 */
								
								/*xmlModule.BaikeTitleElement.setText(word);
								xmlModule.BaikeDescElement.setText(result[1]);
								xmlModule.crawlTimeElement.setText(time);
								XML.CreateXMLFileAndWrite(eachfilepath, xmlModule.doc, word);*/
								
								//=================================================
								//=================================================
								//               GENERATE HTML
								//=================================================
								//=================================================
								
								CreateFloderFile.CreateFileAndWrite(eachfilepath, resultbaike, word+"baike.html");
							}
							else 
							{
								try 
								{
									FileWriter fWriter = new FileWriter(invaildFile,true);
									fWriter.write(word + " Category： " + file.getName() + "\r\n");
									fWriter.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if(resultwiki != null)
							{
								CreateFloderFile.CreateFileAndWrite(eachfilepath, resultwiki, word+"wiki.html");
							}
							else
							{
								try 
								{
									FileWriter fWriter = new FileWriter(invaildFile,true);
									fWriter.write(word + " Category： " + file.getName() + "\r\n");
									fWriter.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
