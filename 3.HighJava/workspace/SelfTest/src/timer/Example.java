package timer;

import java.util.Timer;
import java.util.TimerTask;

public class Example {
   
   public static int count;
   
   public static void main(String[] args){

      count = 0;
      final Timer m_timer = new Timer();
      TimerTask m_task = new TimerTask() {
         
         @Override
         public void run() {
            // TODO Auto-generated method stub
            if(count<5) {
               System.out.println("7시 50분 알람!!");
               count++;
            } else {
               m_timer.cancel();
            }
         }
      };
      m_timer.schedule(m_task, 5000, 2000);
   }
}

