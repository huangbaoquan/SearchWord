package ecnu.ica.wordsearch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.naming.ConfigurationException;

import org.apache.log4j.Logger;

public class Configuration {

	private final static Logger LOGGER = Logger.getLogger(Configuration.class);
	private static Properties config;// 记录配置项
	private static final String PROPERTIES_FILE_NAME = System.getProperty("user.dir")+"\\config\\conf.properties";
	
	public Configuration() {
	}

	public Configuration(String fileName) throws ConfigurationException 
	{
		try 
		{
			config = new Properties();
			FileInputStream fin = new FileInputStream(fileName);
			config.load(fin); // 载入文件
			fin.close();
		} catch (IOException ex) {
			throw new ConfigurationException("无法读取指定的配置文件:" + fileName);
		}
	}
	
	public String getValue(String itemName) 
	{
		return config.getProperty(itemName);
	}

	public void setValue(int value)
	{
		if(config.isEmpty())
		{
			LOGGER.error("Configure config is null");
		}
		else
		{
			config.setProperty("NumPage", value+"");
		}
		//storage file
		try 
		{
			//get the config file
			FileOutputStream fos = new FileOutputStream(new File(PROPERTIES_FILE_NAME));
            config.store(fos, "the Crawler Page Numbers");
            fos.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) throws ConfigurationException {
		String filename = System.getProperty("user.dir") + "\\config\\conf.properties";
		System.out.println(filename);
		Configuration conf = new Configuration(filename);
//		System.out.println(conf.getValue("NumPage"));
		conf.setValue(1000);
	}*/
}