import requests
import pymysql.cursors
from bs4 import BeautifulSoup

def get_effect_data(data):
    results = list()
    soup = BeautifulSoup(data, 'html.parser')
    projects = soup.find_all('div', class_='repo-list-item')
    for project in projects:
        writer_project = project.find('a', attrs={'class': 'v-align-middle'})['href'].strip()
        project_language = project.find('div', attrs={'class': 'd-table-cell col-2 text-gray pt-2'}).get_text().strip()
        project_starts = project.find('a', attrs={'class': 'muted-link'}).get_text().strip()
        update_desc = project.find('p', attrs={'class': 'f6 text-gray mb-0 mt-2'}).get_text().strip()

        result = (writer_project.split('/')[1], writer_project.split('/')[2], project_language, project_starts, update_desc)
        results.append(result)
    return results


def get_response_data(page):
    request_url = 'https://github.com/search'
    params = {'o': 'desc', 'q': 'python', 's': 'stars', 'type': 'Repositories', 'p': page}
    resp = requests.get(request_url, params)
    return resp.text


def insert_datas(data):
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='root',
                                 db='test',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)
    try:
        with connection.cursor() as cursor:
            sql = 'insert into project_info(project_writer, project_name, project_language, project_starts, update_desc) VALUES (%s, %s, %s, %s, %s)'
            cursor.executemany(sql, data)
            connection.commit()
    except:
        connection.close()


if __name__ == '__main__':
    total_page = 2 # 爬虫数据的总页数
    datas = list()
    for page in range(total_page):
        res_data = get_response_data(page + 1)
        data = get_effect_data(res_data)
        datas += data
    insert_datas(datas)