from django.db import models


# Create your models here.

class UserInfo(models.Model):
    user = models.CharField(max_length=32)
    pwd = models.CharField(max_length=32)


class ToDo(models.Model):
    parent = models.BigIntegerField
    task = models.CharField(max_length=64)
    description = models.CharField(max_length=255)
    category = models.CharField(max_length=32)
    progress = models.CharField(max_length=8)
    date = models.DateField
    state = models.CharField(max_length=4)
