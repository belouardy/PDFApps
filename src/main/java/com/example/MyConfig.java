package com.example;

import com.example.items.PdfItemProcessor;
import com.example.items.PdfItemReader;
import com.example.items.PdfItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class MyConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public MyConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<Resource> pdfItemReader() {
        // Utilisez le PdfItemReader personnalisé que vous avez créé.
        return new PdfItemReader();
    }

    @Bean
    public ItemProcessor<Resource, Resource> pdfItemProcessor() {
        // Utilisez le PdfItemProcessor personnalisé que vous avez créé.
        return new PdfItemProcessor();
    }

    @Bean
    public ItemWriter<Resource> pdfItemWriter() {
        // Utilisez le PdfItemWriter personnalisé que vous avez créé.
        return new PdfItemWriter();
    }

    @Bean
    public Step pdfProcessStep() {
        return stepBuilderFactory.get("pdfProcessStep")
                .<Resource, Resource>chunk(10)
                .reader(pdfItemReader())
                .processor(pdfItemProcessor()) // Ajouter le PdfItemProcessor ici
                .writer(pdfItemWriter())
                .build();
    }

    @Bean
    public Job pdfProcessJob() {
        return jobBuilderFactory.get("pdfProcessJob")
                .start(pdfProcessStep())
                .build();
    }
}
