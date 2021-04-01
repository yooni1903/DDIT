import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myqt06.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        a = self.le.text()
        dan = ""
        b = int(a)
        for i in range(1, 10):
            mul = b * i
            # 숫자형은 다... str로 형변환을 해줘야한다니........
            dan += a + " * " + str(i) + " = " + str(mul) + "\n"
        print(dan)
        self.te.setText(dan)
    
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        