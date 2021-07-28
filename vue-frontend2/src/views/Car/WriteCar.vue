<template>
    <div align="center" style="width: 100%">
        <h3>차량 계약 등록</h3>
        <div class="card shadow" style="width: 750px; margin: 30px; padding: 60px 100px">
            <div align="right">
                <span>작성자 : {{ this.$store.state.department }}&nbsp;{{ this.$store.state.nickname }}</span>
            </div>
            <form class="row g-3" style="text-align: left;">
                <v-row>
                    <div class="col-md-3">
                        <label class="form-label" for="inpustDivision">구분</label>
                        <input id="inpustDivision" v-model="division" class="form-control" name="object" type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inpustObject">차량 정보</label>
                        <input id="inpustObject" v-model="object" class="form-control" name="object" type="text">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inpustCarnum">차량 번호</label>
                        <input id="inpustCarnum" v-model="carnum" class="form-control" name="object" type="text">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-3" style="display: block">
                        <label class="form-label" for="inpustCaruser">사용자 </label>
                        <input id="inpustCaruser" v-model="caruser" class="form-control" name="object" type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inputStardDate">계약 시작일</label>
                        <input id="inputStardDate" v-model="startdate" class="form-control" name="startDate"
                               type="date">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inputEndDate">계약 종료일</label>
                        <input id="inputEndDate" v-model="enddate" class="form-control" name="endDate" type="date">
                    </div>&nbsp;&nbsp;
                </v-row>
                <v-row>

                    <div class="col-md-4">
                        <label class="form-label" for="inpustmonthlyFee">월 비용</label>
                        <input id="inpustmonthlyFee" v-model="monthlyfee" class="form-control" name="monthlyFee"
                               style="text-align:right;" type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-4">
                        <label class="form-label" for="inpustClient">업체명</label>
                        <input id="inpustClient" v-model="client" class="form-control"
                               name="client" type="text">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-4">
                        <label class="form-label" for="inputClientNumber">업체 전화번호</label>
                        <input id="inputClientNumber" v-model="clientnumber" class="form-control"
                               name="clientNumber" type="text">
                    </div>
                </v-row>
                <!--File Upload-->
                <v-row style="margin: 30px 0px">
                    <!--    <div class="input-group">-->
                    <!--        <input type="file" name="files" class="form-control" id="inputGroupFile04"-->
                    <!--               aria-describedby="inputGroupFileAddon04" aria-label="Upload">-->
                    <!--        <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04" @onclick="">Upload-->
                    <!--        </button>-->
                    <!--    </div>-->
                </v-row>
            </form>
            <div align="center" style="color: #a0a0a0">
                <v-btn elevation="2" @click="SAVE_DATA">등록</v-btn>
                &nbsp;&nbsp;&nbsp;
                <v-btn elevation="2" @click="goBack">뒤로</v-btn>
            </div>

        </div>
    </div>
</template>

<script>

import {api_getEditProperty} from "@/api/api_property";
import {convertDate} from "@/utils/convertDate";
import {api_getEditCar, api_saveCar} from "@/api/api_car";

export default {
    data() {
        return {
            id          : '',
            division    : '',
            object      : '',
            carnum      : '',
            caruser     : '',
            startdate   : '',
            enddate     : '',
            monthlyfee  : '',
            client      : '',
            clientnumber: '',
        }
    },
    methods: {
        async FETCH_DATA() {
            if (this.$route.params.id !== null) {
                const {data} = await api_getEditCar(this.$route.params.id)
                this.id = data.id;
                this.division = data.division;
                this.object = data.object;
                this.carnum = data.carnum;
                this.caruser = data.caruser;
                this.startdate = convertDate(data.startdate);
                this.enddate = convertDate(data.enddate);
                this.deposit = data.deposit;
                this.monthlyfee = data.monthlyfee;
                this.client = data.client;
                this.clientnumber = data.clientnumber;
            }
        },

        async SAVE_DATA() {
            let start = new Date(this.startdate);
            let end = new Date(this.enddate);

            let diffTime = Math.floor((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24 * 30))
            const Data = {
                id          : this.id,
                division    : this.division,
                object      : this.object,
                carnum      : this.carnum,
                caruser     : this.caruser,
                startdate   : this.startdate,
                enddate     : this.enddate,
                period      : diffTime + '개월',
                monthlyfee  : this.monthlyfee,
                client      : this.client,
                clientnumber: this.clientnumber,
                user        : {id: this.$store.state.id},
            }
            await api_saveCar(Data);
            await this.goBack();
        },
        goBack() {
            this.$router.go(-1);
        },
    },
    created() {
        if (this.$route.path !== '/car/write/') {
            this.FETCH_DATA();
        }
    }
}
</script>

<style scoped>
template {
    font-family: 'Noto Sans KR', sans-serif;
}
</style>