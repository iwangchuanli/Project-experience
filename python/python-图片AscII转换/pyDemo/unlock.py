import os
import cv2
from PIL import Image
 
 
def unlock_mv(sp):
    """ 将视频转换成图片
        sp: 视频路径 """
    cap = cv2.VideoCapture(sp)
    suc = cap.isOpened()  # 是否成功打开
    frame_count = 0
    while suc:
        frame_count += 1
        suc, frame = cap.read()
        params = []
        params.append(2)  # params.append(1)
        cv2.imwrite('mv\\%d.jpg' % frame_count, frame, params)
 
    cap.release()
    print('unlock image: ', frame_count)
 
 
def jpg2video(sp, fps):
    """ 将图片合成视频. sp: 视频路径，fps: 帧率 """
    fourcc = cv2.VideoWriter_fourcc(*"MJPG")
    images = os.listdir('mv')
    im = Image.open('mv/' + images[0])
    vw = cv2.VideoWriter(sp, fourcc, fps, im.size)
 
    os.chdir('mv')
    for image in range(len(images)):
        # Image.open(str(image)+'.jpg').convert("RGB").save(str(image)+'.jpg')
        jpgfile = str(image + 1) + '.jpg'
        try:
            frame = cv2.imread(jpgfile)
            vw.write(frame)
        except Exception as exc:
            print(jpgfile, exc)
    vw.release()
    print(sp, 'Synthetic success!')
 
 
if __name__ == '__main__':
    sp = "111video.mp4"
    sp_new = 'video_new.avi'
    unlock_mv(sp)  # 视频转图片
    jpg2video(sp_new, 28)  # 图片转视频