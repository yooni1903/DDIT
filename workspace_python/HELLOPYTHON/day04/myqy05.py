import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myqt05.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myclick)
        
       
        
    def myclick(self):
        a = ""
        for i in range(5):
            a += self.pb.text() + "\n"
        print(a)    
        self.te.setText(a)
        
    
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        