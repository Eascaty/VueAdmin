import Vue from 'vue'

import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

import request from "./axois";

Vue.prototype.$axios = request
Vue.config.productionTip = false

Vue.use(Element)

require("./mock.js")

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
