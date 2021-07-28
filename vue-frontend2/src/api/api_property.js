import {instance} from './index';

//임대차 리스트
export function api_propertyList() {
    return instance.get('/api/property/findall');
}

//임대차 상세보기
export function api_getOneProperty(id,userid) {
    return instance.get(`/api/property/${id}/${userid}`);
}

//임대차 수정 조회
export function api_getEditProperty(id) {
    return instance.get(`/api/property/${id}`);
}

//리스트 삭제
export function api_deleteProperty(id) {
    return instance.delete(`/api/property/${id}`);
}

//리스트 등록
export function api_saveProperty(Data) {
    return instance.post(`/api/property/`, Data);
}