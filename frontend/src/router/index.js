import Vue from 'vue'
import Router from 'vue-router'
import dashboard from '../components/dashboard.vue'
import information from '../components/information.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/dashboard',
            name: 'dashboard',
            component: dashboard
        },
        {
            path: '/information',
            name: 'information',
            component: information
        }
    ]
})
