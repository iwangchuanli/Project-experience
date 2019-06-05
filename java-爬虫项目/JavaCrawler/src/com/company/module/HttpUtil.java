package com.company.module;


import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
			"AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

	public static Call doGet(String url, String referer) {
		if (url == null || url.isEmpty()) return null;
		Headers.Builder builder = new Headers.Builder();
		builder.add("User-Agent",agent);
		if (referer != null)
			builder.add("Referer", referer);
		Request request = new Request.Builder()
				.url(url)
				.headers(builder.build())
				.build();
		return OkHttp.getInstance().newCall(request);
	}

	public static Call doGet(String url) {
		if (url == null || url.isEmpty()) return null;
		Request request = new Request.Builder()
				.url(url)
				.addHeader("User-Agent",agent)
				.build();
		return OkHttp.getInstance().newCall(request);
	}

	/**
	 * 直接获得结果 使用Get的方式
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doGetForResult(String url) throws IOException {
		Response response = doGet(url,null).execute();
		String string = response.body().string();
		response.close();
		return string;
	}


	/**
	 * Post请求
	 *
	 * @param requestBody 传递的参数
	 * @param url         请求的地址
	 * @return
	 */
	public static Call doPost(RequestBody requestBody, String url) {
		OkHttpClient okHttpClient = OkHttp.getInstance();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.addHeader("user-Agent", agent)
				.build();
		return okHttpClient.newCall(request);
	}

	/**
	 * 通过Url 获取一个输入流
	 * @param url
	 * @param referer
	 * @return
	 */
	public static InputStream getStream(String url, String referer){
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(5000);
			con.setDoInput(true);
			con.addRequestProperty("Referer",referer);
			return con.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
