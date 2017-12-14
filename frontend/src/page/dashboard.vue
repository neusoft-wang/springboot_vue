<template>
    <el-col class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span="10">
                <div class="bar">
                    <schart :canvasId="canvasBar"
                            :type="typeBar"
                            :width="widthBar"
                            :height="heightBar"
                            :data="dataBar"
                            :options="optionsBar"
                    ></schart>
                </div>
            </el-col>
            <el-col :span="10">
                <div class="line">
                    <schart :canvasId="canvasLine"
                            :type="typeLine"
                            :width="widthLine"
                            :height="heightLine"
                            :data="dataLine"
                            :options="optionsLine"
                    ></schart>
                </div>
            </el-col>
            <div>
                <div v-on:click="changeData">更改信息</div>
            </div>
            <div>
                <schart :canvasId="canvasSector"
                        :type="typeSector"
                        :width="widthSector"
                        :height="heightSector"
                        :data="dataSector"
                        :options="optionsSector"
                ></schart>
            </div>
        </el-row>
    </el-col>
</template>

<script>
    import DbSidebar from '../components/DbSidebar.vue'
    import ElCol from "element-ui/packages/col/src/col";
    import Schart from 'vue-schart';

    export default {
        name: 'home',
        methods: {
            changeData() {
                // 整个 data 被重新赋值时
                this.dataBar = [
                    {name: '短袖', value: 1300},
                    {name: '休闲裤', value: 1222},
                    {name: '连衣裙', value: 1283},
                    {name: '外套', value: 1314}
                ];
                // data 里一个属性值被修改时
                this.$set(this.data, 1, {name: '短裙', value: 400});
            }
        },
        data() {
            return {
                canvasBar: 'myCanvasBar',
                typeBar: 'bar',
                widthBar: 400,
                heightBar: 250,
                dataBar: [
                    {name: '2014', value: 1342},
                    {name: '2015', value: 2123},
                    {name: '2016', value: 1654},
                    {name: '2017', value: 1795},
                ],
                optionsBar: {
                    title: 'Total sales of Room information in recent years'
                },
                canvasLine: 'myCanvasLine',
                typeLine: 'line',
                widthLine: 400,
                heightLine: 250,
                dataLine: [
                    {name: '2014', value: 1342},
                    {name: '2015', value: 2123},
                    {name: '2016', value: 1654},
                    {name: '2017', value: 1795},
                ],
                optionsLine: {
                    title: 'Total sales of Room information in recent years'
                },
                canvasSector: 'myCanvasSector',
                typeSector: 'sector',
                widthSector: 400,
                heightSector: 300,
                dataSector: [
                    {name: '2014', value: 1342},
                    {name: '2015', value: 2123},
                    {name: '2016', value: 1654},
                    {name: '2017', value: 1795},
                ],
                optionsSector: {
                    title: 'Total sales of Room information in recent years'
                },
                home: 'dashboard',
                canvasId1: 'myCanvasLine',
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

    .bar {
        display: inline-block;
        vertical-align: middle;
        border-style: none;
        position: relative;
        right: -20px;
        font-size: 25px;
        color: white;

    }

    .line {
        color: #7e95c5;
        width: 300px;
        display: block;
        top: 26px;
        font-weight: 700;
        font-size: 0.3rem;
    }
</style>
