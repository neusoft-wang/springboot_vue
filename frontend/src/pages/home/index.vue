<template>
	<div class="wrapper">
		<el-row class="container">
			<el-col :span="4" class="menu">
				<div>
					<ul>
						<li class="el-menu-item is-active" style=""  v-for="item in links"  v-if="item.text === user.jurisdiction || item.qiana ==='1' " >
							<a @click="$goRoute(item.route)">{{item.text}}</a></li>
					</ul>
				</div>
            </el-col>
			<el-col :span = "20" class="content">
				<div>
					<button slot="left" v-on:click="submit" >{{user}}</button>
					<router-link slot="right"  to="/signout">退出</router-link>
					<div style="padding: 50px;">{{user.jurisdiction}}欢迎回家</div>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>

    import ElCol from "element-ui/packages/col/src/col";
    import {mapState} from "vuex";
    export default {
        computed: mapState({ user: state => state.user }),
        components:
        {
            ElCol,
		},
        data () {
            return{
                links: [
                    {
                        text: 'Dashboard',
                        route: '/dashboard',
						qiana:'1'

                    },
                    {
                        text: 'Information',
                        route: '/information',
						qiana:'Teacher'
                    },
                    {
                        text: 'Dormitory',
                        route: '/dormitory',
                        qiana:'1'
                    }
                    ,
                    {
                        text: 'Teacher',
                        route: '/teacher',
						qiana:'Teacher'
                    }
                    ,
                    {
                        text: 'Student',
                        route: '/student',
                        qiana:'1'
                    }
            ]
            }
		},
        methods: {
            submit() {
                alert(this.user.username)
            }
        }

    }

</script>

<style lang="less" scoped >

</style>