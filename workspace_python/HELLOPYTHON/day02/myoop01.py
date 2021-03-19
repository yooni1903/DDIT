class Animal:
    def __init__(self):
        # 전역변수는 생성자 안에서 선언해준다. 꼭 self.을 붙여줘야한다.
        self.age = 1
        print("생성자" )
    
    def __del__(self):
        print("소멸자/파괴자")
        
    def getOlder(self):
        self.age+=1 # age++이 안먹힌다.

class Human(Animal):
    def __init__(self):
        super().__init__()
        self.flag_coding = True
        
    def cutHand(self):
        self.flag_coding = False

# java의 main과 같음    
if __name__ == '__main__':
    hum = Human()   # 객체 생성 - new가 없다.
    print(hum.age)
    print(hum.flag_coding)
    hum.getOlder()
    hum.cutHand()
    print(hum.age)
    print(hum.flag_coding)
    