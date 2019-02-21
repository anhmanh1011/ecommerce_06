package app.config;

import org.apache.log4j.Logger;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MyScheduler {
    private static final Logger logger = Logger.getLogger(MyScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    	@Scheduled(cron = "0 0 9 * * FRI" )
//    @Scheduled(fixedDelay = 2 * 10000, initialDelay = 500)
    public void myScheduler() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            logger.info("Job's Status:::" + jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            logger.error(e);
        }
    }
}
