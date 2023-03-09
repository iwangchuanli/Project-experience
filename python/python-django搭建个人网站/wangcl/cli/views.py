import os
import subprocess
import time

import simplejson as simplejson
from django.shortcuts import HttpResponse, render_to_response
from django.shortcuts import render, reverse, redirect
from cli import models
from functools import wraps

# Create your views here.

'''说明：这个装饰器的作用，就是在每个视图函数被调用时，都验证下有没有登录，
如果有过登录，则可以执行新的视图函数，
否则没有登录则自动跳转到登录页面。'''
# 导入,可以使此次请求忽略csrf校验
from django.views.decorators.csrf import csrf_exempt


def check_login(f):
    @wraps(f)
    def inner(request, *arg, **kwargs):
        if request.session.get('is_login') == '1':
            return f(request, *arg, **kwargs)
        else:
            return redirect('/login.html')

    return inner


def index(request):
    # students=Students.objects.all()  ## 说明，objects.all()返回的是二维表，即一个列表，里面包含多个元组
    # return render(request,'index.html',{"students_list":students})
    # username1=request.session.get('username')
    user_id1 = request.session.get('user_id')
    # 使用user_id去数据库中找到对应的user信息
    userobj = models.UserInfo.objects.filter(id=user_id1)
    print(userobj)
    if userobj:
        return render(request, 'index.html', {"user": userobj[0]})
    else:
        return render(request, 'index.html', {'user': '匿名用户'})


def login(request):
    # 如果是POST请求，则说明是点击登录按扭 FORM表单跳转到此的，那么就要验证密码，并进行保存session
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = models.UserInfo.objects.filter(user=username, pwd=password)
        print(user)
        if user:
            # 登录成功
            # 1，生成特殊字符串
            # 2，这个字符串当成key，此key在数据库的session表（在数据库存中一个表名是session的表）中对应一个value
            # 3，在响应中,用cookies保存这个key ,(即向浏览器写一个cookie,此cookies的值即是这个key特殊字符）
            request.session['is_login'] = '1'  # 这个session是用于后面访问每个页面（即调用每个视图函数时要用到，即判断是否已经登录，用此判断）
            # request.session['username']=username  # 这个要存储的session是用于后面，每个页面上要显示出来，登录状态的用户名用。
            # 说明：如果需要在页面上显示出来的用户信息太多（有时还有积分，姓名，年龄等信息），所以我们可以只用session保存user_id
            request.session['user_id'] = user[0].id
            return redirect('/index.html')
    # 如果是GET请求，就说明是用户刚开始登录，使用URL直接进入登录页面的
    return render(request, 'user/login.html')


def register(request):
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = models.UserInfo.objects.create(user=username, pwd=password)
        print(user)
        if user:
            return redirect('user/login.html')
    return render(request, 'user/register.html')


# 用户退出
@check_login
def logout(request):
    request.session['is_login'] = '0'
    request.session['user_id'] = None
    return redirect('/index.html')


'''
错误页面处理
'''


def page_not_found(request, exception):
    return render(request, 'error/404.html')


def page_error(request):
    return render(request, 'error/500.html')


'''
todolist
'''


def todo_list(request):
    user = models.UserInfo.objects.all()
    # data.
    # data.append({"user", user})
    return render(request, "todo/list.html", {"user": user})


# 在处理函数加此装饰器即可
@csrf_exempt
def coderun(request):
    if request.method == "POST":
        code = request.POST.get('code')
        type = request.POST.get('type')
        print(type, code)
        start_time: float = time.time()
        if type == 'python2':
            print("python 2")
            file = open("D:\coderun\python\python2.7\\temp.py", 'w', encoding='utf-8')
            file.write(code)
            file.close()
            cmd = "D:\coderun\python\python2.7\Python27\python.exe D:\coderun\python\python2.7\\temp.py"
            results = popen(cmd)
            usedtime = time.time() - start_time
        if type == "python3":
            print("python 3")
            file = open("D:\coderun\python\python3.7\\temp.py", 'w', encoding='utf-8')
            file.write(code)
            file.close()
            cmd = "D:\coderun\python\python3.7\Python37\python.exe D:\coderun\python\python3.7\\temp.py"
            results = popen(cmd)
            usedtime = time.time() - start_time
        if type == 'java':
            file = open("d:/coderun/java/temp.java", 'w', encoding='utf-8')
            file.write(code)
            file.close()
            f = os.popen(r"java d:/java/temp.java", 'r')
            results = f.read()
            f.close()
            usedtime = time.time() - start_time
        data = {"language": type,
                "results": results,
                "usedtime": usedtime}
        return HttpResponse(simplejson.dumps(data, ensure_ascii=False))


def popen(cmd):
    try:
        subprc = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        output = subprc.communicate()
        returnlist = []
        for msg in output:
            returnlist.append(msg.decode('gbk'))
        return "".join(returnlist)
    except BaseException as e:
        return "error"
