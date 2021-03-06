import Vue from 'vue'
import Router from 'vue-router'
import dashboard from '../page/dashboard.vue'
import information from '../page/information.vue'
import dormitory from '../page/dormitory.vue'
import student from '../page/student.vue'
import teacher from '../page/teacher.vue'
import index from '../pages/index/index.vue'
import home from '../pages/home/index.vue'
import login from '../pages/login/index.vue'
import signout from '../pages/signout/index.vue'
import register from '../pages/register/register.vue'
import stuHistory from '../page/stuHistory.vue'
import showStudent from '../page/showStudent.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/dashboard',
            name: 'dashboard',
            component: dashboard
        },
        {
            path: '/Change_Password',
            name: 'information',
            component: information
        },
        {
            path: '/teacher',
            name: 'teacher',
            component: teacher
        },
        {
            path: '/student',
            name: 'student',
            component: student
        }
        ,
        {
            path:'/stuHistory',
            name:'stuHistory',
            component: stuHistory
        }
        ,
        {
            path:'/showStudent',
            name:'showStudent',
            component: showStudent
        }
        ,
        {
            path: '/dormitory',
            name: 'dormitory',
            component: dormitory
        },
        {
            path: '/login',
            name: 'login',
            component: login
        }
        ,
        {
            path: '/home',
            name: 'home',
            component: home
        }
        ,
        {
            path: '/index',
            name: 'index',
            component: index
        }
        ,
        {
            path: '/',
            name: 'index',
            component: index
        }
        ,
        {
            path: '/signout',
            name: 'signout',
            component: signout
        }
        ,
        {
            path: '/register',
            name: 'register',
            component: register
        }
        ]
})
