package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 */
public class DownloadUtil {
	
    /**
     * 下载指定路径下的文件
     */
    public void downloadLocal(String path,String fileName,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
    // 读到流中
    InputStream inStream = new FileInputStream(path + fileName);// 文件的存放路径
    String fileName_down = "";
    String userAgent = request.getHeader("user-agent").toLowerCase();

    if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {
            // win10 ie edge 浏览器 和其他系统的ie
    	try {
    		fileName_down = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } else {
            // fe
    	try {
    		fileName_down = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    // 设置输出的格式
    response.reset();
    response.setContentType("bin");
    response.addHeader("Content-Disposition", "attachment; filename=" + fileName_down);
    // 循环取出流中的数据
    byte[] b = new byte[100];
    int len;
    try {
    while ((len = inStream.read(b)) > 0)
    response.getOutputStream().write(b, 0, len);
    inStream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
   
}
