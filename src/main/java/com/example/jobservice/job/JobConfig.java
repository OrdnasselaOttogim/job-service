package com.example.jobservice.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobConfig {

    @Bean
    CommandLineRunner commandLineRunner(JobRepository repository){
        return args -> {
            Job job1 = new Job("titolo1", "descrizione1", "roma", 222);
            Job job2 = new Job("titolo2", "descrizione2", "roma", 222);

            repository.saveAll(List.of(job1,job2));

        };
    }
}
