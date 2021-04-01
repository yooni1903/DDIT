from keras import layers
from keras import models
from keras.datasets import mnist
from keras.utils import to_categorical


# MNIST 데이터셋 불러오기
(train_images, train_labels), (test_images, test_labels) = mnist.load_data()

arr2D = train_images[1]

for i in arr2D:
    for j in i:
        if j ==0:
            print("0",end="")
        else:
            print("1",end="") 
    print()
# print(train_images,end="\t")
# print(train_images)
# print(train_images[9])
# print(train_labels[9])
 # print(len(train_labels))
 # print(len(test_images))
 # print(len(test_labels))


# 이미지 데이터 준비하기 (모델에 맞는 크기로 바꾸고 0과 1사이로 스케일링)
train_images = train_images.reshape((60000, 28 * 28))
train_images = train_images.astype('float32') / 255
test_images = test_images.reshape((10000, 28 * 28))
test_images = test_images.astype('float32') / 255

# 레이블을 범주형으로 인코딩
train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)

# 모델 정의하기 (여기에서는 Sequential 클래스 사용)
model = models.Sequential()
model.add(layers.Dense(512, activation='relu', input_shape=(28 * 28,)))
model.add(layers.Dense(10, activation='softmax'))

# 모델 컴파일 하기
model.compile(optimizer='rmsprop',
                loss='categorical_crossentropy',
                metrics=['accuracy'])

# fit() 메서드로 모델 훈련 시키기
model.fit(train_images, train_labels, epochs=5, batch_size=128)

# 테스트 데이터로 정확도 측정하기
test_loss, test_acc = model.evaluate(test_images, test_labels)
print('test_acc: ', test_acc)