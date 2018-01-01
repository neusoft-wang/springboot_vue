<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span = "20" class="content">
                <div>
                    <div style="margin-top: 18px">
                        <el-col :span = "20">
                            <db-filterinput-dor></db-filterinput-dor>
                        </el-col>
                        <el-col :span = "4">
                            <el-button v-if="user.jurisdiction === 'Admin'"  :plain="true" style="float: right;text-align: center;" @click="newDor()" >New</el-button>
                        </el-col>
                    </div>
                    <div>
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
                                    <el-button v-if="user.jurisdiction === 'Admin'" @click="editItem(scope.$index, tableData)" type="text" size="large">Edit</el-button>
                                    <el-button v-else="user.jurisdiction === 'Admin'" @click="editItem(scope.$index, tableData)" type="text" size="large">See</el-button>
                                    <el-button v-if="user.jurisdiction !== 'Student'" @click="deleteItem(scope.$index, tableData)" type="text" size="large">Delete</el-button>
                                    <el-button v-else="user.jurisdiction !== 'Student'" @click="choice(scope.$index, tableData)" type="text" size="large">Choice</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                                       v-on:current-change="changePage">
                        </el-pagination>
                        <db-modal-dor :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal-dor>
                        <el-dialog title="New" v-model="dialogFormDor" :close-on-click-modal="false" :show-close="false">
                            <el-form :model="newform">
                                <el-form-item label="name" :label-width="formLabelWidth">
                                    <el-input v-model="newform.name" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="state" :label-width="formLabelWidth">
                                    <el-input v-model="newform.state" auto-complete="off"></el-input>
                                </el-form-item>

                                <el-form-item label="description" :label-width="formLabelWidth">
                                    <el-input v-model="newform.description" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="dor" :label-width="formLabelWidth">
                                    <el-input v-model="newform.dor" auto-complete="off"></el-input>
                                </el-form-item>

                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button :plain="true" type="danger" v-on:click="canclemodal1">Cancel</el-button>
                                <el-button :plain="true" @click="creatform(newform)">Save</el-button>
                            </div>
                        </el-dialog>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>


</template>

<script>
    import Bus from '../eventBus'
    import DbModalDor from '../components/DbModalDor.vue'
    import DbFilterinputDor  from '../components/DbFilterinputDor.vue'
    import DbSidebar  from '../components/DbSidebar.vue'
    import ElCol from "element-ui/packages/col/src/col";
    import {mapState} from "vuex";

    export default {
        computed: mapState({user: state => state.user}),
        data(){
            return {
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/dormitory',
                stuUrl: 'http://127.0.0.1:8000/api/Student/addDormitory',
                total: 0,
                pageSize: 10,
                currentPage: 1,
                name:'',
                state:'',
                username:'',
                id :'',
                newform: {
                    dor : '',
                    description : '',
                    state: '',
                    name: '',
                },
                dialogFormDor: false,
                dialogFormVisible: false,
                form: '',
                formLabelWidth :'120px',
            }
        },
        components: {
            DbFilterinputDor,
            DbModalDor,
            ElCol, DbSidebar
        },
        mounted () {
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = data.total_pages;
                this.pageSize = data.count;
                this.name = data.name;
                this.state = data.state;

            });
        },

        methods: {
            dialogVisible: function () {
                this.dialogFormVisible = false;
            },
            dialogDor: function () {
                this.dialogFormDor= false;
            },
            getCustomers: function () {
                this.$axios.get(this.apiUrl, {
                    params: {
                        page: this.currentPage,
                        state: this.state,
                        name: this.name
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
            changePage: function (currentPage) {
                this.currentPage = currentPage;
                this.getCustomers()
            },
            editItem: function (index, rows) {
                this.dialogFormVisible = true;
                const itemId = rows[index].id;
                const idurl = 'http://127.0.0.1:8000/api/dormitory/detailOfDor/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
            },
            deleteItem: function (index, rows) {
                const itemId = rows[index].id;
                const idurl = 'http://127.0.0.1:8000/api/dormitory/deleteDor/' + itemId;
                this.$axios.put(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
                location.reload();
            },
            choice: function (index, rows) {
                this.$axios.get(this.stuUrl, {
                    params: {
                        username: this.user.username,
                        id: rows[index].id,
                    }
                }).then((response) => {
                    this.$message({
                        message: '已发送请求',
                        type: 'success'
                    });
                }).catch(function (response) {
                    this.$message({
                        message: '发送请求失败',
                        type: 'warning'
                    });
                    console.log(response)
                });
            },
            newDor: function () {
                this.dialogFormDor = true;
                this.form = '';
            },
            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
            creatform: function (formName) {
                let name = formName.name;
                let state = formName.state;
                let dor = formName.dor;
                let description = formName.description;
                this.$axios.put('http://127.0.0.1:8000/api/dormitory/addDormitory', {
                    name: name,
                    state :state,
                    dor : dor,
                    description:description,
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            },
            canclemodal1: function () {
                this.dialogFormDor = false;
            }
        }
    }
</script>

<style>
    .table {
    }

    .pagination {
        margin-top: 10px;
        float: right;
    }
</style>
