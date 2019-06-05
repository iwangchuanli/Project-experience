package com.company.module;


import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OkHttp {
	private static class Get{
		private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
				.readTimeout(10, TimeUnit.SECONDS)
				.connectTimeout(30,TimeUnit.SECONDS)
				.build();
	}

	public static OkHttpClient getInstance(){
		return Get.OK_HTTP_CLIENT;
	}
}
