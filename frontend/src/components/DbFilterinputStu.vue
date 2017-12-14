<template>
            <el-form :inline="true" :model="formInline">

                <el-form-item label="classInfo">
                    <el-select v-model="formInline.classInfo" clearable placeholder="select classInfo"
                               v-on:visible-change="selectDemo">
                        <el-option
                                v-for="item in type_options"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item v-if='formInline.classInfo' label="number">
                    <el-input v-model="formInline.number" placeholder="Please input suffix of number"></el-input>
                </el-form-item>

                <el-form-item v-else='formInline.classInfo' label="number">
                    <el-input v-model="formInline.number" disabled placeholder="Please input suffix of number"></el-input>
                </el-form-item>
            </el-form>
</template>

<script>
    import lodash from 'lodash'
    import Bus from '../eventBus'

    export default {
        components: {},
        name: 'db-filterinput-stu',
        data() {
            return {
                type_options: [],
                formInline: {
                    classInfo: '',
                    number: ''
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.classInfo': 'filterResultData',
            'formInline.number': 'filterResultData'
        },

        methods: {
            selectDemo: function (params) {
                if (params) {
                    this.$axios.get("http://127.0.0.1:8000/api/Student/stu/class")
                        .then((response) => {
                            this.type_options = response.data;
                            console.log(response.data);
                        }).catch(function (response) {
                        console.log(response)
                    });
                }

            },
            filterResultData: _.debounce(
                function () {
                    this.$axios.get("http://127.0.0.1:8000/api/Student", {
                        params: {
                            classInfo: this.formInline.classInfo,
                            number: this.formInline.number,
                        }
                    }).then((response) => {
                        response.data['classInfo'] = this.formInline.classInfo;
                        response.data['number'] = this.formInline.number;
                        Bus.$emit('filterResultData', response.data);
                        console.log(response.data);
                    }).catch(function (response) {
                        console.log(response)
                    });

                },
                500
            ),
        }
    }


</script>