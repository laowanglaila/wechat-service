package com.hualala.app.wechat.sdk.mp.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileUtil {
	
	private static Logger logger= LoggerFactory.getLogger(FileUtil.class);

	//文件上传 
	public static String uploadFile(String filePath){
	    
		if(filePath == null)
			return null;
		
		File file=new File(filePath);
		
		return uploadFile(file);
	}
	
	public static String uploadFile(File file){
		
		Map map = uploadfile(file);
		
		if(map == null)
			return null;
		
		return String.valueOf(map.get("url"));
		
	}
	
	public static Map uploadfile(File file){
	    
		HttpResponse response = getHttpResponse(file);
		
		if(response == null)
			return null;
		
		Map result = null;
		
		try{
			HttpEntity httpEntity=response.getEntity();
			InputStream is=httpEntity.getContent();
			int code = response.getStatusLine().getStatusCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String resultUrl="http://res.hualala.com/"+br.readLine();
			br.close();
			result = new HashMap();
			result.put("code", code);
			result.put("url", resultUrl);
			logger.info("---map---"+result);
		}catch(Exception e){
			logger.error("---FileUpload1---",e);
		}
		
		return result;
	}
	
	public static HttpResponse getHttpResponse(File file){
		
		if(file == null || !file.exists())
			return null;
		
		String url="http://file.hualala.com/upload!mobileClient.action";
		
		HttpResponse response = null;
		HttpClient httpClient=null;
		
		try{
			httpClient=new DefaultHttpClient();
			FileBody fileBody=new FileBody(file);
			MultipartEntity entity=new MultipartEntity();
			entity.addPart("upload",fileBody);
			HttpPost post=new HttpPost(url);
			post.setEntity(entity);
			response = httpClient.execute(post);
		}catch(Exception e){
			logger.error("---FileUpload---",e);
		}finally{
			if(httpClient != null){
				if(httpClient.getConnectionManager() != null){
					httpClient.getConnectionManager().shutdown();
				}
			}
		}
		
		return response;
		
	}
	
	//文件下载
	public static File downloadFile(String url,String filePath){
		URL u =null;
		HttpURLConnection connection=null;
		File file=null;
		try{
			u=new URL(url);
			connection = (HttpURLConnection)u.openConnection();
			connection.getDoInput();
			connection.getDoOutput();
			InputStream is=connection.getInputStream();
			file=new File(filePath);
			file.createNewFile();
			OutputStream os=new FileOutputStream(file);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=is.read(buffer))!=-1){
				os.write(buffer,0,length);
			}
			os.flush();
			os.close();
			is.close();
		}catch(Exception e){
			logger.error("---FileDownload---",e);
		}
		return file;
	}
	
	public static File downloadFile(String url,String filePath ,int setConnectTimeout, int ReadTimeout){
		URL u =null;
		HttpURLConnection connection=null;
		File file=null;
		try{
			u=new URL(url);
			connection = (HttpURLConnection)u.openConnection();
			connection.setConnectTimeout(setConnectTimeout);  
			connection.setReadTimeout(ReadTimeout); 
			connection.getDoInput();
			connection.getDoOutput();
			InputStream is=connection.getInputStream();
			file=new File(filePath);
			file.createNewFile();
			OutputStream os=new FileOutputStream(file);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=is.read(buffer))!=-1){
				os.write(buffer,0,length);
			}
			os.flush();
			os.close();
			is.close();
		}catch(Exception e){
			logger.error("---FileDownload---",e);
		}
		return file;
	}
    
    //获取图片宽高
    
    public static Map<String,Object> getImageInfo(File file){
    	
    	if(file == null || !file.exists())
    		return null;
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	int imgWidth = 1;
		int imgHeight = 1;
		
    	try{
    		
    		BufferedImage bi = ImageIO.read(file);
    		
    		imgWidth = bi.getWidth();
    		
    		imgHeight = bi.getHeight();
    		
    	}catch(Exception e){
    		
    		try{
    			ImageInputStream input = ImageIO.createImageInputStream(file);
                
        		Iterator readers = ImageIO.getImageReaders(input); 
                
        		ImageReader reader = null;  
                
        		while (readers.hasNext()) {  
                    
        			reader = (ImageReader) readers.next();  
                    
        			if (reader.canReadRaster()) {  
                        break;  
                    }  
                }  
        		
        		if(reader != null){
        			
        			reader.setInput(input); 
            		
            		Raster raster = reader.readRaster(0, null);
            		
            		imgWidth = raster.getWidth();
            		
            		imgHeight = raster.getHeight();
        		}
    		
    		}catch(Exception e1){
    			
    		}
    	}
    	
    	
    	double imgHWP=new BigDecimal(imgHeight).divide(new BigDecimal(imgWidth),2,RoundingMode.HALF_DOWN).doubleValue();
    	
    	map.put("imgWidth", imgWidth);
    	
    	map.put("imgHeight", imgHeight);
    	
    	map.put("imgHWP", imgHWP);
    	
    	return map;
    }
}
