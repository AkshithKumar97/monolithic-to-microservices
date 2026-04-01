package com.upgrade.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepo extends JpaRepository<Job, Long>{
}
