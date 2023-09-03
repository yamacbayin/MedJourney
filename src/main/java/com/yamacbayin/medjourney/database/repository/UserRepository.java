package com.yamacbayin.medjourney.database.repository;

import com.yamacbayin.medjourney.database.entity.UserEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends IBaseRepository<UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}
