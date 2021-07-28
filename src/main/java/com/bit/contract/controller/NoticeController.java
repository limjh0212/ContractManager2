package com.bit.contract.controller;

import com.bit.contract.domain.entity.NoticeEntity;
import com.bit.contract.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private JavaMailSender mailSender;

    //알림 리스트 조회
    @PostMapping("/")
    public void save(@RequestBody NoticeEntity noticeEntity) {
        noticeService.saveNotice(noticeEntity);
    }

    //리스트 삭제
    @DeleteMapping("/{id}")
    public void save(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }

    //알림 발송
    @GetMapping("/mailsend/{section}/{id}")
    public void mailSend(@PathVariable int section, @PathVariable Long id) {
        noticeService.sendMail(section, id);
    }
}
