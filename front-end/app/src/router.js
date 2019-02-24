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
      path: '/mathematics',
      name: 'Mathematics',
      props: 'mathematics',
      component: Home
    },
    {
      path: '/physics',
      name: 'Physics',
      props: 'physics',
      component: Home
    },
    {
      path: '/chemistry',
      name: 'Chemistry',
      props: 'chemistry',
      component: Home
    },
    {
      path: '/biology',
      name: 'Biology',
      props: 'biology',
      component: Home
    },
    {
      path: '/cs',
      name: 'Computer Science',
      props: 'computer_science',
      component: Home
    },
    {
      path: '/varia',
      name: 'Varia',
      props: 'varia',
      component: Home
    },
    {
      path: '/addpost',
      name: 'addpost',
      component: AddPost
    }
  ]
})
