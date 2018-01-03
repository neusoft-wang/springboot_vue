<style lang="less" scoped xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
	.login {
		padding: 50px;
	}

	.input {
		width: 220px;
	}

	button {
		padding: 0 20px;
		margin-top: 20px;
		line-height: 28px;
	}
	.login-container {
		/*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
		-webkit-border-radius: 5px;
		border-radius: 5px;
		-moz-border-radius: 5px;
		background-clip: padding-box;
		margin: 160px auto;
		width: 350px;
		padding: 35px 35px 15px 35px;
		background: #fff;
		border: 1px solid #eaeaea;
		box-shadow: 0 0 25px #cac6c6;
		background: -ms-linear-gradient(top, #ace, #00C1DE); /* IE 10 */
		background: -moz-linear-gradient(top, #ace, #00C1DE); /*火狐*/
		background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ace), to(#00C1DE)); /*谷歌*/
		background: -webkit-linear-gradient(top, #ace, #00C1DE); /*Safari5.1 Chrome 10+*/
		background: -o-linear-gradient(top,#ace, #00C1DE); /*Opera 11.10+*/
	.title {
		margin: 0px auto 40px auto;
		text-align: center;
		color: #505458;
	}
	.remember {
		margin: 0px 0px 35px 0px;
	}
	}
</style>
<template>

	<div style="text-align: center">
		<el-form ref="form" :model="form" label-width="80px" class="login-container">
			<el-form-item label="Username">
				<el-input class="input" v-model="form.username" placeholder="username">
				</el-input>
			</el-form-item>
			<el-form-item label="Password">
				<el-input class="input" v-model="form.password"  placeholder="password">
				</el-input>
			</el-form-item>
			<div>
				<el-select v-model="form.jurisdiction" placeholder="请选择类型">
					<el-option
							v-for="item in options"
							:key="item.value"
							:label="item.label"
							:value="item.value">
					</el-option>
				</el-select>
			</div>
			<el-button type="primary" v-on:click="function1(form.jurisdiction)">登录</el-button>
			<a v-on:click="register">没有账号？马上注册</a>
		</el-form>
	</div>
</template>
<script >
    import { mapActions } from 'vuex'
    import { USER_SIGNIN } from '../../store/user'
    export default {
        data() {
			return {
				btn: false, //true 已经提交过， false没有提交过
				form: {
					password: '',
					username: '',
				},
                options: [{
                    value: 'Teacher',
                    label: 'Teacher'
                }, {
                    value: 'Student',
                    label: 'Student'
                }, {
                    value: 'Admin',
                    label: 'Admin'
                }],
			}
		},
		methods: {
			...mapActions([USER_SIGNIN]),
			submit() {
				this.btn = true
				if(!this.form.password || !this.form.username) return
                this.USER_SIGNIN(this.form)
				this.$router.replace({ path: '/home' })
            },
            register(){
                this.$router.replace({path: '/register'})
            },
            function1 (value) {
                this.$axios.get("http://127.0.0.1:8000/api/"+value+"/password", {
                    params: {
                        username: this.form.username,
                    }
                }).then((response) => {
                    if(response.data == this.form.password){
                        this.USER_SIGNIN(this.form)
                        location.reload()
                        this.$router.replace({ path: '/home' })
                    }else {
                        alert("密码错误")
					}
                    console.log(response.data);
                }).catch(function (response) {
                    console.log(response)
                });

            },
		}
    }
</script>