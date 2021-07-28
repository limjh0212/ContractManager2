<template>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 50px">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">계약관리 시스템</a>
            <button aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"
                    data-bs-target="#navbarNav" data-bs-toggle="collapse" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarNav" class="d-flex">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a aria-current="page" class="nav-link" href="/">Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/property">임대차 계약</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/insurance">보험 계약</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/car">차량 계약</a>
                    </li>

                    <div v-if="isUserLogin" style="display: flex">
                        <li class="nav-item">
                            <a class="nav-link" href="/aboutme">내 정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" @click="logoutUser">Logout</a>
                        </li>
                    </div>
                    <div v-else style="display: flex">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/signup">Signup</a>
                        </li>
                    </div>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    methods : {
        async logoutUser() {
            await this.$store.commit('clearUserData');
            await this.$router.push('/')
        },
    },
    computed: {
        isUserLogin() {
            return this.$store.getters.isLogin;
        },
    }
}
</script>

<style scoped>
</style>