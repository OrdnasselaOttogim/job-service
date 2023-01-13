package com.example.jobservice.controller;

import com.example.jobservice.model.Itinerary;
import com.example.jobservice.model.Job;
import com.example.jobservice.model.Position;
import com.example.jobservice.service.JobService;
import com.example.jobservice.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/job")
@CrossOrigin
public class JobController {


    private final JobService jobService;
    private final MapService mapService;
    @Autowired
    public JobController(JobService jobService, MapService mapService) {
        this.jobService = jobService;
        this.mapService = mapService;
    }

    @GetMapping
    public List<Job> getJobs(){
        return jobService.getJobs();
    }



    @GetMapping("/{id}")
    public Optional<Job> getJob(@PathVariable Long id){
        Optional<Job> job = jobService.getJob(id);
        if (job.isPresent()){
            Job jobTemp = job.get();
            if (jobTemp.getId()>=1000){
                jobTemp.setAddress(jobTemp.getAddress()+ ", Australia");
            }
            jobTemp.setPosition(mapService.getPosition(jobTemp.getAddress()));
        }
        return job;
    }

    @GetMapping("/{id}/itinerary")
    public Map<String,Itinerary> getJobWithItinerary(@PathVariable Long id, @RequestParam String start, @RequestParam String end){
        Position positionStart = mapService.getPosition(start);
        Position positionEnd = mapService.getPosition(end);
        return mapService.getItineraries(positionStart, positionEnd);
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
