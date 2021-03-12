package quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class MainEntry {
   
   public static void main(String[] args) {
   
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();   // SchedulerFactory 생성, Scheduler를 생성하기위해 사용
      
      try {                                          // Scheduler -> JobDetail과 Trigger를 등록,관리하는 interface클래스 
         Scheduler scheduler = schedulerFactory.getScheduler();   // Scheduler는 어떤 job과 어떤 trigger를 실행해줄지 관리한다.
                                                   // SchedulerFactory에서 getScheduler을 통해 가져오는데 job과 trigger를 셋팅해줄수있고,
         // Scheduler가 동작하는 단위가 쓰레드               // 작업공간에 올라가지 않은 상태인데 start()메서드를 통해 작업공간에 올려진다.
         
         // newJob 메서드는 JobBuilder의 메서드인데 job을 implements한 클래스의 작업과 jobName을 JobDetail로 Build해서 만들어준다.
         JobDetail job = newJob(TestJob.class).withIdentity("jobName", Scheduler.DEFAULT_GROUP).build();
         // newJob(abc).withIdentity(name, GROUP).build(); -> 사용법, 구성요소, 방식...
         
         Trigger trigger = 
            newTrigger().withIdentity("jobName", Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule("*/1 * * * * ?")).build();
         // 위와 동일,  cronSchedule(" 표현식 ")
         
         scheduler.scheduleJob(job, trigger);   // job과 trigger를 셋팅을 해준다.
         scheduler.start();
     
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

// 임시파일 정리
// 쇼핑몰 주문시 무통장입금 시간지나면 주문취소





