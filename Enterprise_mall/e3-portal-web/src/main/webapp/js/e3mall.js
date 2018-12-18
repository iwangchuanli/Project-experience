var E3MALL = {
	checkLogin : function() {
		var _ticket = $.cookie("token");
		//如果从页面中没有取到token说明没有用户没有登录过
		if (!_ticket) {
			alert("没有取到token")//这个是为了测试用，如果没有取到首页会弹出：“没有取到token”的提示
			return;
		}
		//ajax请求，请求"http://localhost:8088/user/token/{token}",请求获取的数据格式如下： 
		//jsonp1524537658798({"status":200,"msg":"OK","data":{"id":6,"username":"koushuang","password":null,"phone":"18163138155","email":null,"created":1524491157000,"updated":1524491157000}});
		$.ajax({
			url : "http://localhost:8088/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data) {
				//如果请求得到的status为200说明请求成功
				if (data.status == 200) {
					//获取用户的姓名
					var username = data.data.username;
					var html = username + "，欢迎来到宜立方购物网！<a href=\"http://www.e3mall.cn/user/logout.html\" class=\"link-logout\">[退出]</a>";
					//替换url
					$("#loginbar").html(html);
				}
			}
		});
	}
}

//这个函数会在页面加载的时候自动执行
$(function() {
	// 查看是否已经登录，如果已经登录查询登录信息
	E3MALL.checkLogin();
});