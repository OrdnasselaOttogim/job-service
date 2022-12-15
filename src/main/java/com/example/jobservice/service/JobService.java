package com.example.jobservice.service;

import com.example.jobservice.repository.JobRepository;
import com.example.jobservice.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobService {


    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public List<Job> getJobs(){
        return jobRepository.findAll();
    }


    public void postJob(Job job) {
        Optional<Job> jobOptional = jobRepository.findJobByTitle(job.getTitle());
        if (jobOptional.isPresent()){
            throw new IllegalStateException("Title already existing");
        }
        jobRepository.save(job);
    }


    public void deleteJob(Long jobId) {
        boolean exists = jobRepository.existsById(jobId);
        if(!exists){
            throw new IllegalStateException(
                    "job with id: " + jobId + " doesn't exist"
            );
        }
        jobRepository.deleteById(jobId);
    }


    @Transactional
    public void putJob(Long jobId, String newTitle, String description, String address, String payment) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalStateException(
                "job with id=" + jobId + " does not exist"
        ));

        Optional<Job> jobOptional = jobRepository.findJobByTitle(newTitle);

        if (newTitle != null && newTitle.length() > 0 && !Objects.equals(job.getTitle(), newTitle) && jobOptional.isEmpty()){
            job.setTitle(newTitle);
        }

        if (description != null){
            job.setDescription(description);
        }

        if (address != null){
            job.setAddress(address);
        }

        if (payment != null){
            job.setCategory(payment);
        }

    }





}
