package cn.eqianyuan.erp.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	/**
	 * 私有构造函数，不允许本类生成实例
	 */
	private Md5Util(){}

	public static void main(String args[]) {
		String pwd = "admin123@xyw";
//		B5161CB311C28108

		try {
			System.out.println(MD5By32(pwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * md532位加密
	 * @param s
	 * @return
     */
	public final static String MD5By32(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
