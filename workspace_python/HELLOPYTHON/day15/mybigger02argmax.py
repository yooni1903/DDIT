import numpy as np

a = [0,0,0,0,0, 0,0,0,6,0]

a_n = np.array(a)

pred = np.argmax(a_n)        

print(pred)