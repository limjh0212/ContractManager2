import {instance} from './index';

//로그인
export function api_login(userData) {
    return instance.post('/api/user/login', userData);
}

//회원등록
export function api_signup(userData) {
    return instance.post('/api/user/signup', userData);
}

//ID 중복조회
export function api_checkId(username) {
    return instance.get(`/api/user/checkId?username=${username}`);
}

//회원 Id조회
export function api_getUser(userid) {
    return instance.get(`/api/user/${userid}`);
}

//1:1메일 발송
export function api_mailQna(Data) {
    return instance.post('/api/user/mailsend/qna', Data);
}