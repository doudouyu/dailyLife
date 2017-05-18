package life.bean.com.beanlife.gesture;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class MD5EncodeUtil {
	public static String MD5Ecode(String gesture){
		Log.i("GYL","gesture---"+gesture.toString());
		byte[] toencode=gesture.toString().getBytes();
		Log.i("GYL","byte[]----"+toencode.toString());
		
		try{
			MessageDigest md5=MessageDigest.getInstance("MD5");		
			md5.reset();
			md5.update(toencode);
			return HexEncode(md5.digest());
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return "";
	}
	public static String HexEncode(byte[] toencode){
		StringBuilder sb=new StringBuilder(toencode.length*2);
		for(byte b:toencode){
			sb.append(Integer.toHexString((b&0xf0)>>>4));
			sb.append(Integer.toHexString(b&0x0f));			
		}
		return sb.toString().toUpperCase();
	}
}
