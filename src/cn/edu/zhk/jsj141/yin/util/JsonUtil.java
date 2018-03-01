package cn.edu.zhk.jsj141.yin.util;

import java.io.UnsupportedEncodingException;

public class JsonUtil {
	public static String urlToJson(String url) {
		int sum = 0;
		int count = 0;
		String data = "";

		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		// 计算出字符串里有多少个&符号
		for (int i = 0; i < url.length(); i++) {
			if ("&".equals(url.substring(i, i + 1))) {
				sum++;
			}
		}
		String a[] = url.split("[&]");// 以&符号分隔
		// 将分隔出来的字符串加上，
		for (int i = 0; i < sum + 1; i++) {
			sb.append(a[i] + ",");
		}
		// 以=分隔
		String[] c = sb.toString().split("[=]");
		// 将分隔出来的字符串加上，
		for (int j = 0; j < c.length; j++) {
			sb2.append(c[j] + ",");
		}
		// 去掉最后面两个,
		String result = sb2.toString().substring(0, sb2.length() - 2);
		// 计算出字符串里有多少个,符号
		for (int i = 0; i < result.length(); i++) {
			if (",".equals(result.substring(i, i + 1))) {
				count++;
			}
		}
		// 以，分隔
		String[] d = result.toString().split("[,]");
		for (int i = 0; i < count + 1; i++) {
			if (i % 2 != 0) {// 字符下标为奇数时加上，
				data += "\"" + d[i] + "\",";
			} else {
				data += "\"" + d[i] + "\":";
			}
		}
		// 拼接json格式
		String jsonArray = "{" + data.substring(0, data.length()-1) + "}";
		 try {
			 jsonArray = java.net.URLDecoder.decode(jsonArray, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
}
