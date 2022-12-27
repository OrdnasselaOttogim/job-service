package com.example.jobservice.controller;

import com.example.jobservice.model.Job;
import com.example.jobservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/job")
@CrossOrigin
public class JobController {


    private final JobService jobService;
    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getJobs(){
        return jobService.getJobs();
    }



    @GetMapping("/{id}")
    public Optional<Job> getJob(@PathVariable Long id){
        return jobService.getJob(id);
    }


    @PostMapping
    public void postJob(@RequestBody Job job){
        jobService.postJob(job);
    }


    @PostMapping("/params")
    public void postJobParams(@RequestParam String title, @RequestParam String description, @RequestParam String address,
                             @RequestParam String category){
        System.out.println(title + description + address + category);
        jobService.postJobParams(title, description, address, category);
    }


    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }



    @PutMapping("/{id}")
    public Job updateJob(@RequestBody Job newJob, @PathVariable Long id){
        return jobService.putJob(newJob, id);
    }

    @PutMapping(path = {"/params/{jobId}"})
    public void putJob(
            @PathVariable("jobId") Long jobId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String payment
            ){
        jobService.putJobParams(jobId, title, description, address, payment);
    }


}
