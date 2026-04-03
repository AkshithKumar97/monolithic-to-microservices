package com.upgrade.companyms.company.clients;

import com.upgrade.companyms.company.dto.JobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="job-service")
public interface JobClient {

    //added dependency module of Job
    @GetMapping("/jobs/company/{companyId}")
    List<JobDTO> jobsByCompId(@PathVariable("companyId") Long companyId);

}
