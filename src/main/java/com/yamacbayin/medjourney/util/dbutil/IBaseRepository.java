package com.yamacbayin.medjourney.util.dbutil;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface IBaseRepository<E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

    Optional<E> findByUuid(UUID uuid);

    @Transactional
    void deleteByUuid(UUID uuid);

}
