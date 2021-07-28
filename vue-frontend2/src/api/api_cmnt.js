import {instance} from './index';

//댓글저장
export function api_saveCmnt(Data) {
    return instance.post('/api/cmnt/',Data);
}

//댓글삭제
export function api_delCmnt(id) {
    return instance.delete(`/api/cmnt/del/${id}`);
}

//하위 댓글 여부
export function api_checkCmnt2(id) {
    return instance.get(`/api/cmnt/check/${id}`);
}
