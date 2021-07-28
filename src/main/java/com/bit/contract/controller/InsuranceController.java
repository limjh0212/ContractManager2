package com.bit.contract.controller;

import com.bit.contract.domain.entity.InsuranceEntity;
import com.bit.contract.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;

    //전체 조회
    @GetMapping("/findall")
    public List<InsuranceEntity> findAll() {
        return insuranceService.findAll();
    }

    //게시글 조회
    @GetMapping("/{id}/{userid}")
    public HashMap<String, Object> findById(@PathVariable Long id, @PathVariable Long userid) {
        return insuranceService.findById(id, userid);
    }

    //게시글 수정조회
    @GetMapping("/{id}")
    public Optional<InsuranceEntity> editById(@PathVariable Long id) {
        return insuranceService.editById(id);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void DeleteById(@PathVariable Long id) {
        insuranceService.deleteById(id);
    }

    //게시글 저장, 수정
    @PostMapping("/save")
    public void save(@RequestBody InsuranceEntity insuranceEntity) {
        insuranceService.save(insuranceEntity);
    }
}
