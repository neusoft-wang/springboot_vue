<template>
    <div class="wrapper">
        <el-row class="container">
            <el-col :span="4" class="menu">
                <db-sidebar></db-sidebar>
            </el-col>
            <el-col :span = "20" class="content">
                <el-form :model="form">
                    <el-form-item label="Old Pass Word" :label-width="formLabelWidth">
                        <el-input v-model="form.passWord" auto-complete="off"></el-input>
                    </el-form-item>

                    <el-form-item label="New PassWord" :label-width="formLabelWidth">
                        <el-input v-model="form.newPassword" auto-complete="off"></el-input>
                    </el-form-item>

                    <el-form-item label="again Pass Word" :label-width="formLabelWidth">
                        <el-input v-model="form.againPassword" auto-complete="off"></el-input>
                    </el-form-item>
                </el-form>

                    <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
                    <el-button :plain="true" @click="edit(form)">modify</el-button>

            </el-col>
        </el-row>
    </div>


</template>

<script>
    import Bus from '../eventBus'
    import DbModal from '../components/DbModal.vue'
    import DbFilterinput  from '../components/DbFilterinput.vue'
    import DbSidebar  from '../components/DbSidebar.vue'
    import {mapState} from "vuex"
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        computed: mapState({ user: state => state.user }),
        data(){
            return {
                tableData: [],
                apiUrl: 'http://127.0.0.1:8000/api/persons',
                total: 0,
                pageSize: 10,
                currentPage: 1,
                sex: '',
                email: '',
                dialogFormVisible: false,
                form: {
                    username:'',
                    passWord: '',
                    newPassword: '',
                    againPassword: ''
                }
            }
        },
        components: {
            DbModal,
            DbFilterinput,
            ElCol, DbSidebar
        },
        mounted () {
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = data.total_pages;
                this.pageSize = data.count;
                this.email = data.email;
                this.sex = data.sex;

            });
        },

        methods: {

            dialogVisible: function () {
                this.dialogFormVisible = false;
            },

            getCustomers: function () {
                this.$axios.get(this.apiUrl, {
                    params: {
                        page: this.currentPage,
                        sex: this.sex,
                        email: this.email
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
                const idurl = 'http://127.0.0.1:8000/api/persons/detail/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
            },

            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
            edit: function (formName) {
                alert("lala");
                let username = this.user.username;
                let passWord = formName.passWord;
                let newPassword = formName.newPassword;
                let againPassword = formName.againPassword;
                this.$axios.put('http://127.0.0.1:8000/api/Student/editOfStu/', {
                    username : username,
                    newPassword: newPassword,
                    againPassword: againPassword,
                    passWord: passWord
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                this.$router.replace({ path: '/login' })
                location.reload();
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
