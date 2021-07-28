package com.bit.contract.service;

import com.bit.contract.domain.entity.*;
import com.bit.contract.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeService {

    private final PropertyRepository propertyRepository;
    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    //알림 저장
    public void saveNotice(NoticeEntity noticeEntity) {
        noticeRepository.save(noticeEntity);
    }

    //알림 삭제
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    //알림 저장여부 확인
    public boolean existNotice(int section, Long sectionid, Long userid) {
        return noticeRepository.existsBySectionAndSectionidAndUser_Id(section, sectionid, userid);
    }

    //알림 조회
    public NoticeEntity getNotice(int section, Long sectionid, Long userid) {
        return noticeRepository.findBySectionAndSectionidAndUser_Id(section, sectionid, userid);
    }

    //메일 발송
    public void sendMail(int section, Long sectionid) {
        List<NoticeEntity> getlist = noticeRepository.findBySectionAndSectionid(section, sectionid);

        String titlehead;
        String getObject;
        String mailsec;
        if (section == 1) {
            PropertyEntity temp = propertyRepository.getById(sectionid);
            mailsec = "property";
            titlehead = "임대차";
            getObject = temp.getObject();

        } else if (section == 2) {
            mailsec = "insurance";
            titlehead = "보험";
            InsuranceEntity temp = insuranceRepository.getById(sectionid);
            getObject = temp.getObject();
        } else {
            mailsec = "car";
            titlehead = "차량";
            CarEntity temp = carRepository.getById(sectionid);
            getObject = temp.getObject();
        }

        for (int i = 0; i < getlist.size(); i++) {
            Optional<UserEntity> temp = userRepository.findById(getlist.get(i).getUser().getId());
            MimeMessage message = mailSender.createMimeMessage();
            try {
                message.setSubject("[알림] " + titlehead + ' ' + getObject + " 계약 정보가 변경되었습니다.");
                message.setContent("<h4>" + titlehead + ' ' + getObject + " 계약정보가 변경되었습니다.</h4><br>\n" +
                        "<a href=\"http://aoo.myds.me:8080/" + mailsec + "/post/" + sectionid + "/\">바로가기</a>", "text/html; charset=utf-8");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(temp.get().getEmail()));
                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}

