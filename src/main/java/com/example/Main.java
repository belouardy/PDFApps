package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class)) {
            JobLauncher jobLauncher = context.getBean(JobLauncher.class);
            Job job = context.getBean("pdfProcessJob", Job.class);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("Exit Status : " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
