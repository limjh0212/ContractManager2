<template>
    <div align="center">
        <div id="login-page" class="content-view" style="width: 400px; margin-top: 100px">
            <div class="col s12 z-depth-4 card-panel">
                <!--Form-->
                <form class="login-form">
                    <div class="input-field col s12 center">
                        <h5>SignUp</h5>
                        <p class="center">Join to our team!</p>
                    </div>
                    <div style="width:100%; margin: auto">
                        <!--ID-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">account_circle</i>
                                <input id="username" v-model="username" name="username" type="text"/>
                                <label for="username">ID</label>
                            </div>
                        </div>

                        <!--PW-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">vpn_key</i>
                                <input id="password" v-model="password" name="password" type="password"/>
                                <label for="password">Password</label>
                            </div>
                        </div>

                        <!--Name-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">face</i>
                                <input id="nickname" v-model="nickname" name="nickname" type="text"/>
                                <label for="nickname">이름</label>
                            </div>
                        </div>

                        <!--Department-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">badge</i>
                                <input id="department" v-model="department" name="department" type="text"/>
                                <label for="department">부서</label>
                            </div>
                        </div>

                        <!--email-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">mail</i>
                                <input id="email" v-model="email" name="email" type="email"/>
                                <label for="email">Email</label>
                            </div>
                        </div>
                    </div>
                    <p style="height: 24px; font-size:15px; color: cadetblue">
                        {{ logMessage }}
                    </p>

                    <div class="row" style="margin-top: 0;margin-bottom: 0; ">
                        <!--Submit 버튼-->
                        <div class="input-field col s12">
                            <button class="btn waves-effect waves-light col s12" @click.prevent="submitForm">Submit
                            </button>
                        </div>

                        <!--Cancel 버튼-->
                        <div class="input-field col s12">
                            <button class="btn waves-effect waves-light col s12" @click.prevent="goBack">Cancel
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import {api_checkId, api_signup} from "@/api/api_user";
import {validEmail} from "@/utils/emailValidation";

export default {
    data() {
        return {
            username  : '',
            password  : '',
            nickname  : '',
            department: '',
            email     : '',
            logMessage: '',
        }
    },
    computed: {
        checkUsername() {
            if (this.username.length > 2)
                return false;
        }
    },
    methods : {
        //Cancel 버튼
        goBack() {
            return this.$router.push('/')
        },
        async submitForm() {
            try {
                const {data} = await api_checkId(this.username)
                if (data === "이미 존재하는 ID입니다.") {
                    this.username = '';
                    this.logMessage = data;
                } else if (this.checkUsername) {
                    this.logMessage = 'ID를 입력하세요';
                } else if (this.password === '') {
                    this.logMessage = 'Password를 입력하세요';
                } else if (this.nickname === '') {
                    this.logMessage = '사용자 이름을 입력하세요.';
                } else if (this.department === '') {
                    this.logMessage = '부서명을 입력하세요.';
                } else if (this.email === '') {
                    this.logMessage = 'Email 주소를 입력하세요.';
                } else if (!validEmail(this.email)) {
                    this.logMessage = 'Email 주소 확인하세요.';
                } else {
                    const userData = {
                        username  : this.username,
                        password  : this.password,
                        nickname  : this.nickname,
                        department: this.department,
                        email     : this.email
                    };
                    await api_signup(userData);
                    await this.$router.push('/');
                }
            } catch (error) {
                this.logMessage = "입력하신 내용을 다시 확인바랍니다.";
                this.pw = ''
            }
        }
    }
}
</script>

<style scoped>
.content-view {
@import "https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css";
}

</style>