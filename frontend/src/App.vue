<template>
    <el-container class="contatner1">
        <el-header>
            <db-header></db-header>
        </el-header>
        <el-main>
            <router-view></router-view>
        </el-main>
        <el-footer class="el-footer">
            <db-footer></db-footer>
        </el-footer>
    </el-container>
</template>

<script>

    import DbHeader from './components/DbHeader.vue'
    import DbFilterinput from './components/DbFilterinput.vue'
    import DbTable from './components/DbTable.vue'
    import DbFooter from './components/DbFooter.vue'
    import ElRow from "element-ui/packages/row/src/row";

    export default {
        name: 'app',
        components: {
            ElRow, DbHeader,
            DbFilterinput,
            DbTable,
            DbFooter
        }
    }

</script>

<style>

    element.style {
        background-color: rgb(10, 47, 88);
    }

    body {
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
        margin: 0;
        display: flex;
        min-height: 100vh;
        flex-direction: column;
    }

    .el-menu, body, html {
        height: 100%;
    }

    .contatner1{
        background-image: url("../../images/background.png");
        height: 100%;
    }
    .wrapper {

        position: relative;
    }

    footer, div {
        display: block;
    }

    .container {
        /*  padding-top: 35px;*/
        flex: 1;
    }

    .container, .wrapper {
        height: 94%;
    }

    .menu {
        height: 100%;
        background-color: #eef1f6;
    }

    .content {
        padding-right: 25px;
        padding-bottom: 125px;
        padding-left: 25px;
    }

    .footer {
        height: 120px;
        background-color: #324057;
        color: #a4aebd;
        width: 100%;
        z-index: 1000;
        margin-top: -100px;
        line-height: 1;
        font-size: 22px;
    }

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-footer {
        text-align: center;
        background-color: #324057;
        color: #a4aebd;
        z-index: 1000;
        position: fixed;
        bottom: 0px;
        width: 100%;
        left: 0px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

</style>