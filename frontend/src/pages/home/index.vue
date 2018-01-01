<template>
	<div class="wrapper">
		<el-row class="container">
			<el-col :span="4" class="menu">
				<db-sidebar></db-sidebar>
			</el-col>
			<el-col :span = "20" class="content">
				<div>
					<router-link slot="right"  to="/signout">退出</router-link>
					<div style="padding: 50px;">{{user.username}}欢迎回家</div>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>

    import ElCol from "element-ui/packages/col/src/col";
    import DbSidebar from '../../components/DbSidebar.vue'
    import {mapState} from "vuex";
    export default {
        computed: mapState({ user: state => state.user }),
        components:
        {
            ElCol,DbSidebar
		},
		data(){
            return{
			}
		},
        methods: {
            reload(){
                    /*location.reload();*/
			},
            submit() {
                alert(this.user.username)
            }
        },
        mounted () {
            this.reload();
		}

    }

</script>

<style lang="less" scoped >

</style>