import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random

form_class = uic.loadUiType("myqt07.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        rnd = random.random()
        com = ""
        if rnd > 0.5:
            com = "홀"
        else:
            com = "짝"
            
        mine = self.leMine.text()
        
        result = ""
        if com == mine:
            result = "이겼습니다!!!"
        else:
            result = "졌습니다ㅜ"
        self.leCom.setText(com)
        self.leResult.setText(result)
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        