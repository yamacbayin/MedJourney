package com.yamacbayin.medjourney.database.repository;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends IBaseRepository<AppointmentEntity> {
}
