<template>
    <div>
        <div v-if="isUrlCheck">
            <MainNav></MainNav>
        </div>
        <router-view></router-view>
        <div v-if="isUrlCheck">
            <Footer></Footer>
        </div>
    </div>
</template>
<script>
import MainNav from "@/components/MainNav";
import Footer from "@/components/Footer";

export default {
    components: {Footer, MainNav},
    computed  : {
        isUrlCheck() {
            if (this.$route.path !== '/login' && this.$route.path !== '/signup') {
                return true;
            } else {
                return false;
            }
        },
    }
}
</script>
<style>
body {
    font-family: 'Noto Sans KR', sans-serif;
}
</style>
