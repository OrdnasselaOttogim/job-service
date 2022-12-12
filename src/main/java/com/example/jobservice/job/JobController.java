package com.example.jobservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/job")
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


    @PostMapping
    public void postJob(@RequestBody Job job){
        jobService.postJob(job);
    }

    @DeleteMapping(path = "/{jobId}")
    public void deleteJob(@PathVariable("jobId") Long jobId){
        jobService.deleteJob(jobId);
    }


    @PutMapping(path = {"/{jobId}"})
    public void putJob(
            @PathVariable("jobId") Long jobId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String payment
            ){
        jobService.putJob(jobId, title, description, address, payment);
    }


}
