# -*- coding:utf-8 -*-
# coding:utf-8
import os, cv2, subprocess, shutil
from cv2 import VideoWriter, VideoWriter_fourcc, imread, resize
from PIL import Image, ImageFont, ImageDraw

ffmpeg = r'E:\Develop\ffmpeg\bin\ffmpeg.exe'
code_color = (169,169,169) # 颜色RGB 默认灰色 ，'' 则彩色

# 像素对应ascii码
#ascii_char = list("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:oa+>!:+. ")
#ascii_char = ['.',',',':',';','+','*','?','%','S','#','@'][::-1]
#ascii_char = list("MNHQ$OC67+>!:-. ")
ascii_char = list("MNHQ$OC67)oa+>!:+. ")

# 将像素转换为ascii码
def get_char(r, g, b, alpha=256):
    if alpha == 0:
        return ''
    length = len(ascii_char)
    gray = int(0.2126 * r + 0.7152 * g + 0.0722 * b)
    unit = (256.0 + 1) / length
    return ascii_char[int(gray / unit)]

# 将txt转换为图片
def txt2image(file_name):
    im = Image.open(file_name).convert('RGB')
    # gif拆分后的图像，需要转换，否则报错，由于gif分割后保存的是索引颜色
    raw_width = im.width
    raw_height = im.height
    width = int(raw_width / 6)
    height = int(raw_height / 15)
    im = im.resize((width, height), Image.NEAREST)

    txt = ""
    colors = []
    for i in range(height):
        for j in range(width):
            pixel = im.getpixel((j, i))
            colors.append((pixel[0], pixel[1], pixel[2]))
            if (len(pixel) == 4):
                txt += get_char(pixel[0], pixel[1], pixel[2], pixel[3])
            else:
                txt += get_char(pixel[0], pixel[1], pixel[2])
        txt += '\n'
        colors.append((255, 255, 255))

    im_txt = Image.new("RGB", (raw_width, raw_height), (255, 255, 255))
    dr = ImageDraw.Draw(im_txt)
    # font = ImageFont.truetype(os.path.join("fonts","汉仪楷体简.ttf"),18)
    font = ImageFont.load_default().font
    x = y = 0
    # 获取字体的宽高
    font_w, font_h = font.getsize(txt[1])
    font_h *= 1.37  # 调整后更佳
    # ImageDraw为每个ascii码进行上色
    for i in range(len(txt)):
        if (txt[i] == '\n'):
            x += font_h
            y = -font_w
            # self, xy, text, fill = None, font = None, anchor = None,
            # *args, ** kwargs
        if code_color:
            dr.text((y, x), txt[i], fill=code_color) # fill=colors[i]彩色
        else:
            dr.text((y, x), txt[i], fill=colors[i])  # fill=colors[i]彩色
        # dr.text((y, x), txt[i], font=font, fill=colors[i])
        y += font_w

    name = file_name
    # print(name + ' changed')
    im_txt.save(name)

# 将视频拆分成图片
def video2txt_jpg(file_name):
    vc = cv2.VideoCapture(file_name)
    c = 1
    if vc.isOpened():
        r, frame = vc.read()
        if not os.path.exists('Cache'):
            os.mkdir('Cache')
        os.chdir('Cache')
    else:
        r = False
    while r:
        cv2.imwrite(str(c) + '.jpg', frame)
        txt2image(str(c) + '.jpg')  # 同时转换为ascii图
        r, frame = vc.read()
        c += 1
    os.chdir('..')
    return vc

# 将图片合成视频
def jpg2video(outfile_name, fps):
    fourcc = VideoWriter_fourcc(*"MJPG")
    images = os.listdir('Cache')
    im = Image.open('Cache/' + images[0])
    vw = cv2.VideoWriter(outfile_name, fourcc, fps, im.size)

    os.chdir('Cache')
    for image in range(len(images)):
        # Image.open(str(image)+'.jpg').convert("RGB").save(str(image)+'.jpg')
        frame = cv2.imread(str(image + 1) + '.jpg')
        vw.write(frame)
        # print(str(image + 1) + '.jpg' + ' finished')
    os.chdir('..')
    vw.release()

# 调用ffmpeg获取mp3音频文件
def video2mp3(file_name, outfile_name):
    cmdstr = " -i {0} -f mp3 {1} -y".format(file_name, outfile_name)
    cmd(cmdstr)

# 合成音频和视频文件
def video_add_mp3(file_name, mp3_file,outfile_name):
    cmdstr = " -i {0} -i {1} -strict -2 -f mp4 {2} -y".format(file_name, mp3_file, outfile_name)
    cmd(cmdstr)

# 视频截取
def vediocut(file_name, outfile_name, start, end):
    cmdstr = " -i {0} -vcodec copy -acodec copy -ss {1} -to {2} {3} -y".format(file_name,start,end,outfile_name)
    cmd(cmdstr)

# 执行脚本命令
def cmd(cmdstr):
    cmdstr = ffmpeg + cmdstr
    response = subprocess.call(cmdstr, shell=True, creationflags=0x08000000)
    if response == 1:
        print("ffmpeg脚本执行失败,请尝试手动执行:{0}".format(cmdstr))

# 主函数
def main(vedio, save=False, iscut=False, start='00:00:00', end='00:00:14'):
    """
    :param vedio: 原视频文件地址
    :param save: 是否保存临时文件 默认不保存
    :param iscut: 是否先对原视频做截取处理 默认不截取
    :param start: 视频截取开始时间点 仅当iscut=True时有效
    :param end: 视频截取结束时间点 仅当iscut=True时有效
    :return: 输出目标视频文件 vedio.split('.')[0] + '-code.mp4'
    """
    file_cut = vedio.split('.')[0] + '_cut.mp4'
    file_mp3 = vedio.split('.')[0] + '.mp3'
    file_temp_avi = vedio.split('.')[0] + '_temp.avi'
    outfile_name = vedio.split('.')[0] + '-code.mp4'
    print("开始生成...")
    if iscut:
        print("正在截取视频...")
        vediocut(vedio, file_cut, start, end)
        vedio = file_cut
    print("正在转换代码图片...")
    vc = video2txt_jpg(vedio) # 视频转图片，图片转代码图片
    FPS = vc.get(cv2.CAP_PROP_FPS)  # 获取帧率
    vc.release()
    print("正在分离音频...")
    video2mp3(vedio, file_mp3)  # 从原视频分离出 音频mp3
    print("正在转换代码视频...")
    jpg2video(file_temp_avi, FPS)  #代码图片转视频
    print("正在合成目标视频...")
    video_add_mp3(file_temp_avi, file_mp3, outfile_name) # 将音频合成到代码视频
    if (not save): # 移除临时文件
        print("正在移除临时文件...")
        shutil.rmtree("Cache")
        for file in [file_cut, file_mp3, file_temp_avi]:
            if os.path.exists(file):
                os.remove(file)
    print("生成成功：{0}".format(outfile_name))

if __name__ == '__main__':
    vedio = r"111video.mp4"
    main(vedio, save=False, iscut=False, start='00:00:00', end='00:00:14')