import Vue from 'vue'
import App from './App.vue'
import store from './store'
import './assets/tailwind.css'
import router from './router'
import axios from './plugins/axios.js'

Vue.prototype.axios = axios;


Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
