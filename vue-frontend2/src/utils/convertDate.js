export function convertDate(milliSecond) {
    const days = ['일', '월', '화', '수', '목', '금', '토'];
    const data = new Date(milliSecond);  //Date객체 생성
    const year = data.getFullYear();    //0000년 가져오기
    const month = data.getMonth() + 1;  //월은 0부터 시작하니 +1하기
    const date = data.getDate();        //일자 가져오기
    const day = days[data.getDay()];    //요일 가져오기
    if (month < 10 && date < 10) {
        return `${year}-0${month}-0${date}`;
    } else if (month < 10) {
        return `${year}-0${month}-${date}`;
    } else if (date < 10) {
        return `${year}-${month}-0${date}`;
    } else {
        return `${year}-${month}-${date}`;
    }
}