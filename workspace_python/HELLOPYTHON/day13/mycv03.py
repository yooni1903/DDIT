import cv2
import numpy as np
 
img = cv2.imread('image/lena.jfif', 1)
print(img)
# 픽셀은 BGR 순으로 설정해야한다

img90 = cv2.rotate(img, cv2.ROTATE_90_CLOCKWISE) # 시계방향으로 90도 회전
                                                         # = 시계방향으로 270도 회전
 
cv2.imshow('original', img)
height, width = img.shape[:2]
M = cv2.getRotationMatrix2D((width/2.0, height/2.0), #회전 중심
                            10, # 회전각도(양수 반시계방향, 음수 시계방향)
                            1) # 이미지 배율
img_rotation = cv2.warpAffine(img, M, (width, height))
cv2.imshow("rotation", img_rotation)

cv2.waitKey(0)
cv2.destroyAllWindows()


