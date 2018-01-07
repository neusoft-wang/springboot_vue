<style lang="less" scoped>
	.btn {
		padding: 50px;
		text-align: center;
		button {
			padding: 5px 10px;
		};
		height: 70%;
	}
</style>
<template>
		<div class="btn">
			<el-button type="primary" v-on:click="submit">确认退出</el-button>
		</div>
</template>
<script>
    import { mapActions } from 'vuex'
    import { USER_SIGNOUT } from '../../store/user'
    export default {
        methods: {
			...mapActions([USER_SIGNOUT]),
            submit() {
                this.USER_SIGNOUT()
				this.$router.replace({ path: '/login' })
            }
        }
    }
</script>