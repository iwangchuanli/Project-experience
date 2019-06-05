package com.company.module;

import com.company.Main;
import com.company.module.parse.BaseParse;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络请求
 */
public class RequestThread extends Thread {
	private BaseParse mParse;
	private String mUrl;
	private SimpleDateFormat simpleDateFormat;

	public RequestThread(BaseParse parse, String url) {
		this.mParse = parse;
		this.mUrl = url;
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	}

	@Override
	public void run() {
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void execute() throws Exception {
		int pagerCount = mParse.getPager(HttpUtil.doGetForResult(mUrl));
		System.out.println("总页数："+pagerCount);
		for (int i = 1; i <= pagerCount; i++) {
			String currentUrl = mParse.switchPager(mUrl, i);
			Observable.create((ObservableOnSubscribe<List>) observableEmitter -> {
				List message = mParse.getMessage(HttpUtil.doGetForResult(currentUrl));
				if (message.size() != 0) {
					observableEmitter.onNext(message);
					observableEmitter.onComplete();
				} else {
					observableEmitter.onError(new NullPointerException("长度为0,连接："+currentUrl));
				}
			})
					.map(ts -> {
						List data = new ArrayList();
						for (Object s : ts) {
							String detail = mParse.getDetail(s);
							if(detail!=null)
								data.add(s);
						}
						return data;
					})
					.subscribeOn(Schedulers.io())
					.subscribe(new Observer<List>() {
						@Override
						public void onSubscribe(Disposable disposable) {

						}

						@Override
						public void onNext(List ts) {
							String date = simpleDateFormat.format(System.currentTimeMillis());
							for (Object s : ts)
								System.out.println(date + "--" + s.toString());
							Main.DATA.addAll(ts);
						}

						@Override
						public void onError(Throwable throwable) {
							System.err.println(throwable.getMessage());
						}

						@Override
						public void onComplete() {

						}
					});
		}
	}
}
