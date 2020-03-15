package com.rvb.springwithapectlog.repository;

import com.rvb.springwithapectlog.model.SysLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogRepository extends CrudRepository<SysLog, Long> {
}
