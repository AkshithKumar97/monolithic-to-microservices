package com.upgrade.jobms.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();

    void createJob(Job job);

    Job getJobById(Long id);

    void deleteJobById(Long id);

    void updateJobById(Long id, Job job);
}
