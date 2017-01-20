package com.maiyajf.base.utils.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.maiyajf.base.exception.ServiceException;

public class UploadUtils {
	/**
	 * 文件读写
	 * 
	 * @param file
	 *            读入的文件
	 * @param targetPath
	 *            写入目标路径
	 */
	public static void read(File file, String targetPath) {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			FileInputStream in = new FileInputStream(file);
			FileOutputStream out = new FileOutputStream(targetPath);
			inBuff = new BufferedInputStream(in);
			outBuff = new BufferedOutputStream(out);
			byte[] b = new byte[in.available()];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceException("上传文件没有找到！");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("上传文件出现异常！");
		} finally {
			try {
				// 关闭流
				if (inBuff != null)
					inBuff.close();
				if (outBuff != null)
					outBuff.close();
			} catch (IOException e2) {
				e2.printStackTrace();
				throw new ServiceException("上传文件读写出错！");
			}
		}
	}
}
