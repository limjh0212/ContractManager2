package com.bit.contract.service;

import com.bit.contract.domain.entity.CmntEntity;
import com.bit.contract.domain.repository.CmntRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CmntService {
    private final CmntRepository cmntRepository;

    //댓글리스트
    public List<CmntEntity> getCmntList(int section, Long secid, int depth) {
        return cmntRepository.findAllBySectionAndSecidAndDepthOrderByIdAsc(section, secid, depth);
    }

    //대댓글 리스트
    public List<CmntEntity> getCmnt2List(Long id) {
        return cmntRepository.findByCmntidOrderById(id);
    }

    //댓글 저장
    public void save(CmntEntity cmntEntity) {
        cmntRepository.save(cmntEntity);
    }

    //댓글 확인
    public boolean check(Long id) {
        return cmntRepository.existsByCmntid(id);
    }

    //댓글 삭제
    public void delCmnt(Long id) {
        cmntRepository.deleteById(id);
    }
}
