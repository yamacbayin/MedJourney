package com.yamacbayin.medjourney.database.repository;

import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends IBaseRepository<FlightEntity> {
}
