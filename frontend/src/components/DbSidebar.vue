<template>
    <div>
        <ul>
            <li class="el-menu-item is-active" style=""  v-for="item in links">
                <a @click="$goRoute(item.route)">{{item.text}}</a></li>
        </ul>
    </div>
</template>

<script>

    export default {
        name: 'db-sidebar',
        data () {
            return {
                links: [
                    {
                        text: 'Dashboard',
                        route: '/dashboard'
                    },
                    {
                        text: 'Information',
                        route: '/information'
                    },
                    {
                        text: 'Dormitory',
                        route: '/dormitory'
                    }
                ]
            }
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
