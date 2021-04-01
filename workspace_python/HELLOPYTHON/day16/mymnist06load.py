from keras import layers
from keras import models
from keras.datasets import mnist
from keras.utils import to_categorical
from keras.models import load_model
import numpy as np

# MNIST 데이터셋 불러오기
(train_images, train_labels), (test_images, test_labels) = mnist.load_data()
test_oringin_labels = test_labels
test_oringin_images = test_images



# 이미지 데이터 준비하기 (모델에 맞는 크기로 바꾸고 0과 1사이로 스케일링)
test_images = test_images.reshape((10000, 28 * 28))
test_images = test_images.astype('float32') / 255


model = load_model('mymodel.h5')

predictions = model.predict(test_images)

print(np.argmax(predictions[0]))
