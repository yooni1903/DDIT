package quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

   @Override
   public void execute(JobExecutionContext arg0) throws JobExecutionException {

      System.out.println("Job Executed 3조 화이팅 [" 
                  
                     + new Date(System.currentTimeMillis()) + "]");

   }

}