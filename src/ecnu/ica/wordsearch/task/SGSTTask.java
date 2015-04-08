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

	final private static int EXCUTECOUNT = 30;
	final private Logger logger = Logger.getLogger(SGSTTask.class);
	public String PATH;  
	public ArrayList<String> ItemList;
	
	public SGSTTask(String path, ArrayList<String> itemURL) {
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
			ArrayList<String> crawList = QueryURL.queryUrl(ItemList, EXCUTECOUNT);
			System.out.println("Remain Task Count: "+ ItemList.size());
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
						//Generate File Name through random
						String FileName = CreateFloderFile.GenerateRandomFilename();
						//Save File into assigned Path 
						CreateFloderFile.CreateFileAndWrite(this.PATH, contents, FileName);
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
	/*public static void main(String[] args) {
		ArrayList<String> itemURL = new ArrayList<>();
		itemURL.add("http://lib-wf.sgst.cn/D/Periodical_zggyjj200507003.aspx");
		SGSTTask sTask = new SGSTTask("C:\\Users\\Administrator\\workspace\\WordSearch\\集成电路", itemURL);
		sTask.run();
	}*/
}
