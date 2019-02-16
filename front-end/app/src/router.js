import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import AddPost from './components/AddPost.vue'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/addpost',
      name: 'addpost',
      component: AddPost
    }
  ]
})
