package com.yamacbayin.medjourney.database.repository;

import com.yamacbayin.medjourney.database.entity.RoleEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends IBaseRepository<RoleEntity> {

    Optional<RoleEntity> findByName(String name);

}
