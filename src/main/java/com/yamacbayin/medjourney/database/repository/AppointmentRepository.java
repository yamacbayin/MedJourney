package com.yamacbayin.medjourney.database.repository;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends IBaseRepository<AppointmentEntity> {

    List<AppointmentEntity> findAllByStatus(AppointmentStatusEnum status);

}
