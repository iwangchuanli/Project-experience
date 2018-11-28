package com.company.module.parse;

import com.company.bean.PictureBean;
import com.company.module.HttpUtil;
import com.company.module.RequestThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片解析器
 */
public class ParsePicture1 extends BaseParse<List<PictureBean>, PictureBean> {
	private static String ROOT_URL = "http://s2.91sgc.rocks/pw/thread.php?fid=14&page=1";

	public static void main(String[] a) throws Exception {

	}

	@Override
	public int getPager(String html) {
		Document doc = Jsoup.parse(html);
		Elements pages = doc.getElementsByClass("pages");
		String[] s = pages.text().split("/");
		for (String str : s) {
			if (str.contains("total")) {
				String p = str.substring(0, str.indexOf("total"));
				String pager = p.replaceAll("\\D", "");
				return Integer.valueOf(pager);
			}
		}
		return -1;
	}

	@Override
	public List<PictureBean> getMessage(String html) {
		String root = "http://s2.91sgc.rocks/pw/";
		List<PictureBean> data = new ArrayList<>();
		Elements table = Jsoup.parse(html).getElementsByTag("table");
		Elements tr = table.select("tr");
		boolean state = false;
		for (int i = 0; i < tr.size(); i++) {
			if (!state) {
				String text = tr.get(i).toString();
				if (text != null && (text.indexOf("普通主题") != -1 || text.indexOf("文章")!=-1)) {
					state = true;
				}
			} else {
				Element h3 = tr.get(i).selectFirst("h3");
				if (h3 != null) {
					Element a = h3.selectFirst("a");
					if (a != null) {
						String url = root + a.attr("href");
						if (url != null) {
							PictureBean pictureBean = new PictureBean(a.text(), url);
							data.add(pictureBean);
						}
					}
				}
			}
		}
		return data;
	}

	@Override
	public String getDetail(PictureBean obj) {
		try {
			String url = obj.getPictureList().get(0);
			obj.getPictureList().clear();
			Elements div = Jsoup.parse(HttpUtil.doGetForResult(url)).getElementsByClass("tpc_content");
			Elements img = div.select("img");
			for (Element i : img) {
				String path = i.attr("src");
				if (path != null) {
					obj.getPictureList().add(path);
				}
			}
		} catch (Exception e) {
			return null;
		}
		if(obj.getPictureList().size()==0)
			return null;
		return "SUCCESS";
	}

	@Override
	public String switchPager(String url, int index) {
		return "http://s2.91sgc.rocks/pw/thread.php?fid=14&page=" + index;
	}

	@Override
	public void execute() {
		new RequestThread(this, ROOT_URL).start();
	}
}
