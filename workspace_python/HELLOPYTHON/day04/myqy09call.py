import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random

form_class = uic.loadUiType("myqt09call.ui")[0]

class MyWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb0.clicked.connect(lambda: self.myclick(self.pb0))
        self.pb1.clicked.connect(lambda: self.myclick(self.pb1))
        self.pb2.clicked.connect(lambda: self.myclick(self.pb2))
        self.pb3.clicked.connect(lambda: self.myclick(self.pb3))
        self.pb4.clicked.connect(lambda: self.myclick(self.pb4))
        self.pb5.clicked.connect(lambda: self.myclick(self.pb5))
        self.pb6.clicked.connect(lambda: self.myclick(self.pb6))
        self.pb7.clicked.connect(lambda: self.myclick(self.pb7))
        self.pb8.clicked.connect(lambda: self.myclick(self.pb8))
        self.pb9.clicked.connect(lambda: self.myclick(self.pb9))
        self.pbCall.clicked.connect(self.myCall)

        
    def myclick(self, button):
       num = self.le.text()
       num += button.text()
       print(num)
       self.le.setText(num) 
    def myCall(self):
        print("call")
        msg = QMessageBox()
        msg.setWindowTitle("전화중")
        msg.setText(self.le.text() + " Calling...")
        msg.setStandardButtons(QMessageBox.Ok)
        result = msg.exec_()
        if result == QMessageBox.Ok:
            self.send_valve_popup_signal.emit(True)
        
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow =MyWindow()
    myWindow.show()
    app.exec_()
        