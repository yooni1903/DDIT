import pymysql


class MyEmpDao:
    def __init__(self):
        pass
    
    def getEmps(self):
        ret = []
        conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='python', charset='utf8')
        curs = conn.cursor()   
        sql = "select sabun, name, dept, mobile from emp"
        curs.execute(sql)
        rows = curs.fetchall()
        for e in rows:
            temp = {'sabun': e[0], 'name': e[1], 'dept':e[2], 'mobile':e[3]}
            ret.append(temp)
        
        conn.close()
        return ret
    
    def insEmp(self,sabun, name, dept, mobile):
        conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
        curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
         
        sql = "INSERT INTO emp (sabun, name, dept, mobile) VALUES (%s, %s, %s, %s)"
        
        cnt = curs.execute(sql, (sabun, name, dept, mobile))
        conn.commit()   
        conn.close()
        return cnt
    
    def updEmp(self,sabun, name, dept, mobile):
        conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
        curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
         
        sql = "update emp set name=%s,dept=%s,mobile=%s where sabun=%s"
        
        cnt = curs.execute(sql, (name, dept, mobile,sabun))
        conn.commit()   
        conn.close()
        return cnt
    
    def delEmp(self,sabun):
        conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
        curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
         
        sql = "delete from emp where sabun=%s"
        
        cnt = curs.execute(sql, (sabun))
        conn.commit()   
        conn.close()
        return cnt
    
    def getEmp(self, sabun):
        conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='python', charset='utf8')
        curs = conn.cursor()   
        sql = "select sabun, name, dept, mobile from emp where sabun= %s"
        curs.execute(sql, sabun)
        rows = curs.fetchall()
        ret = {'sabun': rows[0], 'name': rows[1], 'dept':rows[2], 'mobile':rows[3]}
        
        conn.close()
        return ret    

if __name__ == '__main__':
    # list = MyEmpDao().getEmps()
    # print(list)
    # cnt = MyEmpDao().insEmp('4', '4', '4', '4')
    # print(cnt)
    # cnt = MyEmpDao().updEmp('3', '4', '4', '4')
    # print(cnt)
    cnt = MyEmpDao().delEmp('3')
    print(cnt)