<template>
    <div align="center" class="content-view" style="margin-top: 200px">
        <div id="login-page" class="content-view" style="width: 400px; height: 700px;">
            <div class="col s12 z-depth-4 card-panel">
                <!--Form-->
                <form class="login-form">
                    <div class="input-field col s12 center">
                        <h5>Login</h5>
                        <p class="center">Login our system now!</p>
                    </div>
                    <div style="width:100%; margin: auto">
                        <!--ID-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">account_circle</i>
                                <input id="id" v-model="username" name="id" type="text"/>
                                <label for="id">ID</label>
                            </div>
                        </div>

                        <!--PW-->
                        <div class="row margin">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">vpn_key</i>
                                <input id="pw" v-model="password" name="pw" type="password"/>
                                <label for="pw">Password</label>
                            </div>
                        </div>
                    </div>
                    <p style="height: 24px; font-size:15px; color: cadetblue">
                        {{ logMessage }}
                    </p>

                    <div class="row" style="margin-top: 0px;margin-bottom: 0px; ">
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

export default {
    data() {
        return {
            logMessage: '',
            username  : '',
            password  : '',
        }
    },
    methods : {
        //Cancel 버튼
        async submitForm() {
            try {
                const userData = {
                    username: this.username,
                    password: this.password,
                };
                await this.$store.dispatch('LOGIN', userData);
                await this.$router.push('/');
            } catch (error) {
                this.logMessage = '로그인에 실패했습니다. 다시 시도해주세요.';
                this.pw = '';
            }
        },
        goBack() {
            this.$router.push('/')
        }
    },
    computed: {
        isUrlCheck() {
            if (this.$route.path !== '/login' && this.$route.path !== '/signup') {
                return true;
            } else {
                return false;
            }
        },
    }
};
</script>
<style scoped>
.content-view{
    @import "https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css";
}
</style>