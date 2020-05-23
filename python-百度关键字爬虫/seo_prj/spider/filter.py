import MySQLdb


def getwordlist(get_sql):
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    fo = open("word_filter.txt", "w", encoding="utf-8")
    cursor = db.cursor()
    word_list = []
    try:
        cursor.execute(get_sql)
        results = cursor.fetchall()
        for row in results:
            for key in row[0].split("~~"):
                word_list.append(key)
        str = '\n'.join(list(set(word_list)))
        # print(str)
        fo.write(str)
    except Exception as e:
        print(e)
    finally:
        fo.close()
        db.close()


def update():
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    fo = open("word_filter.txt", "r", encoding="utf-8")
    cursor = db.cursor()
    word_list = []
    word_list = fo.read().split('\n')
    # print(word_list)
    # delete_sql = ""
    for key in word_list:
        if len(key) > 0:
            delete_sql = "DELETE FROM t_tail_word_20190529 WHERE id in (SELECT id FROM(" \
                         "select id,tail_word from t_tail_word_20190529 where tail_word like '%" + key + "%') a)"
            print(key)
            try:
                cursor.execute(delete_sql)
                db.commit()
            except Exception as e:
                print(e)
                db.rollback()
    fo.close()
    db.close()


if __name__ == '__main__':
    get_sql = "SELECT word_split FROM tail_word_20190529"
    # getwordlist(get_sql)
    update()
