import cv2
 
img = cv2.imread('image/lena.jfif', 1)
img_gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
print(img)

# cv2.imshow('Test Image',img)
cv2.imshow('Test Image',img_gray)
# cv2.imshow('frame3',img_alpha)
cv2.waitKey(0)
cv2.destroyAllWindows()
