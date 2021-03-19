sum = 0
for i in range(11):
    sum += i
print(sum)
# 2~10까지의 2의 배수의 합을 구하시오
a = range(2,11)
sum = 0
for i in a:
    if i % 2 == 0:
       sum += i
       
print(sum)


# 선생님이 하신 코드
sum2 = 0
for i in range(2, 11):
    if i % 2 == 0:
        sum2 += i
print(sum2)