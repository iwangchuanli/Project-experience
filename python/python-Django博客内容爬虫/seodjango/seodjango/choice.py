import MySQLdb
from django.http import HttpRequest, HttpResponseRedirect
from django.shortcuts import render_to_response


def choice(request):
    context = {}
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    cursor = db.cursor()
    select_sql = "select `tail_word` from `tail_word_20190529` where extr_flag = '0' limit 20"
    context['word_list'] = []
    try:
        cursor.execute(select_sql)
        results = cursor.fetchall()
        for row in results:
            context['word_list'].append(row[0])
    except Exception as e:
        print(e)
    db.close()
    print(context)
    return render_to_response('choice.html', context)


def update(request=HttpRequest()):
    context = {}
    word_list = request.POST.get('sub_data').split("~~")
    print(word_list)
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    cursor = db.cursor()
    for word in word_list:
        if len(word) > 1:
            update_sql = "update t_tail_word_20190529 set extr_flag= '1' where tail_word=\"" + word + "\""
            print(update_sql)
            try:
                cursor.execute(update_sql)
                db.commit()
            except Exception as e:
                print(e)
                db.rollback()
    db.close()
    return HttpResponseRedirect('/choice/')


def show(request=HttpRequest()):
    context = {}
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    cursor = db.cursor()
    select_sql = "select `tail_word` from `tail_word_20190529` where extr_flag = '1' limit 20"
    context['word_list'] = []
    try:
        cursor.execute(select_sql)
        results = cursor.fetchall()
        for row in results:
            context['word_list'].append(row[0])
    except Exception as e:
        print(e)
    db.close()
    print(context)
    return render_to_response('show.html', context)
