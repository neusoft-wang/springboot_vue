<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span="20" class="content">
                <div style="margin-top: 18px">
                </div>
                <el-table
                        :data="tableData"
                        border
                        style="width: 70%"
                        class="table"
                        total ='1'>

                    <el-table-column
                            fixed
                            prop="id"
                            label="item_id"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="name"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="dor"
                            label="dor"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="description"
                            label="description"
                            width="130">
                    </el-table-column>
                    <el-table-column
                            prop="create_datetime"
                            label="create_datetime"
                            width="250">
                    </el-table-column>
                    <el-table-column
                            prop="state"
                            label="state"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="Operation"
                            width="80">
                        <template scope="scope" v-if="active === 2">
                            <el-button @click="pay()" type="text" size="large">Pay</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin-top: 18px">
                </div>
                <el-steps :space="386" :active="active" :finish-status="status" >
                    <el-step title="已申请"></el-step>
                    <el-step title="待审核"></el-step>
                    <el-step title="审核结果"></el-step>
                </el-steps>
                <el-dialog title="Pay" v-model="dialogFormDor">
                    <div style="text-align: center">
                        <img width="180" :src="payImg" alt=""> <br>
                    </div>
                    <div slot="footer" class="dialog-footer">
                        <el-button :plain="true" type="danger" v-on:click="confirm()">confirm</el-button>
                    </div>
                </el-dialog>
            </el-col>
        </el-row>


    </div>


</template>

<script>
    import DbSidebar  from '../components/DbSidebar.vue'
    import {mapState} from "vuex"
    import payImg from '../pages/home/pay.png';
    export default {
        computed: mapState({user: state => state.user}),
        components: {
            DbSidebar
        },
        data(){
            return {
                status:'success',
                active:'1',
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/Student/showHistory',
                total: 1,
                pageSize: 0,
                currentPage: 0,
                dialogFormDor: false,
                dialogFormVisible: false,
                form: '',
                formLabelWidth :'120px',
                payImg,
            }
        },
        mounted () {
            this.getStatus();
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = data.total_pages;
                this.pageSize = data.count;
            });
        },
        methods: {
            getCustomers: function () {
                this.$axios.get(this.apiUrl, {
                    params: {
                        username:this.user.username
                    }
                }).then((response) => {
                    this.tableData = response.data.data.results;
                    this.total = response.data.data.total;
                    this.pageSize = response.data.data.count;
                    console.log(response.data.data);
                }).catch(function (response) {
                    console.log(response)
                });
            },
            pay: function () {
                this.dialogFormDor = true;
            },
            confirm: function () {
                this.dialogFormDor = false;
                this.active = '3';
                this.$axios.get('http://127.0.0.1:8000/api/Student/changeStatus', {
                    params: {
                        username: this.user.username,
                        status: '3'
                    }
                }).then((response) => {
                    console.log(response.data);
                }).catch(function (response) {
                    console.log(response)
                });
            },
            getStatus:function(){
                this.$axios.get('http://127.0.0.1:8000/api/Student/getStatus',{
                    params: {
                        username: this.user.username,
                    }
                }).then((response) => {

                    this.active = response.data;


                }).catch(function (response) {
                    this.$message({
                        message: '发送请求失败',
                        type: 'warning'
                    });
                    console.log(response)
                });
            },
        }
    }
</script>

<style>
</style>
