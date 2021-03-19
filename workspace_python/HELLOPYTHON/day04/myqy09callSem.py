import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtWidgets
import random

form_class = uic.loadUiType("myqt09callSem.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb0.clicked.connect(self.myclick)
        self.pb1.clicked.connect(self.myclick)
        self.pb2.clicked.connect(self.myclick)
        self.pb3.clicked.connect(self.myclick)
        self.pb4.clicked.connect(self.myclick)
        self.pb5.clicked.connect(self.myclick)
        self.pb6.clicked.connect(self.myclick)
        self.pb7.clicked.connect(self.myclick)
        self.pb8.clicked.connect(self.myclick)
        self.pb9.clicked.connect(self.myclick)
        self.pbCall.clicked.connect(self.myCall)

        
    def myclick(self):
        txt_old = self.le.text()
        txt_new = self.sender().text()
        
        self.le.setText(txt_old+txt_new)
        # print(self.sender().text())     # sender : 컴포넌트를 보내준다.
    def myCall(self):
        txt_call = self.le.text()
        QtWidgets.QMessageBox.about(self, "Calling", txt_call)
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        