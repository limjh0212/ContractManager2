package com.bit.contract.service;


import com.bit.contract.domain.entity.MailEntity;
import com.bit.contract.domain.entity.UserEntity;
import com.bit.contract.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    //회원 등록
    @Transactional
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    //로그인
    @Transactional
    public UserEntity login(UserEntity userEntity) {
        UserEntity userEntityCheck = userRepository.findByUsername(userEntity.getUsername());
        if (!passwordEncoder.matches(userEntity.getPassword(), userEntityCheck.getPassword()))
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        return userEntityCheck;
    }

    //ID 중복 조회
    @Transactional
    public String checkId(String username) {
        if (userRepository.existsByUsername(username)) {
            return "이미 존재하는 ID입니다.";
        } else {
            return "사용 가능한 ID입니다.";
        }
    }

    //회원 전체조회
    @Transactional
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    //회원 전체조회
    @Transactional
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    //1:1 문의 메일 전송
    public void mailtoManager(MailEntity mailEntity) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setSubject("[1:1문의] " + mailEntity.getTitle());
            message.setContent("회신받을 메일 : " + mailEntity.getEmail() + "</br> 1:1문의 내용 :" + mailEntity.getContent(), "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("jh.im@kakao.com"));
            mailSender.send(message);
        } catch (
                MessagingException e) {
            e.printStackTrace();
        }
    }
}

