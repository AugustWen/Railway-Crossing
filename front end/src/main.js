import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './utils/directives.js'

Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(ElementUI);

new Vue({
    render: h => h(App),
    router
}).$mount('#app')