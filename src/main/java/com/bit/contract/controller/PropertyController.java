package com.bit.contract.controller;

import com.bit.contract.domain.entity.PropertyEntity;
import com.bit.contract.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    //전체 조회
    @GetMapping("/findall")
    public List<PropertyEntity> findAll() {
        return propertyService.findAll();
    }

    //게시글 조회
    @GetMapping("/{id}/{userid}")
    public HashMap<String, Object> findById(@PathVariable Long id, @PathVariable Long userid) {
        return propertyService.findById(id, userid);
    }

    //수정 조회
    @GetMapping("/{id}")
    public Optional<PropertyEntity> editById(@PathVariable Long id) {
        return propertyService.editById(id);
    }

    //삭제
    @DeleteMapping("/{id}")
    public void DeleteById(@PathVariable Long id) {
        propertyService.deleteById(id);
    }

    //저장, 수정
    @PostMapping("/")
    public void save(@RequestBody PropertyEntity propertyEntity) {
        propertyService.save(propertyEntity);
    }
}
