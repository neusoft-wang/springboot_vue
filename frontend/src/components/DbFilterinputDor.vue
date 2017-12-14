<template>
            <el-form :inline="true" :model="formInline">

                <el-form-item label="State">
                    <el-select v-model="formInline.state" clearable placeholder="select state"
                               v-on:visible-change="selectDemo">
                        <el-option
                                v-for="item in type_options"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item v-if='formInline.state' label="name">
                    <el-input v-model="formInline.name" placeholder="Please input suffix of name"></el-input>
                </el-form-item>

                <el-form-item v-else='formInline.state' label="name">
                    <el-input v-model="formInline.name" disabled placeholder="Please input suffix of name"></el-input>
                </el-form-item>
            </el-form>
</template>

<script>
    import lodash from 'lodash'
    import Bus from '../eventBus'
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {ElCol},
        name: 'db-filterinput-dor',
        data() {
            return {
                type_options: [],
                formInline: {
                    state: '',
                    name: ''
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.state': 'filterResultData',
            'formInline.name': 'filterResultData'
        },

        methods: {

            selectDemo: function (params) {
                if (params) {
                    this.$axios.get("http://127.0.0.1:8000/api/dormitory/dor/state")
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
                    this.$axios.get("http://127.0.0.1:8000/api/dormitory", {
                        params: {
                            state: this.formInline.state,
                            name: this.formInline.name,
                        }
                    }).then((response) => {
                        response.data['state'] = this.formInline.state;
                        response.data['name'] = this.formInline.name;
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