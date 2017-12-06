/**
 * Created by gubaoer on 17/4/18.
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import './css/common.css'
import './less/common.less'
import {
    Button,
    Select,
    Row,
    Col,
    Pagination,
    Table,
    TableColumn,
    Form,
    FormItem,
    Input,
    Dialog,
    Option
} from 'element-ui'
import App from './App.vue'
import 'element-ui/lib/theme-default/index.css'
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'
import router from './router'
import store from './store/'
// more grace import third package !
import moment from 'moment'
import axios from 'axios'
import curvejs from 'curvejs'

Object.defineProperty(Vue.prototype, '$moment', { value: moment });
Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Object.defineProperty(Vue.prototype, '$curvejs', { value: curvejs });

Vue.use(Button);
Vue.use(Select);
Vue.use(Row);
Vue.use(Col);
Vue.use(Pagination);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Dialog);
Vue.use(Option);

locale.use(lang);
Vue.use(VueRouter)

/*router.beforeEach(({meta, path}, from, next) => {
  /!*  var { auth = true } = meta
    var isLogin = Boolean(store.state.user.id) //true用户已登录， false用户未登录*!/

    if (auth && !isLogin && path !== '/index') {
        return next()
    }
    next({ path: '/index' })
})*/

Vue.prototype.$goRoute = function (index) {
    this.$router.push(index)
}

// eslint-disable-next-line no-new
new Vue({
    store,
    el: '#app',
    router,
    render: h => h(App)
});

