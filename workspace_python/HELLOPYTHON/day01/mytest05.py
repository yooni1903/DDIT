# input("구구단 단수를 넣으세요") 2
# 2단을 출력

a = input("구구단 단수를 넣으세요")
num = int(a)

print(a,"단")
for i in range(1,10):
    b = num * i
    print(num,"*", i,"=", b)
    

dan = input("단수를 넣으세요")
for i in range(1,10):
    print(dan+"*"+str(i)+"="+str(int(dan)*1))
# print(dan+"*2="+str(int(dan)*2))
# print(dan+"*3="+str(int(dan)*3))
# print(dan+"*4="+str(int(dan)*4))
# print(dan+"*5="+str(int(dan)*5))