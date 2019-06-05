from skimage import color,transform as trans
import imageio

 
class Transfer:
    # 视频路径
    __video_path = ''
    # 视频中提取出来的帧图像
    __input_imgs = []
    # 字符填充 从左到右辨识度增加
    __pixels = " .,-'`:!1+*abcdefghijklmnopqrstuvwxyz<>()\/{}[]?234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ%&@#$"
    # 输出的字符'图像'
    __output_imgs = []
    # 填充字符长度
    __pixels_length = 0
 
    def run(self):
        self.__pixels_lenth = len(self.__pixels)
        self.__readVideo()
        self.__transImgsIntoChars()
        print(len(self.__output_imgs))
        self.__play()
        # echo 0
 
    # 设置视频路径
    def setVideo(self, video_path):
        self.__video_path = video_path
        return  self
 
    # 读取视频的每一帧图像转化为灰度图，输出图像列表
    def __readVideo(self):
        imgs = imageio.get_reader(self.__video_path,'ffmpeg')
        for num,img in enumerate(imgs):
            print(img.shape)
            a = color.rgb2gray(img)
            # 根据你控制台的尺寸自己定义resize后的尺寸
            b = trans.resize(a, (64, 64),1,None,0,True,True,None,None)
            self.__input_imgs.append(b)
 
    # 灰度图像转化为字符图像
    def __transImgsIntoChars(self):
        for input in self.__input_imgs:
            item = []
            (h,w) = input.shape
            for row in range(h):
                row_chars = ''
                for col in range(w):
                    index = int(input[row][col] * (self.__pixels_lenth -1))
                    row_chars += self.__pixels[index]
                item.append(row_chars)
            self.__output_imgs.append(item)
 
    # 播放
    def __play(self):
        import time
        import curses
        width, height = len(self.__output_imgs[0][0]), len(self.__output_imgs[0])
        stdscr = curses.initscr()
        curses.start_color()
        try:
            # 调整窗口大小，宽度最好略大于字符画宽度。另外注意curses的height和width的顺序
            stdscr.resize(height, width * 2)
 
            for pic_i in range(len(self.__output_imgs)):
                # 显示 pic_i，即第i帧字符画
                for line_i in range(height):
                    # 将pic_i的第i行写入第i列。(line_i, 0)表示从第i行的开头开始写入。最后一个参数设置字符为白色
                    stdscr.addstr(line_i, 0, self.__output_imgs[pic_i][line_i], curses.COLOR_WHITE)
                stdscr.refresh()  # 写入后需要refresh才会立即更新界面
                time.sleep(1 / 24)  # 粗略地控制播放速度。更精确的方式是使用游戏编程里，精灵的概念
        finally:
            # curses 使用前要初始化，用完后无论有没有异常，都要关闭
            curses.endwin()
            # print(1)
 

video_path = '111video.avi'
a = Transfer()
a.setVideo(video_path).run()
