<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="2" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span="22" class="content">
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
                </el-table>
                <div style="margin-top: 18px">
                </div>
                <el-steps :space="386" :active="1" finish-status="success" >
                    <el-step title="已申请"></el-step>
                    <el-step title="待审核"></el-step>
                    <el-step title="审核结果"></el-step>
                </el-steps>
            </el-col>
        </el-row>


    </div>


</template>

<script>
    import DbSidebar  from '../components/DbSidebar.vue'
    import {mapState} from "vuex";
    export default {
        computed: mapState({user: state => state.user}),
        components: {
            DbSidebar
        },
        data(){
            return {
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/Student/showHistory',
                total: 1,
                pageSize: 0,
                currentPage: 0,
                dialogFormDor: false,
                dialogFormVisible: false,
                form: '',
                formLabelWidth :'120px',
            }
        },
        mounted () {
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
                    this.tableData = response;
                    console.log(response.data.data);
                }).catch(function (response) {
                    console.log(response)
                });
            }
        }
    }
</script>

<style>
</style>
