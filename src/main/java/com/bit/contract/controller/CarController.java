package com.bit.contract.controller;

import com.bit.contract.domain.entity.CarEntity;
import com.bit.contract.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    //전체 조회
    @GetMapping("/findall")
    public List<CarEntity> findAll() {
        return carService.findAll();
    }

    //저장, 수정
    @PostMapping("/save")
    public void save(@RequestBody CarEntity carEntity) {
        carService.save(carEntity);
    }

    //게시글 조회
    @GetMapping("/{id}/{userid}")
    public HashMap<String, Object> findById(@PathVariable Long id, @PathVariable Long userid) {
        return carService.findById(id, userid);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void DeleteById(@PathVariable Long id) {
        carService.deleteById(id);
    }

    //게시글 수정 조회
    @GetMapping("/{id}")
    public Optional<CarEntity> editById(@PathVariable Long id) {
        return carService.editById(id);
    }
}
