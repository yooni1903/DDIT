# 가위바위보 프로그램을 구현하시오
import random
a = input("가위바위보 입력 : ")

com = ""

rnd = random.random()
if rnd >= 0.66:
    com = "가위"
elif rnd >= 0.33:
    com = "바위"
else:
    com = "보"
    
print("나 : ", a, "/ 컴퓨터 : ", com)
if a == com:
    print("비겼습니다")
elif a == "가위" and com == "보" or a == "바위" and com == "가위" or a == "보" and com == "바위":
    print("이겼습니다!! \^o^/")
else:
    print("졌습니다ㅜ") 
    
    
# 선생님이 하신 코드
# if rnd >= 0.66:
    # com = "가위"
# elif rnd >= 0.33:
    # com = "바위"
# else:
    # com = "보"
    #
# result = "" 
# if com == "가위"  and a == "가위":
    # result="비김"
# elif com == "가위" and a == "바위":
    # result= "짐"
# elif com == ""

# 암튼 다 쓰심