package com.bit.contract.domain.repository;

import com.bit.contract.domain.entity.CmntEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmntRepository extends JpaRepository<CmntEntity, Long> {
    List<CmntEntity> findAllBySectionAndSecidAndDepthOrderByIdAsc(int section, Long secid, int depth);

    List<CmntEntity> findByCmntidOrderById(Long id);

    boolean existsByCmntid(Long id);
}
