import cv2
 
img = cv2.imread('image/blue.png', 1)
print(img)
# 픽셀은 BGR 순으로 설정해야한다

cv2.imshow('Test Image',img)
# cv2.imshow('frame2',img_gray)
# cv2.imshow('frame3',img_alpha)
cv2.waitKey(0)
cv2.destroyAllWindows()


