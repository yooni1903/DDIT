package timer;

import java.util.Timer;
import java.util.TimerTask;

public class timer {

   public static void main(String[] args) {

      Timer m_timer = new Timer();
      TimerTask m_task = new TimerTask() { @Override public void run() {   System.out.println("5초후 알림");
            
         }
      };
      
      m_timer.schedule(m_task, 1000);
      
   }

}