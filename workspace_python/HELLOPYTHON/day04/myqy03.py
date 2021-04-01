import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myqt03.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        # python은 C++의 라이브러리를 가져와서 사용하기 때문에 이전에서는 사용되지 않은 get이 없는거다! 
        # -> 그래서 getText()가 아닌 text()인 이유
        a = self.le1.text()
        b = self.le2.text()
        sum = int(a)+int(b)
        self.le3.setText(str(sum))
    
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        