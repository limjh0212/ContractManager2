package com.bit.contract.service;

import com.bit.contract.domain.entity.CmntEntity;
import com.bit.contract.domain.entity.NoticeEntity;
import com.bit.contract.domain.entity.PropertyEntity;
import com.bit.contract.domain.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final NoticeService noticeService;
    private final CmntService cmntService;

    //임대차 계약 전체 조회
    @Transactional
    public List<PropertyEntity> findAll() {
        return propertyRepository.findAll();
    }

    //임대차 계약 게시글 조회
    @Transactional
    public HashMap<String, Object> findById(Long id, Long userid) {
        HashMap<String, Object> items = new HashMap<>();
        //게시글 정보
        Optional<PropertyEntity> tempProperty = propertyRepository.findById(id);

        //알림 신청여부
        boolean notice;
        Long noticeid = null;
        if (noticeService.existNotice(1, id, userid)) {
            notice = true;
            NoticeEntity noticeTemp = noticeService.getNotice(1, id, userid);
            noticeid = noticeTemp.getId();
        } else {
            notice = false;
        }

        //댓글
        List<CmntEntity> cmntList = cmntService.getCmntList(1, id, 0);

        //대댓글
        List<List<CmntEntity>> cmnt2List = new ArrayList<>();
        for (CmntEntity cmntEntity : cmntList) {
            List<CmntEntity> temp = cmntService.getCmnt2List(cmntEntity.getId());
            cmnt2List.add(temp);
        }
        items.put("notice", notice);
        items.put("noticeid", noticeid);
        items.put("property", tempProperty);
        items.put("cmnt1", cmntList);
        items.put("cmnt2", cmnt2List);

        return items;
    }

    //임대차 계약 게시글 수정 조회
    @Transactional
    public Optional<PropertyEntity> editById(Long id) {
        return propertyRepository.findById(id);
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }

    //게시글 등록
    @Transactional
    public void save(PropertyEntity propertyEntity) {
        propertyRepository.save(propertyEntity);
    }
}
