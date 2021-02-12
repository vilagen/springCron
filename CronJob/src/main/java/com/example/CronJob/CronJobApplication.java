package com.example.CronJob;

import java.util.Date;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//import com.sun.org.slf4j.internal.Logger;



@SpringBootApplication

public class CronJobApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CronJobApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CronJobApplication.class, args);
	}
	
//	@Scheduled(initialDelay = 1000L, fixedDelayString = "${someJob.delay}") 
	//Fixed would be every 2 seconds, between each invocation fixedDelaly means each invocation starts 2 seconds after the previous one.
	//fixedDelayString means we can write something like PT10M, meaning every 10 minutes, or PT1H meaning every one hour.
	// we can use a fixed variable name like ${someJob.delay}, and put the name on application.properties.
//	@Scheduled(cron="0 0 18 * * MON-FRI")
	// the cron job has six arguments, ****** means every second of every minute of every hour of every day.
	// cron="*/5 */5 * * * *" */5 means every 5th minute, */5 */5 means every 5 seconds of every minute, * */5 means every five minutes.
	// cron=0 0 18 * * * would mean 6pm every day, cron="0 0 18 * * MON-FRI" means 6pm Mon-Fri.
	// you can look up a site like crontab.guru to get more specific timesfor cron.
//	void Somejob() throws InterruptedException
//	{
//		System.out.println("Now is " + new Date());
//		Thread.sleep(1000L);
//	}
	
	@Scheduled(initialDelay = 1000L, fixedDelayString = "${someJob.delay}") 
	void Somejob() throws InterruptedException
	{
		LOGGER.info("Now is " + new Date());
		Thread.sleep(1000L);
	}
	
	//All scheduled jobs run on a single thread. 
	// If we want to make multiple jobs run at the same time, we can do this.
	@Scheduled(fixedRate = 1000L)
	void Somejob2() throws InterruptedException
	{
		LOGGER.info("Now is another example " + new Date());
		Thread.sleep(1000L);
	}
	
	

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "schduling.enabled", matchIfMissing= true)
class SchedulingConfiguration {
	
}