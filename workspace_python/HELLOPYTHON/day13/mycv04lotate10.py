import cv2
 
img = cv2.imread('image/lena.jfif', 1)
print(img)
# 픽셀은 BGR 순으로 설정해야한다

img90 = cv2.rotate(img, cv2.ROTATE_90_CLOCKWISE) # 시계방향으로 90도 회전
img180 = cv2.rotate(img, cv2.ROTATE_180) # 180도 회전
img270 = cv2.rotate(img, cv2.ROTATE_90_COUNTERCLOCKWISE) # 반시계방향으로 90도 회전 
                                                         # = 시계방향으로 270도 회전
 
cv2.imshow('original', img)
cv2.imshow('rotate90', img90)
cv2.imshow('rotate180', img180)
cv2.imshow('rotate270', img270)


cv2.waitKey(0)
cv2.destroyAllWindows()


