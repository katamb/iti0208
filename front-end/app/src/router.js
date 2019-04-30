import Vue from 'vue'
import Router from 'vue-router'
import AddPost from './components/AddPost.vue'
import ViewPost from './components/ViewPost.vue'
import Registration from './components/Registration.vue'
import UserActivities from './components/UserActivities.vue'
import ViewPosts from '@/components/ViewPosts.vue'
import ForgotPassword from '@/components/ForgotPassword.vue'
import ResetPassword from '@/components/ResetPassword.vue'


Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: ViewPosts
        },
        {
            path: '/mathematics',
            name: 'Mathematics',
            component: ViewPosts
        },
        {
            path: '/physics',
            name: 'Physics',
            component: ViewPosts
        },
        {
            path: '/chemistry',
            name: 'Chemistry',
            component: ViewPosts
        },
        {
            path: '/biology',
            name: 'Biology',
            component: ViewPosts
        },
        {
            path: '/cs',
            name: 'Computer_Science',
            component: ViewPosts
        },
        {
            path: '/varia',
            name: 'Varia',
            component: ViewPosts
        },
        {
            path: '/addpost',
            name: 'addpost',
            component: AddPost
        },
        {
            path: '/viewpost/:Pid',
            name: 'viewpost',
            component: ViewPost
        },
        {
            path: '/registration/',
            name: 'registration',
            component: Registration
        },
        {
            path: '/search=:searchTerm',
            name: 'search',
            component: ViewPosts
        },
        {
            path: '/userActivities',
            name: 'userActivities',
            component: UserActivities
        },
        {
            path: '/forgotPassword',
            name: 'forgotPassword',
            component: ForgotPassword
        },
        {
            path: '/resetPassword',
            name: 'resetPassword',
            component: ResetPassword
        }
    ]
})
