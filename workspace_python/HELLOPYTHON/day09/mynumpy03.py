import numpy as np

a = np.zeros((10, 10), dtype=int)  # int형으로 넣어주는 명령어

print(a)
print(a.shape)

b = np.reshape(a,(20,5))

print(b)
