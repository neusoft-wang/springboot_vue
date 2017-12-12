<style lang="less" scoped xmlns:v-on="http://www.w3.org/1999/xhtml">
    .register {
        padding: 75px;
        text-align: center;
        width: 220px;
    }

    .line {
        padding: 5px;
    }

    input {
        padding: 0 10px;
        line-height: 28px;
    }

    button {
        padding: 0 20px;
        margin-top: 20px;
        line-height: 28px;
    }
</style>
<template>
    <div  class="register">
        <el-form   :model="form">
            <el-form-item label="username" :label-width="formLabelWidth">
                <el-input v-model="form.username" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="passWord" :label-width="formLabelWidth">
                <el-input v-model="form.passWord" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="jurisdiction" :label-width="formLabelWidth">
                <el-input v-model="form.jurisdiction" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="number" :label-width="formLabelWidth">
                <el-input v-model="form.number" auto-complete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
            <el-button :plain="true" @click="registerForm(form)">Register</el-button>
        </div>
    </div>
</template>
<script>
    import {mapActions} from 'vuex'
    import {USER_SIGNIN} from '../../store/user'

    export default {
        data() {
            return {
                btn: false, //true 已经提交过， false没有提交过
                form: {
                    username: '',
                    passWord: '',
                    jurisdiction: '',
                    number: ''
                }
            }
        },
        methods: {
            ...mapActions([USER_SIGNIN]),
            registerForm: function (formName) {
                let username = formName.username;
                let passWord = formName.passWord;
                let jurisdiction = formName.jurisdiction;
                let number = formName.number;
                this.$axios.put('http://127.0.0.1:8000/api/Admin/addAdmin', {
                    username: username,
                    jurisdiction: jurisdiction,
                    number: number,
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
            },
            canclemodal: function () {
                this.$emit('canclemodal');
            }
        }

    }
</script>