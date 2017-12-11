<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span = "20" class="content">
                <div>
                    <div class="home text-center">{{home}}</div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import DbSidebar  from '../components/DbSidebar.vue'
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        name: 'home',
        data () {
            return {
                home: 'dashboard'
            }
        },
        components:
            {
                ElCol, DbSidebar
            }
    }
</script>

<style>
    .home {
        margin-top: 10%;
        font-size: 2.4em;
        font-weight: bold;
    }
</style>
