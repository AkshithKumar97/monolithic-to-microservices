package com.upgrade.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobsRepo extends JpaRepository<Job, Long>{
    List<Job> findByCompanyId(Long companyId);
}
