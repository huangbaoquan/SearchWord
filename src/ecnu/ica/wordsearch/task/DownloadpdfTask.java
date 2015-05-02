package ecnu.ica.wordsearch.task;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ecnu.ica.wordsearch.SGST.parseSGST;

public class DownloadpdfTask extends Task {
	final private Logger logger = Logger.getLogger(SGSTTask.class);
	public String PATH;  
	public ArrayList<String> ItemList;

	public DownloadpdfTask(String path,ArrayList<String> list) 
	{
		this.PATH = path;
		this.ItemList = list;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		parseSGST pSgst = new parseSGST();
		for(int i = 0;i < ItemList.size();i++)
		{
			try 
			{
				//Fetch page's contents
				pSgst.downloadpdf(ItemList.get(i),PATH);
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
	}
}
