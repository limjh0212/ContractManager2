package com.bit.contract.domain.repository;

import com.bit.contract.domain.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    boolean existsBySectionAndSectionidAndUser_Id(int section, Long sectionid, Long userid);

    NoticeEntity findBySectionAndSectionidAndUser_Id(int section, Long sectionid, Long userid);

    List<NoticeEntity> findBySectionAndSectionid(int section, Long sectionid);

}
