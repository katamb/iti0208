import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VeeValidate from 'vee-validate'
import VueSwal from 'vue-swal'

Vue.use(VueSwal);

Vue.config.productionTip = false;
Vue.use(VeeValidate);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
