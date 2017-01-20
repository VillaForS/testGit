package com.maiyajf.base.utils.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import com.maiyajf.base.utils.log.DebugLogger;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/**
 * =========================================================
 * 
 * 文件： .FtpTest.java
 * 
 * 所含类: FtpTest.java
 * 
 * 修改记录： 日期 作者 内容 ========================================================= Jul
 * 4, 2011 zhangdeng 创建文件，实现基本功能
 */
@SuppressWarnings("restriction")
public class FtpUtils {

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件
	 * @param serverPath
	 *            上传的路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public boolean uploadToFtpServer(File file, String serverPath,
			String fileName) {
		String serverUrl = "192.168.1.101";
		String userName = "frbao";
		String passWord = "frbao";
//		List<Parameters> lp = GrpParaUtil.get(GrpParaUtil._FTPPROPERTIES);
//		for (int i = 0; i < lp.size(); i++) {
//			if (i == 0) {
//				serverUrl = lp.get(i).getSvalue();
//			} else if (i == 1) {
//				userName = lp.get(i).getSvalue();
//			} else {
//				passWord = lp.get(i).getSvalue();
//			}
//		}
		try {
			//FtpClient ftpClient = new FtpClient();// 打开ftp客户端(由于jdk1.7修改FtpClient为私有  故此方法只可以在jdk1.6中使用)
			FtpClient ftpClient=FtpClient.create(serverUrl);//此为jdk1.7创建ftp连接
			// 登录到Ftp服务器
			loginToFtpServer(ftpClient, serverUrl,
					userName, passWord);
			boolean isOpenSuccess = ftpClient.isLoggedIn();
			if (isOpenSuccess) {// 以下几步顺序不能错
				createDir(ftpClient, serverPath);
				ftpClient.changeDirectory(serverPath);
				
//				String os = SysParaUtil.get(SysParaUtil.FTPOS);
//				if ("centos".equals(os)) {
//					ftpClient.changeDirectory(ftpClient.getWorkingDirectory()+serverPath);
//				} else {
//					ftpClient.changeDirectory(serverPath);
//				}
				//OutputStream outputStream = getFtpClientOutputStream(ftpClient,
				//		fileName);
				InputStream inputStream = getLocalFileInputStream(file);
				//writeToFtpServer(outputStream, inputStream);
				ftpClient.putFile(fileName, inputStream);
				close(ftpClient, null, inputStream);// 关闭
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 检查文件夹在当前目录下是否存在
	 * 
	 * @param dir
	 * @return
	 */
	private boolean isDirExist(FtpClient ftpClient, String dir) {
		String pwd = "";
		try {
			pwd = ftpClient.getWorkingDirectory();
			ftpClient.changeDirectory(dir);
			ftpClient.changeDirectory(pwd);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 在当前目录下创建文件夹
	 * 
	 * @param dir
	 * @return
	 * @throws FtpProtocolException 
	 * @throws Exception
	 */
	private boolean createDir(FtpClient ftpClient, String dir) throws FtpProtocolException {
		try {
			ftpClient.setAsciiType();
			StringTokenizer s = new StringTokenizer(dir, "/"); // sign
			s.countTokens();
			String pathName=ftpClient.getWorkingDirectory();
			while (s.hasMoreElements()) {
				pathName = pathName + "/" + (String) s.nextElement();
				if (!isDirExist(ftpClient, pathName)) {
					try {
						ftpClient.makeDirectory(pathName);
					} catch (Exception e) {
						e.printStackTrace();
						e = null;
						return false;
					}
					ftpClient.getLastResponseString();
				}
			}
			ftpClient.setBinaryType();
			return true;
		} catch (IOException e1) {
			// e1.printStackTrace();
			return false;
		}
	}

	/** 登录到Ftp服务器 
	 * @throws FtpProtocolException */
	public boolean loginToFtpServer(FtpClient ftpClient, String serverUrl,
			String userName, String passWord) throws FtpProtocolException {
		try {
			/*ftpClient.openServer(serverUrl);
			ftpClient.login(userName, passWord); 在1.7中要修改为下面的方法*/
			ftpClient.login(userName, passWord.toCharArray());   
			return true;
		} catch (IOException e) {
			DebugLogger.debug("Ftp 客户端打开失败");
			return false;
		}
	}

	/** 得到ftp客户端的输出流 
	 * @throws FtpProtocolException */
	public OutputStream getFtpClientOutputStream(FtpClient ftpClient,
			String fileName) throws IOException, FtpProtocolException {
		//ftpClient.binary();此为jdk1.6方法 以下为jdk1.7方法
		ftpClient.setBinaryType();  
		return ftpClient.putFileStream(fileName,true);//ftpClient.put(fileName);此为jdk1.6方法 以下为jdk1.7方法
	}

	/** 得到ftp客户端的输出流 
	 * @throws FtpProtocolException */
	public InputStream getFtpClientInputStream(FtpClient ftpClient,
			String downLoadFile) throws IOException, FtpProtocolException {
		//ftpClient.binary();
		ftpClient.setBinaryType(); 
		File file = new File(downLoadFile);
		return ftpClient.getFileStream(file.getName());
	}

	/** 得到本地文件的输入流 */
	public InputStream getLocalFileInputStream(File file)
			throws FileNotFoundException {
		return new FileInputStream(file);
	}

	/** 向ftp服务器写入文件 */
	public void writeToFtpServer(OutputStream outputStream,
			InputStream inputStream) throws IOException {
		byte[] bytes = new byte[1024];
		while (inputStream.read(bytes) != -1) {
			outputStream.write(bytes, 0, bytes.length);
		}
		outputStream.flush();
	}

	/** 下载文件到本地 */
	public void writeToLocal(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		byte[] bytes = new byte[1024];
		while (inputStream.read(bytes) != -1) {
			outputStream.write(bytes, 0, bytes.length);
		}
	}

	/** 关闭 */
	public void close(FtpClient ftpClient, OutputStream outputStream,
			InputStream inputStream) throws IOException {
		if (inputStream != null)
			inputStream.close();
		if (outputStream != null)
			outputStream.close();
		if (ftpClient != null)
			ftpClient.close();
	}
	
	public static void main(String[] args) {
//		FtpTest i=new FtpTest();

		String serverUrl="192.168.1.162";
		try {
			//FtpClient ftpClient = new FtpClient();// 打开ftp客户端(由于jdk1.7修改FtpClient为私有  故此方法只可以在jdk1.6中使用)
			FtpClient ftpClient=FtpClient.create(serverUrl);//此为jdk1.7创建ftp连接
			// 登录到Ftp服务器
			ftpClient.login("maiya", "maiya@#2015".toCharArray());
		
			boolean isOpenSuccess = ftpClient.isLoggedIn();
			if (isOpenSuccess) {// 以下几步顺序不能错
				try {
					ftpClient.makeDirectory("t1");
				} catch (IOException e1) {
					// e1.printStackTrace();

				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
