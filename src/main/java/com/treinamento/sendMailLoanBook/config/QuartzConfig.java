package com.treinamento.sendMailLoanBook.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.treinamento.sendMailLoanBook.job.SendBookLoanNotificationScheduleJob;

@Configuration
public class QuartzConfig {

	@Bean
	JobDetail sendBookLoanNotificationJobDetail() {
		return JobBuilder.newJob(SendBookLoanNotificationScheduleJob.class)
				.withIdentity("sendBookLoanNotificationJobDetail")
				.storeDurably()
				.build();
	}

	@Bean
	Trigger jobTrigger(JobDetail sendBookLoanNotificationJobDetail) { 
		
		// Exemplo A: Executa a cada 10 segundos
		//String exp = "0/10 * * * * ?"; 

		// Exemplo B: Executa a cada 1 minuto (no segundo 0 de cada minuto)
		// String exp = "0 * * * * ?"; 

		// Exemplo C: Ajustar para o horário atual exato para teste imediato (ex: 10h40min)
		 String exp = "0 55 10 * * ?";				
				
		return TriggerBuilder
				.newTrigger()
				.forJob(sendBookLoanNotificationJobDetail) // Utiliza a variável injetada
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(exp))
				.build();
	}
}