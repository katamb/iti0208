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
      path: '/mathematics',
      name: 'Mathematics',
      component: Home
    },
    {
      path: '/physics',
      name: 'Physics',
      component: Home
    },
    {
      path: '/chemistry',
      name: 'Chemistry',
      component: Home
    },
    {
      path: '/biology',
      name: 'Biology',
      component: Home
    },
    {
      path: '/cs',
      name: 'Computer_Science',
      component: Home
    },
    {
      path: '/varia',
      name: 'Varia',
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
