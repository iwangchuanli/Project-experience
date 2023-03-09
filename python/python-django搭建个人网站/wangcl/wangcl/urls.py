"""wangcl URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

from django.contrib import admin
import cli.views
import todo.views
from django.views import static
from django.conf import settings
from django.conf.urls import url, handler404, handler500

handler404 = 'cli.views.page_not_found'
handler500 = 'cli.views.page_error'
urlpatterns = [
    # path('admin/', admin.site.urls),
    url(r'^$', cli.views.index),
    url(r'^index.html$', cli.views.index),
    url(r'^login.html$', cli.views.login),
    url(r'^logout/$', cli.views.logout),
    url(r'^register.html$', cli.views.register),
    url(r'^static/(?P<path>.*)$', static.serve, {'document_root': settings.STATIC_ROOT}, name='static'),
    url(r'^todo/list.html$', cli.views.todo_list),
    url(r'^coderun.html$', cli.views.coderun)
]
