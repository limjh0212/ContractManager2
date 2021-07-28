<template>
    <div align="center" style="width: 100%">
        <h3>1:1 문의</h3>
        <div class="card shadow" style="width: 750px; margin: 30px; padding: 60px 100px">
            <form class="row g-3" style="text-align: left;">
                <v-row>
                    <div class="col-md-6" style="display: block">
                        <label class="form-label" for="inpustObject">제목</label>
                        <input id="inpustObject" v-model="title" class="form-control" name="text" type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-6" style="display: block">
                        <label class="form-label" for="inputStardDate">Email</label>
                        <input id="inputStardDate" v-model="email" class="form-control" name="email"
                               type="email">
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                </v-row>
                <v-row>
                    <div class="form-group">
                        <label class="form-label">내용</label>
                        <textarea class="form-control" rows="10" style="resize: none" v-model="content"></textarea>
                    </div>
                    <div align="center" style="color: red; margin-top: 10px; font-size: 14px"> {{ logMessage }}</div>
                </v-row>
                <div align="center" style="color: #a0a0a0; margin-top: 25px">
                    <v-btn elevation="2" @click="SAVE_DATA">등록</v-btn>
                    &nbsp;&nbsp;&nbsp;
                    <v-btn elevation="2" @click="goBack">뒤로</v-btn>
                </div>
            </form>
        </div>
    </div>
</template>

<script>

import {api_mailQna} from "@/api/api_user";
import {validEmail} from "@/utils/emailValidation";

export default {
    data() {
        return {
            title     : '',
            email     : '',
            content   : '',
            logMessage: '',

        }
    },
    methods: {
        async SAVE_DATA() {
            if (this.title === '') {
                this.logMessage = "제목을 입력하세요."
            } else if (this.email === '') {
                this.logMessage = "Email 주소를 입력하세요.";
            } else if (!validEmail(this.email)) {
                this.logMessage = 'Email 주소를 확인하세요.';
            } else {
                const Data = {
                    title  : this.title,
                    email  : this.email,
                    content: this.content
                }
                await api_mailQna(Data);
                alert('메일이 발송되었습니다.')
                this.goBack();
            }
        },
        goBack() {
            this.$router.go(-1);
        },
    },
}
</script>

<style scoped>
template {
    font-family: 'Noto Sans KR', sans-serif;
}
</style>