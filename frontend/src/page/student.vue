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
                            <db-filterinput-stu></db-filterinput-stu>
                        </el-col>
                        <el-col :span = "4">
                            <el-button v-if="user.jurisdiction === 'Admin'"  :plain="true" style="float: right;text-align: center;"  @click="newStu()" >New</el-button>
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
                                    width="130">
                                <template scope="scope" v-if="user.jurisdiction === 'Admin'">
                                    <el-button @click="editItem(scope.$index, tableData)" type="text" size="large">Edit</el-button>
                                    <el-button @click="deleteItem(scope.$index, tableData)" type="text" size="large">Delete</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                                       v-on:current-change="changePage">
                        </el-pagination>
                        <db-modal-stu :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal-stu>
                        <el-dialog title="New" v-model="dialogFormDor" :close-on-click-modal="false" :show-close="false">
                            <el-form :model="newform">
                                <el-form-item label="name" :label-width="formLabelWidth">
                                    <el-input v-model="newform.name" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="username" :label-width="formLabelWidth">
                                    <el-input v-model="newform.username" auto-complete="off"></el-input>
                                </el-form-item>

                                <el-form-item label="sex" :label-width="formLabelWidth">
                                    <el-input v-model="newform.sex" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="phone" :label-width="formLabelWidth">
                                    <el-input v-model="newform.phone" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="passWord" :label-width="formLabelWidth">
                                    <el-input v-model="newform.passWord" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="number" :label-width="formLabelWidth">
                                    <el-input v-model="newform.number" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="email" :label-width="formLabelWidth">
                                    <el-input v-model="newform.email" auto-complete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="classInfo" :label-width="formLabelWidth">
                                    <el-input v-model="newform.classInfo" auto-complete="off"></el-input>
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
    import DbModalStu from '../components/DbModalStu.vue'
    import DbFilterinputStu  from '../components/DbFilterinputStu.vue'
    import DbSidebar  from '../components/DbSidebar.vue'
    import ElCol from "element-ui/packages/col/src/col";
    import {mapState} from "vuex";

    export default {
        computed: mapState({user: state => state.user}),
        data(){
            return {
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/Student',
                total: 0,
                pageSize: 10,
                currentPage: 1,
                classInfo:'',
                number:'',
                newform: {
                    name : '',
                    username : '',
                    sex: '',
                    phone: '',
                    passWord: '',
                    number: '',
                    email: '',
                    classInfo: '',
                },
                dialogFormDor: false,
                dialogFormVisible: false,
                form: '',
                formLabelWidth :'120px',
            }
        },
        components: {
            DbFilterinputStu,
            DbModalStu,
            ElCol, DbSidebar
        },
        mounted () {
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = data.total_pages;
                this.pageSize = data.count;
                this.classInfo = data.classInfo;
                this.number = data.number;

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
                        classInfo: this.classInfo,
                        number: this.number
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
                const idurl = 'http://127.0.0.1:8000/api/Student/detailOfStu/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
            },
            deleteItem: function (index, rows) {
                const itemId = rows[index].id;
                const idurl = 'http://127.0.0.1:8000/api/Student/deleteStu/' + itemId;
                this.$axios.put(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
                location.reload();
            },
            newStu: function () {
                this.dialogFormDor = true;
                this.form = '';
            },
            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
            creatform: function (formName) {
                let name = formName.name;
                let username = formName.username;
                let sex = formName.sex;
                let phone = formName.phone;
                let passWord = formName.passWord;
                let number = formName.number;
                let email = formName.email;
                let classInfo = formName.classInfo;
                this.$axios.put('http://127.0.0.1:8000/api/Student/addStudent', {
                    name : name,
                    username : username,
                    sex: sex,
                    phone: phone,
                    passWord: passWord,
                    number: number,
                    email: email,
                    classInfo: classInfo,
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
