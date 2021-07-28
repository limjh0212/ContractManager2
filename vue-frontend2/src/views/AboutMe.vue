<template>
    <div align="center">
        <h3>개인 정보 수정</h3>
        <v-card align="center" class="card shadow" style="width: 500px; margin: 40px 20px; padding: 60px 100px">
            <div align="center">
                <form class="row g-3" style="text-align: left;">
                    <v-row>
                        <div class="col-md-16" style="display: block">
                            <label class="form-label" for="username">ID</label>
                            <input id="username" v-model="username" class="form-control" readonly type="text">
                        </div>&nbsp;
                    </v-row>
                    <v-row>
                        <div class="col-md-16" style="display: block">
                            <label class="form-label" for="Password">Password<span
                                style="font-size: 12px; color: red"> *필수</span></label>
                            <input id="Password" v-model="password" class="form-control" type="password">
                        </div>&nbsp;
                    </v-row>
                    <v-row>
                        <div class="col-md-16" style="display: block">
                            <label for="changePassword" class="form-label">Password<span
                                style="font-size: 12px; color: #a8a8a8"> *변경 시</span></label>
                            <input type="password" class="form-control" id="changePassword" v-model="changepassword">
                        </div>&nbsp;
                        <div class="col-md-16" style="display: block">
                            <label for="checkPassword" class="form-label">Password<span
                                style="font-size: 12px; color: #a3a3a3"> *변경 확인</span></label>
                            <input type="password" class="form-control" id="checkPassword" v-model="checkpassword">
                        </div>&nbsp;
                    </v-row>
                    <v-row style="display: flex">
                        <div class="col-md-16">
                            <label class="form-label" for="department">부서명</label>
                            <input id="department" v-model="department" class="form-control"
                                   type="text">
                        </div>&nbsp;
                        <div class="col-md-16">
                            <label class="form-label" for="nickname">이름</label>
                            <input id="nickname" v-model="nickname" class="form-control" type="text">
                        </div>&nbsp;
                        <div class="col-md-16">
                            <label class="form-label" for="email">Email</label>
                            <input id="email" v-model="email" class="form-control" type="text">
                        </div>&nbsp;
                    </v-row>
                    <div align="center" style="font-size: 15px; color: red">
                        {{ logMessage }}
                    </div>
                    <v-row style="margin-top: 40px">
                        <div align="center" style="color: #a0a0a0">
                            <v-btn elevation="2" v-bind:disabled="isPwValid" @click="SAVE_DATA">수정</v-btn>
                            &nbsp;&nbsp;&nbsp;
                            <v-btn elevation="2" @click="goBack">뒤로</v-btn>
                        </div>
                    </v-row>
                </form>

            </div>
        </v-card>
    </div>
</template>

<script>
import {api_getUser, api_login, api_signup} from "@/api/api_user";
import {validEmail} from "@/utils/emailValidation";

export default {
    data() {
        return {
            item          : {},
            id            : '',
            username      : '',
            password      : '',
            changepassword: '',
            checkpassword : '',
            department    : '',
            nickname      : '',
            email         : '',
            logMessage    : '',
        }
    },
    computed: {
        isPwValid() {
            return this.password === '';
        },
    },
    watch   : {
//        changepassword(newMsg) {
//            if (newMsg) {
//                this.logMessage = '비밀번호를 한번 더 입력하세요.'
//            }
//        },
        checkpassword(newMsg) {
            if (newMsg === this.changepassword) {
                this.logMessage = '비밀번호가 일치합니다.'
            } else {
                this.logMessage = '비밀번호가 일치하지 않습니다.'
            }
        }
    },
    methods : {
        async FETCH_DATA() {
            const {data} = await api_getUser(this.$store.state.id);
            this.id = data.id;
            this.username = data.username;
            this.department = data.department;
            this.nickname = data.nickname;
            this.email = data.email;
        },
        async SAVE_DATA() {
            if (this.password === '') {
                this.logMessage = 'Password를 입력하세요.';
            } else if (this.department === '') {
                this.logMessage = '부서명을 입력하세요.';
            } else if (this.nickname === '') {
                this.logMessage = '이름을 입력하세요.';
            } else if (this.email === '') {
                this.logMessage = 'Email을 입력하세요.';
            } else if (!validEmail(this.email)) {
                this.logMessage = 'Email을 확인하세요.'
            } else {
                const checkData = {
                    username: this.username,
                    password: this.password
                };
                const {data} = await api_login(checkData)
                if (data !== '') {
                    if (this.checkpassword !== '') {
                        const Data = {
                            id        : this.id,
                            username  : this.$store.state.username,
                            password  : this.changepassword,
                            department: this.department,
                            nickname  : this.nickname,
                            email     : this.email
                        };
                        await api_signup(Data)
                        await this.$store.commit('clearUserData');
                        await this.$router.push('/login')
                    } else {
                        const Data = {
                            id        : this.id,
                            username  : this.$store.state.username,
                            password  : this.password,
                            department: this.department,
                            nickname  : this.nickname,
                            email     : this.email
                        };
                        await api_signup(Data)
                        await this.$store.commit('clearUserData');
                        await this.$router.push('/login')
                    }
                }
            }
        },
        goBack() {
            this.$router.go(-1);
        }
    },
    created() {
        this.FETCH_DATA();
    }
}
</script>

<style scoped>

</style>