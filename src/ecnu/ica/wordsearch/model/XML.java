package ecnu.ica.wordsearch.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *@Author : baoquan Huang 
 *@Email  : baoquanhuang@gmail.com
 *@Date   : 2014年12月18日下午1:25:48
 *@Description : TODO
 */
public class XML extends CreateFloderFile{
	private final static Logger LOG = Logger.getLogger(XML.class);
	/*private String JDItemInfo;
	private String price ;
	private String productIntro;
	private String productPara;*/

	private static void writeXML(OutputStream outputStream,Document doc) 
	{
		XMLOutputter out = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		format.setEncoding("UTF-8");
		format.setIndent("    ");
		out.setFormat(format);
		try 
		{
			out.output(doc, outputStream);
		} catch (IOException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
	}
	
	public static void CreateXMLFileAndWrite(String path,Document doc,String filename)
	{
		if(doc != null)
		{
			try 
			{
				String FileName = filename;
				File file = new File(path + File.separator + FileName + ".xml");
				file.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				writeXML(fileOutputStream, doc);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.toString());
			}
		}
	}
}
