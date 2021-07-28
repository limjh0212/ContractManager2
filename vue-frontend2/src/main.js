import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store/index'
import vuetify from "@/utils/vuetify";
import {convertDate} from "@/utils/convertDate";
import {moment} from "@/utils/moment";

Vue.config.productionTip = false


new Vue({
    render: h => h(App),
    router,
    store,
    vuetify,
    convertDate,
    moment,
}).$mount('#app')

