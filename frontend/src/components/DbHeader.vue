<template>
    <header style="background-color: rgb(10, 47, 88);">
        <div class="text-logo">{{msg}}</div>
        <p class="meta">致力于打造最快捷的宿舍交互系统。</p>
        <ul class="header-operations">
        </ul>
        <div class="tuichu" v-if="user.jurisdiction">
            <router-link slot="right" to="/signout">退出</router-link>
        </div>
    </header>
</template>

<script>
    import {mapState} from "vuex";
    export default {
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
    header {
        height: 70px;
        position: absolute;
        width: 100%;
        top: 0;
        left: 0;
        padding: 0 20px;
        z-index: 1;
        box-sizing: border-box;
    }

    .text-logo {
        display: inline-block;
        vertical-align: middle;
        border-style: none;
        position: relative;
        top: 26px;
        right: -20px;
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
        color: rgba(192, 192, 197, 0);
        width: 200px;
        display: block;
        margin: -5px 0 0 1100px;
        font-weight: 700;
        font-size: 0.3rem;
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