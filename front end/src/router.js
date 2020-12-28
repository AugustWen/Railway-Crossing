import Vue from "vue";
import Router from "vue-router";

import Login from '@/components/Login'
import Home from '@/components/Home'
import LingHandle from '@/components/LingHandle'
import VillageHandle from '@/components/VillageHandle'
import OilHandle from '@/components/OilHandle'

Vue.use(Router)

export default new Router({
    routes: [
        { path: '/', redirect: '/Login' },
        { path: '/Login', component: Login },
        { path: '/Home', component: Home },
        { path: '/LingHandle', component: LingHandle },
        { path: '/VillageHandle', component: VillageHandle },
        { path: '/OilHandle', component: OilHandle },
    ],
    mode: 'history'
})