# Contract Manager

## 기획의도 
- 2020년 네파 인사총무팀 재직 시, 2021년 사업계획으로 계약 관리 시스템 개발 선정
- 2020년 12월 말 개인 사유로 개발 미진행
- 실무에서 불편을 느꼈던 부분을 직접 개선해보고자 프로젝트를 진행함

## 주요기능
- 계약현황 조회
  - 계약정보 조회/검색 기능
  - Restful 배포로 디바이스 제약조건 완화


- 알림메일 발송 기능
  - 알림 신청 인원에 대해 일괄 메일 발송 기능 구현  


- 댓글/대댓글 기능 구현
  - 담당자 간 계약 요청사항 공유 
  - 의견 조율 내용 이력관리 가능  


- 권한별 기능 제한
  - 관리자/사용자(공람자) 권한에 맞는 기능 제한 부여

## 개발스택
- Backend : Java / Spring Boot / Spring Security / Gradle / Tomcat / MariaDB
- Frontend : Html5 / CSS / JS / Vue.js / Npm / Bootstrap
- Others : Git / Git hub / Docker / Intellij / Notion / Slack

## Web Service
- 서비스 URL : http://aoo.myds.me:8080/ 
- Test계정(ID/PW)
  - 관리자 : admin / admin
  - 사용자 : user / user
  - 추가 계정 생성 가능