<template>
    <el-row>
        <el-col :span="4">
            <div class="grid-content bg-purple text-logo">
                <div style="position: relative; top: 20px; text-align: center">{{msg}}</div>
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple-light">
                <p style="top: 26px;position: relative;font-size: 10px;color: #74fff8;">
                    致力于打造最快捷的宿舍交互系统。</p></div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple-light"></div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
            <div class="rid-content bg-purple">
                <div v-if="user.jurisdiction" style="text-align: right; position: relative;top: 35px;right: 10px">
                    <el-dropdown @command="handleCommand">
                    <span class="el-dropdown-link">
                            功能选项<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="/signout">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
        </el-col>
    </el-row>

</template>

<script>
    import {mapState} from "vuex";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow
        },
        computed: mapState({user: state => state.user}),
        name: 'db-header',
        data() {
            return {
                msg: 'Aurora'
            }
        },
        methods: {
            handleCommand(command) {
                this.$router.push({path: command})
            }
        }
    }
</script>

<style>
    .el-dropdown-link {
        cursor: pointer;
        color: #74fff8;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        height: 70px;
        background: #334057;
    }

    .bg-purple {
        height: 70px;
        background: #334057;
    }

    .bg-purple-light {
        height: 70px;
        background: #334057;
    }

    .grid-content {
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #334057;
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
        border-style: none;
        font-size: 25px;
        color: white;

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
        color: #FF0000;
        text-decoration: underline;
    }

    a:visited {
        color: #12a7ff;
        text-decoration: none;
    }

    a:hover {
        color: #000000;
        text-decoration: none;
    }

    a:active {
        color: #FFFFFF;
        text-decoration: none;
    }
</style>