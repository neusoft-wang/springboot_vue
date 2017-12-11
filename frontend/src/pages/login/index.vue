<style lang="less" scoped xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
	.login {
		padding: 50px;
		text-align: center;
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
	<div>
		<v-header title="登录">
			<router-link slot="left" to="/">返回</router-link>
		</v-header>
		<form class="login">
			<div class="line">	
				<div v-show="btn && !form.username">id不能为空</div>
				<input type="number" placeholder="输入你的id" v-model="form.username">
			</div>
			<div class="line">
				<div v-show="btn && !form.password">用户名不能为空</div>
				<input type="text" placeholder="输入你的用户名" v-model="form.password">
			</div>
			<button v-on:click="function1">登录</button>
		</form>
		<button v-on:click="register">没有账号？马上注册</button>
	</div>
</template>
<script>
    import { mapActions } from 'vuex'
    import { USER_SIGNIN } from '../../store/user'

    export default {
        data() {
			return {
				btn: false, //true 已经提交过， false没有提交过
				form: {
					id: '',
					name: ''
				}
			}
		},
		methods: {
			...mapActions([USER_SIGNIN]),
			submit() {
				this.btn = true
				if(!this.form.id || !this.form.name) return
                this.USER_SIGNIN(this.form)
				this.$router.replace({ path: '/home' })
            },
            register(){
                this.$router.replace({path: '/register'})
            },
            function1 () {
                this.$axios.get("http://127.0.0.1:8000/api/Admin/password", {
                    params: {
                        username: this.form.username,
                    }
                }).then((response) => {
                    if(response.data == this.form.password){
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