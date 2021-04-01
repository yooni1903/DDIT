import cv2
 
img = cv2.imread('image/lena.jfif', 1)
print(img)

height, width = img.shape[:2]
resize_img = cv2.resize(img, (int(width/3), int(height/3)))

cv2.imshow('Test Image', resize_img)
cv2.waitKey(0)
cv2.destroyAllWindows()


