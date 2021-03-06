import cv2
 
img = cv2.imread('0.jfif', 1)

height, width = img.shape[:2]
img_28 = cv2.resize(img, (28, 28))
img_gray = cv2.cvtColor(img_28,cv2.COLOR_BGR2GRAY)

img_input = (255 - img_gray) / 256

cv2.imshow('Test Image', img_28)
cv2.imshow('Test Image', img_input)
print(img_gray)

cv2.waitKey(0)
cv2.destroyAllWindows()


