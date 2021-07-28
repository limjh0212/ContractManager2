import {instance} from './index';

//보험 리스트
export function api_insuranceList() {
    return instance.get('/api/insurance/findall');
}

//보험 상세보기
export function api_getOneInsurance(id, userid) {
    return instance.get(`/api/insurance/${id}/${userid}`);
}

//임대차 수정 조회
export function api_getEditInsurance(id) {
    return instance.get(`/api/insurance/${id}`);
}

//리스트 삭제
export function api_deleteInsurance(id) {
    return instance.delete(`/api/insurance/${id}`);
}

//리스트 등록
export function api_saveInsurance(Data) {
    return instance.post(`/api/insurance/save`, Data);
}
