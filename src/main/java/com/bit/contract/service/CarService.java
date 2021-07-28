package com.bit.contract.service;

import com.bit.contract.domain.entity.CarEntity;
import com.bit.contract.domain.entity.CmntEntity;
import com.bit.contract.domain.entity.NoticeEntity;
import com.bit.contract.domain.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CmntService cmntService;
    private final NoticeService noticeService;

    //보험 계약 전체 조회
    @Transactional
    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }

    //게시글 등록
    @Transactional
    public void save(CarEntity insuranceEntity) {
        carRepository.save(insuranceEntity);
    }

    //차량 수정조회
    @Transactional
    public Optional<CarEntity> editById(Long id) {
        return carRepository.findById(id);
    }

    //차량 게시글 조회
    @Transactional
    public HashMap<String, Object> findById(Long id, Long userid) {
        HashMap<String, Object> items = new HashMap<>();
        //게시글 정보
        Optional<CarEntity> tempCar = carRepository.findById(id);

        //알림 신청여부
        boolean notice;
        Long noticeid = null;
        if (noticeService.existNotice(3, id, userid)) {
            notice = true;
            NoticeEntity noticeTemp = noticeService.getNotice(3, id, userid);
            noticeid = noticeTemp.getId();
        } else {
            notice = false;
        }

        //댓글
        List<CmntEntity> cmntList = cmntService.getCmntList(3, id, 0);

        //대댓글
        List<List<CmntEntity>> cmnt2List = new ArrayList<>();
        for (CmntEntity cmntEntity : cmntList) {
            List<CmntEntity> temp = cmntService.getCmnt2List(cmntEntity.getId());
            cmnt2List.add(temp);
        }
        items.put("notice", notice);
        items.put("noticeid", noticeid);
        items.put("car", tempCar);
        items.put("cmnt1", cmntList);
        items.put("cmnt2", cmnt2List);

        return items;
    }

    // 삭제
    @Transactional
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
