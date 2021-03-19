import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myqt04.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        # python은 C++의 라이브러리를 가져와서 사용하기 때문에 이전에서는 사용되지 않은 get이 없는거다! 
        # -> 그래서 getText()가 아닌 text()인 이유
        # obj = QLineEdit(self.le1)
        a = self.le1.text()
        b = self.le2.text()
        sum = 0
        for i in range(int(a), int(b)+1):
            sum += i
            
        # 형변환을 안하면 창이 꺼진다
        self.le3.setText(str(sum))
    
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        