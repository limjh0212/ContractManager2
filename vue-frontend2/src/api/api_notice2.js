import {instance} from './index';

//알림 추가
export function api_saveNotice(Data) {
    return instance.post('/api/notice/',Data);
}

//알림 삭제
export function api_delNotice(id) {
    return instance.delete(`/api/notice/${id}`);
}

//알림 발송
export function api_sendMail(section, id) {
    return instance.get(`/api/notice/mailsend/${section}/${id}`);
}


