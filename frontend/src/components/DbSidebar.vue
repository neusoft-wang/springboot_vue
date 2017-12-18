<template>
    <div>
        <ul>
            <li class="el-menu-item is-active" style=""  v-for="item in links"  v-if="item.text === user.jurisdiction || item.qiana ==='1' " >
                <a @click="$goRoute(item.route)">{{item.text}}</a></li>
        </ul>
    </div>
</template>

<script>

    import {mapState} from "vuex";

    export default {
        computed: mapState({ user: state => state.user }),
        name: 'db-sidebar',
        data () {
            return {
                links: [
                    {
                        text: 'Dashboard',
                        route: '/dashboard',
                        qiana:'1'

                    },
                    {
                        text: 'Information',
                        route: '/information',
                        qiana:'Teacher'
                    },
                    {
                        text: 'Dormitory',
                        route: '/dormitory',
                        qiana:'1'
                    }
                    ,
                    {
                        text: 'Teacher',
                        route: '/teacher',
                        qiana:'Teacher'
                    }
                    ,
                    {
                        text: 'Student',
                        route: '/student',
                        qiana:'1'
                    }]}
        },
        methods: {
            fun() {
                alert("hello");
            }
        }
    }

</script>

<style>
    li {
        padding-left: 20px;
    }
</style>
