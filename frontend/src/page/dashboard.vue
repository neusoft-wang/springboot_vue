<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span = "20" class="content">
                <div>
                    <div class="home text-center">{{home}}</div>
                    <div id="app">
                        <schart :canvasId="canvasId"
                                :type="type"
                                :width="width"
                                :height="height"
                                :data="data"
                                :options="options"
                        ></schart>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import DbSidebar  from '../components/DbSidebar.vue'
    import ElCol from "element-ui/packages/col/src/col";
    import Schart from 'vue-schart';
    export default {
        name: 'home',
        methods: {
            changeData(){
                // 整个 data 被重新赋值时
                this.data = [
                    {name:'短袖', value:1200},
                    {name:'休闲裤', value:1222},
                    {name:'连衣裙', value:1283},
                    {name:'外套', value:1314}
                ];
                // data 里一个属性值被修改时
                this.$set(this.data, 1, {name:'短裙',value:500});
            }
        },
        data() {
            return {
                canvasId: 'myCanvas',
                type: 'bar',
                width: 500,
                height: 400,
                data: [
                    {name: '2014', value: 1342},
                    {name: '2015', value: 2123},
                    {name: '2016', value: 1654},
                    {name: '2017', value: 1795},
                ],
                options: {
                    title: 'Total sales of stores in recent years'
                },
                home: 'dashboard'
            }
        },
        components:
            {
                ElCol, DbSidebar, Schart
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
