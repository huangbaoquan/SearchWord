package ecnu.ica.wordsearch.job;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ecnu.ica.wordsearch.task.SGSTTask;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年4月8日下午2:07:23
 *@Description : TODO
 */
public class SGSTJob extends Job{

	final private static int THREADCOUNT = 10;
	private static final Logger logger = Logger.getLogger(SGSTJob.class);
	private String PATH;
	public ArrayList<String> ItemList;
	
	public SGSTJob(String path,ArrayList<String> ItemList) {
		this.PATH = path;
		this.ItemList = ItemList;
	}
	@Override
	public void excute() {
		try 
		{
			//start THREADCOUNT thread to run
			SGSTTask sTask = new SGSTTask(this.PATH, this.ItemList);
			for(int i = 0;i < THREADCOUNT;i++)
			{
				new Thread(sTask).run();
			}
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
}
