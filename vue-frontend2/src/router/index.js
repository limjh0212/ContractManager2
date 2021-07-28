import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store/index'
import MainPage from "@/views/MainPage";
import ListProperty from "@/views/Property/ListProperty";
import PageProperty from "@/views/Property/PageProperty";
import WriteProperty from "@/views/Property/WriteProperty";
import AboutMe from "@/views/AboutMe";
import LoginPage from "@/views/User/LoginPage";
import SignupPage from "@/views/User/SignupPage";
import PageInsurance from "@/views/Insurance/PageInsurance";
import ListInsurance from "@/views/Insurance/ListInsurance";
import WriteInsurance from "@/views/Insurance/WriteInsurance";
import PageCar from "@/views/Car/PageCar";
import ListCar from "@/views/Car/ListCar";
import WriterCar from "@/views/Car/WriteCar";
import toManager from "@/views/toManager";

Vue.use(VueRouter)

export const router = new VueRouter({
        mode  : "history",
        routes: [
            {
                path     : '/',
                component: MainPage
            },
            {
                path     : '/login',
                component: LoginPage
            },
            {
                path     : '/signup',
                component: SignupPage
            },
            {
                path     : '/mailto',
                component: toManager
            },
            {
                path     : '/property',
                component: ListProperty,
                meta     : {auth: true},
            },
            {
                path     : '/property/post/:id',
                component: PageProperty,
                meta     : {auth: true},
            },
            {
                path     : '/property/edit/:id',
                component: WriteProperty,
                meta     : {auth: true},
            },
            {
                path     : '/property/write/',
                component: WriteProperty,
                meta     : {auth: true},
            },
            {
                path     : '/insurance',
                component: ListInsurance,
                meta     : {auth: true},
            },
            {
                path     : '/insurance/post/:id',
                component: PageInsurance,
                meta     : {auth: true},
            },
            {
                path     : '/insurance/edit/:id',
                component: WriteInsurance,
                meta     : {auth: true},
            },
            {
                path     : '/insurance/write',
                component: WriteInsurance,
                meta     : {auth: true},
            },
            {
                path     : '/car',
                component: ListCar,
                meta     : {auth: true},
            },
            {
                path     : '/car/post/:id',
                component: PageCar,
                meta     : {auth: true},
            },
            {
                path     : '/car/edit/:id',
                component: WriterCar,
                meta     : {auth: true},
            },
            {
                path     : '/car/write',
                component: WriterCar,
                meta     : {auth: true},
            },
            {
                path     : '/aboutme',
                component: AboutMe,
                meta     : {auth: true},
            }
        ]
    })
;

//로그인 검증
router.beforeEach((to, form, next) => {
    if (to.meta.auth && !store.getters.isLogin) {
        next('/login');
        return;
    }
    next();
})

export default router
