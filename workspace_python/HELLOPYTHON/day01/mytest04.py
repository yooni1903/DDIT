# a = input("시작수를 넣으세요") 단 작은 수 
# b = input("끝 수를 넣으세요")
# a에서 b까지의 합을 구하세요

a = input("시작수를 넣으세요")
b = input("끝 수를 넣으세요")

sum = 0
for i in range(int(a), int(b)+1):
    sum += i
    
print("합계 : ", sum)