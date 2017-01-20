package com.maiyajf.base.security.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单向加密算法
 * 
 * @author ck
 *
 */
public class EncrypSHA {

	public byte[] eccrypt(String info) throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance("SHA");
		byte[] srcBytes = info.getBytes();
		// 使用srcBytes更新摘要
		sha.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = sha.digest();
		return resultBytes;
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String msg = "麦芽xiaodai-测试";
		EncrypSHA sha = new EncrypSHA();
		byte[] resultBytes = sha.eccrypt(msg);
		System.out.println("明文是：" + msg);
		System.out.println("密文是：" + new String(resultBytes));

	}

}
