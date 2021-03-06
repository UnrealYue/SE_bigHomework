package com.iL.hotel.common.encrypt;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
* MD5操作工具类
*
*/
public class MD5Encrypt {

	/* 16进制字符*/
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/* 指定算法为MD5的MessageDigest */
	private static MessageDigest messageDigest = null;

	/* 初始化messageDigest的加密算法为MD5 */
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 获取文件的MD5加密<br/>
	 * 
	 * @param file
	 *            目标文件
	 * @return String MD5字符串
	 * @throws IOException
	 * */
	public static String getFileMD5String(File file) throws IOException {
		String ret = "";
		FileInputStream in = null;
		FileChannel ch = null;
		try {
			in = new FileInputStream(file);
			ch = in.getChannel();
			ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
					file.length());
			messageDigest.update(byteBuffer);
			ret = bytesToHex(messageDigest.digest());
		} finally {
			if (in != null)
				in.close();
			if (ch != null)
				ch.close();
		}
		return ret;
	}

	/**
	 * <p>
	 * 获取字符串的MD5加密<br/>
	 * 
	 * @param str
	 *            目标字符串
	 * @return String MD5加密后的字符串
	 **/
	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	/**
	 * <p>
	 * 获取byte数组的MD5加密<br/>
	 * 
	 * @param bytes
	 *            目标byte数组
	 * @return String MD5加密后的字符串
	 * */
	public static String getMD5String(byte[] bytes) {
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}

	/**
	 * <p>
	 * 校验密码与其MD5是否一致<br/>
	 * 
	 * @param pwd
	 *            密码字符串
	 * @param md5
	 *            基准MD5串
	 * @return boolean 比对结果
	 **/
	public static boolean checkPassword(String pwd, String md5) {
		return getMD5String(pwd).equalsIgnoreCase(md5);
	}

	/**
	 * <p>
	 * 校验密码与其MD5是否一致<br/>
	 * 
	 * @param pwd
	 *            以字符数组表示的密码
	 * @param md5
	 *            基准MD5串
	 * @return boolean 比对结果
	 * */
	public static boolean checkPassword(char[] pwd, String md5) {
		return checkPassword(new String(pwd), md5);

	}

	/**
	 * <p>
	 * 比对文件与其MD5是否一致<br/>
	 * 
	 * @param file
	 *            目标文件
	 * @param md5
	 *            基准MD5串
	 * @return boolean 比对结果
	 * @throws IOException
	 * */
	public static boolean checkFileMD5(File file, String md5)
			throws IOException {
		return getFileMD5String(file).equalsIgnoreCase(md5);

	}

	/**
	 * <p>
	 * 将字节数组转换成16进制字符串<br/>
	 * 
	 * @param bytes
	 *            目标字节数组
	 * @return String 转换结果
	 * */
	public static String bytesToHex(byte bytes[]) {
		return bytesToHex(bytes, 0, bytes.length);

	}

	/**
	 * <p>
	 * 将字节数组中指定区间的子数组转换为16进制字符串<br/>
	 * 
	 * @param bytes
	 *            目标字节数组
	 * @param start
	 *            起始位置（包括该位置）
	 * @param end
	 *            结束位置（不包括该位置）
	 * @return String 转换结果
	 * */
	public static String bytesToHex(byte bytes[], int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + end; i++) {
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();

	}


	/**
	 * <p>
	 * 将单个字节码转换为16进制字符串<br/>
	 * 
	 * @param bt
	 *            目标字节
	 * @return String 转换结果
	 * */
	public static String byteToHex(byte bt) {
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];

	}

	
	public static String encryptAddSalt(String password,Long timeMillis){
		String firstMd5 = getMD5String((password+timeMillis).getBytes());
		System.out.println(firstMd5);
		firstMd5 = firstMd5.substring(25, 32) + firstMd5.substring(0, 25);
		System.out.println(firstMd5);
		return getMD5String(firstMd5.getBytes());
	}
	
	public static String encrypt(String username,String password){
		String firstMd5 = getMD5String((username+password).getBytes()).toLowerCase();
		System.out.println(firstMd5);
		firstMd5 = firstMd5.substring(8, 32) + firstMd5.substring(0, 8) ;
		System.out.println(firstMd5);
		return getMD5String(firstMd5.getBytes()).toLowerCase();
	}
}
