package ecnu.ica.wordsearch.task;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import ecnu.ica.wordsearch.SGST.parseSGST;
import ecnu.ica.wordsearch.job.QueryURL;
import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月8日下午3:52:27
 *@Description : TODO
 */
public class SGSTTask extends Task{

	final private static int EXCUTECOUNT = 100;
	final private Logger logger = Logger.getLogger(SGSTTask.class);
	public String PATH;  
	public ArrayList<String> ItemList;
	private int ID;
	public SGSTTask(String path, ArrayList<String> itemURL,int ID) {
		this.ID = ID;
		this.PATH = path;
		this.ItemList = itemURL;
	}

	@Override
	public void run() {
		while(true)
		{
			/**
			 * assgin url's numbers to parse
			 */
			ArrayList<String> crawList = QueryURL.queryUrl(ItemList, EXCUTECOUNT,ID);
			if(crawList == null||crawList.size() == 0)
			{
				logger.info("JOB is done");
				break;
			}
			else
			{
				try 
				{
					parseSGST pSgst = new parseSGST();
					for(int i = 0;i < crawList.size();i++)
					{ 
						//Fetch page's contents
						String contents = pSgst.Imparse(crawList.get(i));
						if(contents != null)
						{
							//Generate File Name through random
							//String FileName = CreateFloderFile.GenerateRandomFilename();
							//Generate File Name Through URL
							String FileName = crawList.get(i).toString()
									.replaceAll("/", "").replaceAll(".aspx", "").replaceAll("\\.", "").replaceAll(":", "");
							//Save File into assigned Path 
							CreateFloderFile.CreateFileAndWrite(this.PATH, contents, FileName);
						}
						else
						{
							/**
							 *if URL　cannot be parse,let it do three times
							 *Set Time To Wait
							 */
							int waittime = 5000;
							for(int loop =0;loop < 3;loop++)
							{
								contents = pSgst.Imparse(crawList.get(i),waittime);
								if(contents != null)
								{
									String FileName = crawList.get(i).toString().
											replaceAll("/", "").replaceAll(".aspx", "").replaceAll("\\.", "").replaceAll(":", "");
									CreateFloderFile.CreateFileAndWrite(this.PATH, contents, FileName);
									break;
								}
								waittime += 5000;
							}
							if(contents == null)
							{
								logger.error(TheCrawlerUtil.GetCurrentDate() + " 未能正常解析："+ crawList.get(i));
							}
						}
					}
				} 
				catch (Exception e) 
				{
					logger.error(e.toString() + TheCrawlerUtil.GetCurrentDate());
					e.printStackTrace();
				}
			}
		}
	}
	/*public static void main(String[] args) throws InterruptedException {
		ArrayList<String> itemURL = new ArrayList<>();
		itemURL.add("http://lib-wf.sgst.cn/D/Periodical_zggyjj200507003.aspx");
		String FileName = itemURL.get(0).toString().replaceAll("/", "").replaceAll(".aspx", "").replaceAll("\\.", "").replaceAll(":", "");
		System.out.println(FileName);
		SGSTTask sTask = new SGSTTask("C:\\Users\\Administrator\\workspace\\WordSearch\\集成电路", itemURL);
		sTask.run();
	}*/
}
