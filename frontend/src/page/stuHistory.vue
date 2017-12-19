<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span="20" class="menu">
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
                            width="130">
                        <template scope="scope">
                            <el-button v-if="user.jurisdiction === 'Admin'" @click="editItem(scope.$index, tableData)" type="text" size="large">Edit
                            </el-button>
                            <el-button v-else="user.jurisdiction == 'Admin'" @click="editItem(scope.$index, tableData)" type="text" size="large">See
                            </el-button>
                            <el-button v-if="user.jurisdiction !== 'Student'" @click="deleteItem(scope.$index, tableData)" type="text" size="large">
                                Delete
                            </el-button>
                            <el-button v-else="user.jurisdiction !== 'Student'" @click="choice(scope.$index, tableData)" type="text" size="large">
                                Choice
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
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
                total: 0,
                pageSize: 10,
                currentPage: 1,
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
                    this.total = '1';
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
