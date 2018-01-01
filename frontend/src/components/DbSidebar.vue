<template>
    <div>
        <ul>
            <li class="el-menu-item is-active" style="text-align: center"  v-for="item in links"  v-if="item.qiana === user.jurisdiction || item.qiana ==='1' " >
                <div @click="$goRoute(item.route)">
                    <a >{{item.text}}</a>
                </div>
            </li>
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
                        qiana:'1'
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
                        qiana:'Admin'
                    }
                    ,
                    {
                        text: 'Student',
                        route: '/student',
                        qiana:'1'
                    },
                    {
                        text: 'stuHistory',
                        route: '/stuHistory',
                        qiana:'Student'
                    },
                    {
                        text: 'showStudent',
                        route: '/showStudent',
                        qiana:'Teacher'
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
