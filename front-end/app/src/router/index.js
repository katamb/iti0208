import Vue from 'vue';
import Router from 'vue-router';
import MainView from '@/components/MainView';
import AddPost from '@/components/AddPost';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainView',
      component: MainView,
    },
    {
      path: '/addpost',
      name: 'AddPost',
      component: AddPost,
    },
  ],
});
