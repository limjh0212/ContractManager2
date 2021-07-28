package com.bit.contract.controller;

import com.bit.contract.domain.entity.MailEntity;
import com.bit.contract.domain.entity.UserEntity;
import com.bit.contract.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    //로그인
    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    //회원 등록, 수정
    @PostMapping("/signup")
    public void save(@RequestBody UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userService.save(userEntity);
    }

    //회원 Username 중복 확인
    @GetMapping("/checkId")
    public String checkId(String username) {
        return userService.checkId(username);
    }

    //회원 전체 조회
    @GetMapping("/users")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    //회원 정보 조회
    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    //1:1문의 메일
    @PostMapping("/mailsend/qna")
    public void mailQna(@RequestBody MailEntity mailEntity) {
        userService.mailtoManager(mailEntity);
    }
}
