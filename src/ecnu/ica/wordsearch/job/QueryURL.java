package ecnu.ica.wordsearch.job;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2015年1月12日上午10:11:57
 *@Description : TODO
 */
public class QueryURL {
	private static final Logger LOG = Logger.getLogger(QueryURL.class);

	
	/**
	 * For control each thread safe use public URL coresponding
	 * @param urlList
	 * @param popCount
	 * @return
	 */
	public static ArrayList<String> queryUrl(ArrayList<String> urlList,int popCount)
	{
		try 
		{
			ArrayList<String> list = new ArrayList<>();
			synchronized (urlList) 
			{
				while(popCount > 0)
				{
					if(urlList.size() <= 0)
					{
						break;
					}
					list.add(urlList.get(0));
					urlList.remove(0);
					popCount--;
				}
				return list;
			}
		} catch (Exception e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		for(int i=1 ; i<50 ;i++)
		{
			a.add(i+"");
		}
		//QueryURL.queryUrl(a, 10);
		ArrayList<String> b = a;
		Thread t1 = new Thread();
	}*/
}
