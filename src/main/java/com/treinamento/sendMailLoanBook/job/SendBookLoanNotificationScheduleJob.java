package com.treinamento.sendMailLoanBook.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Configuration
public class SendBookLoanNotificationScheduleJob extends QuartzJobBean {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		try {
			this.jobLauncher.run(this.job, jobParameters);
		} catch (Exception e) {
			throw new JobExecutionException("Erro ao executar job do Spring Batch via Quartz", e);
		}
	}
}