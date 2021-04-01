# python의 class는 default로 public 이다.
class Dog:
    def __init__(self):
        self.flag_bark = True
        
    def cutSungdae(self):
        self.flag_bark = False
        
class Bird:
    def __init__(self):
        self.idx_fly = 0
    def training(self):
        self.idx_fly += 1
    def traing_hard(self, power):   # parameter는 power 하나로 메소드를 사용할 땐 괄호 안에 1개만 넣는다. self는 자동으로 들어감!
        self.idx_fly += power
        
        
class GaeSae(Dog, Bird):    # 다중 상속
    def __init__(self):
        Dog.__init__(self)  # 다중 상속을 할 경우에는 super를 사용하게 되면 첫번째 부모만을 사용하기 때문에 따로 해야된다.
        Bird.__init__(self)
        self.flag_kill = True
        #pass    # pass는 java의 {}처럼 메모리 영역이 끝났다는 것을 알려줌 - 아무것도 없을 때 사용
    def usekill(self):
        self.flag_kill = False
    
if __name__ == '__main__':
    gs = GaeSae()
    print(gs.flag_bark)
    print(gs.idx_fly)
    print(gs.flag_kill)
    
    gs.cutSungdae()
    gs.traing_hard(5)
    gs.usekill()
    
    print(gs.flag_bark)
    print(gs.idx_fly)
    print(gs.flag_kill)
