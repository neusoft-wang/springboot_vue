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
                        style="width: 100%"
                        class="table">

                    <el-table-column
                            fixed
                            prop="id"
                            label="item_id"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="number"
                            label="number"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="name"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="username"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="email"
                            label="email"
                            width="130">
                    </el-table-column>
                    <el-table-column
                            prop="classInfo"
                            label="classInfo"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="phone"
                            label="phone"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="create_datetime"
                            label="create_datetime"
                            width="250">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="Operation"
                            width="180">
                        <template scope="scope">
                            <el-button @click="changeStatus(scope.$index,tableData)" type="text" size="large">Confirm
                            </el-button>
                            <el-button @click="disagree" type="text" size="large">Disagree</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                               v-on:current-change="changePage">
                </el-pagination>
                <div style="margin-top: 18px">
                </div>
                <el-steps :space="386" :active="active" :finish-status="status">
                    <el-step title="待审核"></el-step>
                    <el-step title="审核结果"></el-step>
                </el-steps>
            </el-col>
        </el-row>


    </div>


</template>

<script>
    import DbSidebar from '../components/DbSidebar.vue'
    import {mapState} from "vuex";

    export default {
        computed: mapState({user: state => state.user}),
        components: {
            DbSidebar
        },
        data() {
            return {
                status: 'success',
                active: 1,
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/Teacher/showStudent',
                total: 0,
                pageSize: 0,
                currentPage: 0,
                dialogFormDor: false,
                dialogFormVisible: false,
                form: '',
                formLabelWidth: '120px',
            }
        },
        mounted() {
            this.getCustomers();
            this.getStatus(1,tableData);
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
                        username: this.user.username
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
            confirm: function () {
                if (this.active = 1) {
                    this.active = 2;
                    this.status = 'success'
                }
            },
            disagree: function () {
                if (this.active = 1) {
                    this.active = 2;
                    this.status = 'error'
                }
            },
            changeStatus: function (index, rows) {
                if (this.active = 1) {
                    this.active = 2;
                    this.status = 'success'
                    let name = rows[index].username;
                    this.$axios.get('http://127.0.0.1:8000/api/Student/changeStatus', {
                        params: {
                            username: name,
                        }
                    }).then((response) => {
                        console.log(response.data);
                    }).catch(function (response) {
                        console.log(response)
                    });
                }

            },
            getStatus:function(index, rows){
                this.$axios.get('http://127.0.0.1:8000/api/Student/getStatus',{
                    params: {
                        username: rows[index].username,
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
