import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import {api_login} from "@/api/api_user";

Vue.use(Vuex)

export default new Vuex.Store({
    plugins  : [createPersistedState()],
    state    : {
        id        : '',
        username  : '',
        nickname  : '',
        department: '',
        email     : '',
    },
    mutations: {
        setUserData(state, userData) {
            state.id = userData.id;
            state.username = userData.username;
            state.nickname = userData.nickname;
            state.department = userData.department;
            state.email = userData.email;
        },
        clearUserData(state) {
            state.id = ''
            state.username = ''
            state.nickname = ''
            state.department = ''
            state.email = ''
        }
    },
    actions  : {
        async LOGIN({commit}, userData) {
            const {data} = await api_login(userData);
            commit('setUserData', data);
            return data;
        },
    },
    getters  : {
        isLogin(state) {
            return state.id !== '';
        },
        convertDate(milliSecond) {
            const days = ['일', '월', '화', '수', '목', '금', '토'];
            const data = new Date(milliSecond);  //Date객체 생성

            const year = data.getFullYear();    //0000년 가져오기
            const month = data.getMonth() + 1;  //월은 0부터 시작하니 +1하기
            const date = data.getDate();        //일자 가져오기
            const day = days[data.getDay()];    //요일 가져오기

            return `${year}-${month}-${date}`;
        }
    }
})
