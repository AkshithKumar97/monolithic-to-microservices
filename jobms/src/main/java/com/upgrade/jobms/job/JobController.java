package com.upgrade.jobms.job;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAllJobs(){
        return jobService.findAllJobs();
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        //do not post duplicate jobs
        jobService.createJob(job);
        return ResponseEntity.ok("Job Has Posted Successfully");
    }

    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @DeleteMapping("delete-job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        jobService.deleteJobById(id);
        return ResponseEntity.ok("Job has been deleted successfully");
    }

    @PutMapping("update-job/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
        jobService.updateJobById(id,job);
        return ResponseEntity.ok("Details Updated");
    }
}
