package com.bit.contract.controller;

import com.bit.contract.domain.entity.CmntEntity;
import com.bit.contract.service.CmntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cmnt")
public class CmntController {
    private final CmntService cmntService;

    //댓글 작성, 수정
    @PostMapping("/")
    public void saveCmnt(@RequestBody CmntEntity cmntEntity) {
        cmntService.save(cmntEntity);
    }

    //댓글 작성 여부
    @GetMapping("/check/{id}")
    public boolean check(@PathVariable Long id) {
        return cmntService.check(id);
    }

    //댓글 삭제
    @DeleteMapping("/del/{id}")
    public void delCmnt(@PathVariable Long id) {
        cmntService.delCmnt(id);
    }
}
