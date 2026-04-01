package com.upgrade.jobms.job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImp implements JobService {

    private JobsRepo jobsRepo;

    public JobServiceImp(JobsRepo jobsRepo) {
        this.jobsRepo = jobsRepo;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobsRepo.findAll();
    }

    @Override
    public void createJob(Job job) {
        if(job == null) throw new IllegalArgumentException("Job data can not be null");

        if(job.getMinSalary() > job.getMaxSalary()) try {
            throw  new IllegalAccessException("minimum salary cannot excited maximum salary");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }

        jobsRepo.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobsRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteJobById(Long id) {
        jobsRepo.deleteById(id);
    }


    @Override
    public void updateJobById(Long id, Job job) {
        Job updateJob = jobsRepo.findById(id).orElse(null);

        if(updateJob != null){
            updateJob.setTitle(job.getTitle());
            updateJob.setDescription(job.getDescription());
            updateJob.setLocation(job.getLocation());
            updateJob.setMinSalary(job.getMinSalary());
            updateJob.setMaxSalary(job.getMaxSalary());

            jobsRepo.save(updateJob);
        }
    }


}
