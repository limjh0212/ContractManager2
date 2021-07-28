import {instance} from './index';

//차량 리스트
export function api_carList() {
    return instance.get('/api/car/findall');
}

//차량 상세보기
export function api_getOneCar(id, userid) {
    return instance.get(`/api/car/${id}/${userid}`);
}

//차량 수정 조회
export function api_getEditCar(id) {
    return instance.get(`/api/car/${id}`);
}

//리스트 삭제
export function api_deleteCar(id) {
    return instance.delete(`/api/car/${id}`);
}

//리스트 등록
export function api_saveCar(Data) {
    return instance.post(`/api/car/save`, Data);
}