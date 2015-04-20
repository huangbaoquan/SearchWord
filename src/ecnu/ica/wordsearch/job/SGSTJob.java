package ecnu.ica.wordsearch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import ecnu.ica.wordsearch.task.SGSTTask;
import ecnu.ica.wordsearch.util.TheCrawlerUtil;

/**
 *@ClassName   : SGSTJob.java
 *@Package     : ecnu.ica.wordsearch.job
 *@Author      : baoquanhuang 
 *@Email       : baoquanhuang@gmail.com
 *@Date        : 2015年4月10日上午11:36:01
 *@Description : TODO
 */
public class SGSTJob extends Job{
	/**
	 * @Fields THREADCOUNT :  Represent Thread Numbers
	 */
	final private static int THREADCOUNT = 10;
	private static final Logger logger = Logger.getLogger(SGSTJob.class);
	private String PATH;
	public ArrayList<String> ItemList;
	private ExecutorService myService;
	private static long startTime,endTime;
	
	public SGSTJob(String path,ArrayList<String> ItemList) {
		this.PATH = path;
		this.ItemList = ItemList;
	}
	@Override
	public void excute() {
		try 
		{
			//Start THREADCOUNT Thread To Run
			//Using Thread Pool To Do This Work
			Date startDate = new Date();
			startTime = startDate.getTime();
			System.out.println(startTime);
			
			myService = Executors.newFixedThreadPool(THREADCOUNT);
			for(int i = 0;i < THREADCOUNT;i++)
			{
				//new Thread(sTask).run();
				myService.execute(new SGSTTask(this.PATH, this.ItemList,i+1));
			}
			//myService.execute(new SGSTTask(this.PATH, this.ItemList));
			myService.shutdown();
			
			Date endDate = new Date();
			endTime = endDate.getTime();
			System.out.println(endTime);
			
			logger.info(TheCrawlerUtil.GetTimeSpan(startTime, endTime));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
}
