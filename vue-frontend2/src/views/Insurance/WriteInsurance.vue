<template>
    <div align="center" style="width: 100%">
        <h3>보험 계약 등록</h3>
        <div class="card shadow" style="width: 750px; margin: 30px; padding: 60px 100px">
            <div align="right">
                <span>작성자 : {{ this.$store.state.department }}&nbsp;{{ this.$store.state.nickname }}</span>
            </div>
            <form class="row g-3" style="text-align: left;">
                <v-row>
                    <div class="col-md-6" style="display: block">
                        <label class="form-label" for="inpustObject">계약 대상</label>
                        <input id="inpustObject" v-model="object" class="form-control" name="object" type="text">
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
                        <label class="form-label" for="inpustDiposit">비용</label>
                        <input id="inpustDiposit" v-model="cost" class="form-control" name="diposit"
                               style="text-align:right;" type="text">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-4">
                        <label class="form-label" for="inpustmonthlyFee">전년 비용</label>
                        <input id="inpustmonthlyFee" v-model="precost" class="form-control" name="monthlyFee"
                               style="text-align:right;" type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-4">
                        <label class="form-label" for="inpustDiposit">보험사</label>
                        <input id="client" v-model="client" class="form-control"
                               name="client" type="text">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-md-4">
                        <label class="form-label" for="inpustmonthlyFee">보험사 연락처</label>
                        <input id="clientNumber" v-model="clientnumber" class="form-control"
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

import {convertDate} from "@/utils/convertDate";
import {api_getEditInsurance, api_saveInsurance} from "@/api/api_insurance";

export default {
    data() {
        return {
            id          : '',
            object      : '',
            startdate   : '',
            enddate     : '',
            cost        : '',
            precost     : '',
            diffcost    : '',
            client      : '',
            clientnumber: '',
        }
    },
    methods: {
        async FETCH_DATA() {
            if (this.$route.params.id !== null) {
                const {data} = await api_getEditInsurance(this.$route.params.id)
                this.id = data.id;
                this.object = data.object;
                this.startdate = convertDate(data.startdate);
                this.enddate = convertDate(data.enddate);
                this.cost = data.cost;
                this.precost = data.precost;
                this.diffcost = data.diffcost;
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
                object      : this.object,
                startdate   : this.startdate,
                enddate     : this.enddate,
                period      : diffTime + '개월',
                cost        : this.cost,
                precost     : this.precost,
                diffcost    : Number(this.cost) - Number(this.precost),
                client      : this.client,
                clientnumber: this.clientnumber,
                user        : {id: this.$store.state.id}
            }
            await api_saveInsurance(Data);
            await this.goBack();
        },
        goBack() {
            this.$router.go(-1);
        },
    },
    created() {
        if (this.$route.path !== '/insurance/write/') {
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