<template>
    <el-row>
        <el-col :span="4"><div class="grid-content bg-purple text-logo">{{msg}}</div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple-light"> <p style="top: 26px;position: relative;font-size: 15px;
        color: #50bfff;">致力于打造最快捷的宿舍交互系统。</p></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">3</div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple-light">4</div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">5</div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple-light"><div v-if="user.jurisdiction" style="text-align: right; position: relative;top: 35px;right: 10px">
            <router-link  slot="right" to="/signout">退出</router-link>
        </div></div></el-col>
    </el-row>

   <!-- <el-col>
        <el-row>
            <el-col :span="4">
                <header style="background-color: rgb(10, 47, 88);">
                    <div class="text-logo">{{msg}}</div>
                    <p class="meta">致力于打造最快捷的宿舍交互系统。</p>
                    <ul class="header-operations">
                    </ul>
                </header>
            </el-col>
            <el-col span="20">
                <div v-if="user.jurisdiction">
                    <router-link  slot="right" to="/signout">退出</router-link>
                </div>
            </el-col>
        </el-row>
    </el-col>-->

</template>

<script>
    import {mapState} from "vuex";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow},
        computed: mapState({ user: state => state.user }),
        name: 'db-header',
        data () {
            return {
                msg: 'Aurora'
            }
        }
    }
</script>

<style>
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {
        height: 70px;
        background: #7e95c5;
    }
    .bg-purple {
        height: 70px;
        background: #7e95c5;
    }
    .bg-purple-light {
        height: 70px;
        background: #7e95c5;;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .row-bg {
        padding: 10px 0;
        background-color: #7e95c5;
    }
    header {
        height: 70px;
        position: absolute;
        top: 0;
        left: 0;
        padding: 0 20px;
        z-index: 1;
        box-sizing: border-box;
    }
    .text-logo {
        height: 70px;
        display: inline-block;
        vertical-align: middle;
        border-style: none;
        position: relative;
        top: 26px;
        right: -20px;
        font-size: 25px;
        color: white;
        background: #7e95c5;

    }

    .meta {
        color: #7e95c5;
        width: 200px;
        display: block;
        margin: -5px 0 0 225px;
        font-weight: 700;
        font-size: 0.3rem;
    }
    .tuichu {
        width: 200px;
        margin: -5px 0 0 1100px;
        font-weight: 700;
        display: inline-block;
        line-height: 1;
        font-size: 12px;
        color: #768193;
    }
    a:link {
        color:#FF0000;
        text-decoration:underline;
    }
    a:visited {
        color: #12a7ff;
        text-decoration:none;
    }
    a:hover {
        color:#000000;
        text-decoration:none;
    }
    a:active {
        color:#FFFFFF;
        text-decoration:none;
    }
</style>