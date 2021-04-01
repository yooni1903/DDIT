import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random

form_class = uic.loadUiType("myqt08.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        rnd = random.random()
        com = ""
        if rnd > 0.66:
            com = "가위"
        elif rnd > 0.33:
            com = "바위"
        else:
            com = "보"
            
        mine = self.leMine.text()
        
        result = ""
        if com == mine:
            result = "비겼습니다"
        elif com == "가위" and mine == "바위" or com == "바위" and mine== "보" or com == "보" and mine == "가위":
            result = "이겼습니다!!!!!!"
        else:
            result = "졌습니다ㅜ"
        self.leCom.setText(com)
        self.leResult.setText(result)
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        