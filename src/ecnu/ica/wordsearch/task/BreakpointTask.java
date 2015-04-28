package ecnu.ica.wordsearch.task;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ecnu.ica.wordsearch.SGST.parseSGST;
import ecnu.ica.wordsearch.model.CreateFloderFile;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;

public class BreakpointTask extends Task{
	
	final private Logger logger = Logger.getLogger(SGSTTask.class);
	public String PATH;  
	public ArrayList<String> ItemList;

	public BreakpointTask(String path,ArrayList<String> list) 
	{
		this.PATH = path;
		this.ItemList = list;
	}

	@Override
	public void run() {
		parseSGST pSgst = new parseSGST();
		for(int i = 0;i < ItemList.size();i++)
		{
			//Fetch page's contents
			String contents = pSgst.Imparse(ItemList.get(i));
			if(contents != null)
			{
				//Generate File Name through random
				//String FileName = CreateFloderFile.GenerateRandomFilename();
				//Generate File Name Through URL
				String FileName = ItemList.get(i).toString()
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
					contents = pSgst.Imparse(ItemList.get(i),waittime);
					if(contents != null)
					{
						String FileName = ItemList.get(i).toString().
								replaceAll("/", "").replaceAll(".aspx", "").replaceAll("\\.", "").replaceAll(":", "");
						CreateFloderFile.CreateFileAndWrite(this.PATH, contents, FileName);
						break;
					}
					waittime += 5000;
				}
				if(contents == null)
				{
					logger.error(TheCrawlerUtil.GetCurrentDate() + " 未能正常解析："+ ItemList.get(i));
				}
			}
		}
	}
}
