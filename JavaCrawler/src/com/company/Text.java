package com.company;


import com.company.module.HttpUtil;
import okhttp3.Headers;
import okhttp3.Response;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Text {
	public static void main(String[] a)throws Exception {
		Response response = HttpUtil.doGet("http://img.mmjpg.com/2018/1254/47.jpg",null).execute();
		Headers responseHeaders = response.headers();
		int responseHeadersLength = responseHeaders.size();
		for (int i = 0; i < responseHeadersLength; i++){
			String headerName = responseHeaders.name(i);
			String headerValue = responseHeaders.get(headerName);
			System.out.print("TAG----------->Name:"+headerName+"------------>Value:"+headerValue+"\n");
		}
		if (response.isSuccessful()) {
			InputStream inputStream = response.body().byteStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			FileOutputStream fis = new FileOutputStream(new File("E://1.jpg"));
			byte[] buf = new byte[1024 * 1000];
			int len;
			while ((len = bis.read(buf)) != -1) {
				fis.write(buf, 0, len);
			}
			fis.flush();
			fis.close();
			bis.close();
			response.close();
		}else{
			System.out.println(response.code());
		}
	}
}
