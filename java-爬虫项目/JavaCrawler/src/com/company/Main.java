package com.company;

import com.company.bean.PictureBean;
import com.company.module.HttpUtil;
import com.company.module.parse.ParsePicture2;
import okhttp3.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<PictureBean> DATA = new ArrayList<>();

	public static void main(String[] args) throws Exception {
//		new ParsePicture1().execute();
		new ParsePicture2().execute();

		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		/**
		 * 输入任意exit退出程序
		 * 输入do 开始下载图片到本地
		 * 输入其他查看当前爬取了多少条目
		 */
		while (!in.equals("exit")) {
			System.out.println("总条数:" + DATA.size());
			if (in.equals("do"))
				downloadImage();
			in = sc.nextLine();
		}

	}

	protected volatile static int currentCount = 0;
	protected static int count = 0;

	/**
	 * 开始下载到本地
	 */
	public static void downloadImage() {
		//ExecutorService pool = Executors.newCachedThreadPool();
		for (PictureBean bean : DATA) {
			count += bean.getPictureList().size() - 1;
		}
		File root = new File("E://download1");  //下载的路径
		if (!root.exists())
			root.mkdir();
		for (PictureBean bean : DATA) {
			File file = new File(root, bean.getTitle());
			if (!file.exists())
				file.mkdir();
			for (int i = 1; i < bean.getPictureList().size(); i++) {
				int index = i;
				new Thread(() -> {
					Response response = null;
					InputStream inputStream = null;
					BufferedInputStream bis = null;
					FileOutputStream fis = null;
					try {
						inputStream = HttpUtil.getStream(bean.getPictureList().get(index),
								bean.getPictureList().get(0));
							bis = new BufferedInputStream(inputStream);
							fis = new FileOutputStream(new File(file, index + ".jpg"));
							byte[] buf = new byte[1024 * 1000];
							int len;
							while ((len = bis.read(buf)) != -1) {
								fis.write(buf, 0, len);
							}
							fis.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						currentCount++;
						System.out.println("共计任务：" + count + "\t已下载：" + currentCount);
						if (currentCount == count) {
							System.out.println("下载完成！ SUCCESS");
						}
						try {
							if (inputStream != null)
								inputStream.close();
							if (fis != null)
								fis.close();
							if (bis != null)
								bis.close();
							if (response != null)
								response.close();

						} catch (Exception e) {
						}
					}
				}).start();
			}
		}
	}
}
