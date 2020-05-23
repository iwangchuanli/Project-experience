import requests
from bs4 import BeautifulSoup
import urllib.request
import os
import random
import time


def html(url):
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Opera/9.25 (Windows NT 5.1; U; en)',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=url, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    times = soup.select("time")
    views = soup.select("p.label-key > b")
    active_str = str(views[2])
    active = active_str[active_str.find("title=\"") + 7:active_str.find("Z")]
    answers = soup.select("#answers-header > div > h2 >span")
    question_content = soup.select("div.post-text")
    tags = soup.select("#question > div.post-layout > div.postcell.post-layout--right > "
                       "div.post-taglist.grid.gs4.gsy.fd-column > div >a")
    title = soup.select("h1 >a")
    tags_str = ""
    item = []
    for tag in tags:
        tags_str += tag.get_text() + ","
    answer_contetnts = []
    for i in range(1, len(question_content)):
        answer_contetnts.append(question_content[i])
    for i in range(len(times)):
        if len(times[i].get_text()) > 1:
            asked_time = times[i].get("datetime").replace("T", " ")
    item.append(title[
                    0].get_text())  # title  views  answersnum   asked_time  tag_str  active_time   quest_content_ text  answer_content_list
    item.append(views[1].get_text())
    item.append(answers[0].get_text())
    item.append(asked_time)
    item.append(tags_str)
    item.append(active)
    item.append(question_content[0])
    item.append(answer_contetnts)
    print(item)
    # updatetosql(item)


def updatetosql(item):

    ansers_text = "[split]".join(item[7])
    updatesql = "UPDATE `t_stackoverflow_question` " \
                "SET `tags`='%s', `views`='%s', `answers_num`='%s', `asked_time`='%s', `last_active_time`='%s', `question_content`='%s', `answers_contetnt`='%s' " \
                "WHERE (`question_id`='%s') " \
                % (item[4], item[1], item[2], item[3], item[5], item[6], ansers_text, item[0],)
    pass


if __name__ == '__main__':
    html("https://stackoverflow.com/questions/50119673/nginx-fast-cgi-cache-on-error-page-404")
