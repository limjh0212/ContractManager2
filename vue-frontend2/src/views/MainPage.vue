<template>
    <div style="height: 640px">
        <div align="center" class="jumbotron" style="height: 230px">
            <h6 class="display-5">계약관리 시스템</h6>
            <br>
            <h5>계약관리 시스템은 계약에 관한 내용을 공유하고 관리하기 위한 도구입니다. </h5>
            <h5>시스템을 기반으로 신뢰가 바탕이 되는 협업을 지향합니다.</h5>
            <hr class="my-4">
            <p>자세한 내용은 하단 링크를 참조바랍니다.</p>
        </div>

        <div align="center">
            <div class="card shadow" style="width: 20rem; height: 360px">
                <img class="card-img-top" src="https://aoo.myds.me/imgs/1.jpg" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">임대차 계약</h5>
                    <p class="card-text" style="font-size: 14px">본사/직영점/고객지원센터/물류창고 등<br> 부동산 계약관리</p>
                    <v-btn elevation="2" small :href="'/property'">바로가기</v-btn>
                </div>
            </div>
            <div class="card shadow" style="width: 20rem; height: 360px">
                <img class="card-img-top" src="https://aoo.myds.me/imgs/2.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">보험 계약</h5>
                    <p class="card-text" style="font-size: 14px">화재보험/임직원 단체 상해보험 등<br> 보험 계약관리</p>
                    <v-btn elevation="2" small :href="'/insurance'">바로가기</v-btn>
                </div>
            </div>
            <div class="card shadow" style="width: 20rem; height: 360px">
                <img class="card-img-top" src="https://aoo.myds.me/imgs/3.jpg" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">차량 계약</h5>
                    <p class="card-text" style="font-size: 14px">임원용/영업용 리스 및 렌탈차량 등<br> 차량 계약관리</p>
                    <v-btn elevation="2" small :href="'/car'">바로가기</v-btn>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name   : "MainPage",
}
</script>

<style scoped>
.card {
    background-color: whitesmoke;
    margin: 30px;
    width: 23rem;
    display: inline-block;
    height: 220px;
}
</style>