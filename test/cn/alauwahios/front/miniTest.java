package cn.alauwahios.front;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class miniTest {
	
	public static void main(String args[]) {
		String url = "%E8%A5%BF%E9%97%A8%E5%A4%A7%E5%AB%82";
		try {
			String dec = URLDecoder.decode(url, "utf-8");
			System.out.println(URLEncoder.encode(dec, "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
