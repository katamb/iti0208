import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import AddPost from './components/AddPost.vue'
import ViewPost from './components/ViewPost.vue'


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
    },
    {
      path: '/viewpost/:id',
      name: 'viewpost',
      component: ViewPost
    }
  ]
})
