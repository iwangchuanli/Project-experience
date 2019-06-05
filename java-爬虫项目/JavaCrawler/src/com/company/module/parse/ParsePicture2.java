package com.company.module.parse;

import com.company.bean.PictureBean;
import com.company.module.RequestThread;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 解析器
 */
public class ParsePicture2 extends BaseParse<List<PictureBean>, PictureBean> {
	private static final String ROOT_URL = "http://www.mmjpg.com";

	public static void main(String[] a) throws Exception {

	}

	@Override
	public int getPager(String html) {
		Elements div = Jsoup.parse(html).getElementsByClass("page");
		Elements a = div.select("a");
		if(a.size()==0)
			return 1;
		String pager = a.get(a.size() - 1)
				.attr("href")
				.replaceAll("\\D", "");
		return pager == null || pager.isEmpty() ? 1 : Integer.valueOf(pager);
	}

	@Override
	public List<PictureBean> getMessage(String html) {
		List<PictureBean> data = new ArrayList<>();
		Elements li = Jsoup.parse(html).getElementsByClass("pic").select("li");
		for (Element e : li) {
			Element a = e.selectFirst("a");
			String title = a.selectFirst("img").attr("alt");
			String url = a.attr("href");
			PictureBean bean = new PictureBean(title, url);
			data.add(bean);
		}
		return data;
	}

	@Override
	public synchronized String getDetail(PictureBean obj) {
		String url = obj.getPictureList().get(0);
		//初始化参数
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		//设置支持AJAX特TM重要的
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(5000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getOptions().setUseInsecureSSL(true);
		try {
			HtmlPage page = webClient.getPage(url);

			//如果第一层（getMessage（））没有得到标题那么再这里重新获取
			if(obj.getTitle()==null || obj.getTitle().isEmpty()){
				List<HtmlElement> list = page.getBody().getElementsByAttribute("div", "class", "article");
				String title = list.get(0).getElementsByTagName("h2").get(0).asText();
				obj.setTitle(title);
			}
			HtmlElement element = page.getHtmlElementById("opic");
			/*
			这里是执行JavaScript的代码
			String clickAttribute = element.getOnClickAttribute();
			ScriptResult result = page.executeJavaScript(clickAttribute);
			HtmlPage page1 = (HtmlPage) result.getNewPage();*/
			HtmlPage page1 = element.click();
			List<HtmlElement> content = page1
					.getBody()
					.getElementsByAttribute("div", "class", "content")
					.get(0)
					.getElementsByTagName("img");
			for (HtmlElement h : content) {
				String src = h.getAttribute("src");
				obj.getPictureList().add(src);
			}
		} catch (IOException e) {
			System.err.println("出错:" + e.getMessage());
			return null;
		} finally {
			webClient.close();
		}
		if (obj.getPictureList().size() == 0)
			return null;
		return "SUCCESS";

	}

	@Override
	public String switchPager(String url, int index) {
		//http://www.mmjpg.com/home/20
		String path = url.substring(0, url.lastIndexOf("/") + 1);
		if (index == 1)
			return url;
		return path + index;
	}

	@Override
	public void execute() {
		ExecutorService service = getExecutorService();
		service.execute(new RequestThread(this, "http://www.mmjpg.com/hot/"));
		//service.execute(new RequestThread(this, ROOT_URL));
		service.shutdown();
	}
}
