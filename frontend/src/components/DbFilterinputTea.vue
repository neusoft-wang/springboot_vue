<template>
            <el-form :inline="true" :model="formInline">

                <el-form-item label="Subject">
                    <el-select v-model="formInline.subject" clearable placeholder="select subject"
                               v-on:visible-change="selectDemo">
                        <el-option
                                v-for="item in type_options"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item v-if='formInline.subject' label="number">
                    <el-input v-model="formInline.number" placeholder="Please input suffix of number"></el-input>
                </el-form-item>

                <el-form-item v-else='formInline.subject' label="number">
                    <el-input v-model="formInline.number" disabled placeholder="Please input suffix of number"></el-input>
                </el-form-item>
            </el-form>
</template>

<script>
    import lodash from 'lodash'
    import Bus from '../eventBus'
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {ElCol},
        name: 'db-filterinput-tea',
        data() {
            return {
                type_options: [],
                formInline: {
                    subject: '',
                    number: ''
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.subject': 'filterResultData',
            'formInline.number': 'filterResultData'
        },

        methods: {

            selectDemo: function (params) {
                if (params) {
                    this.$axios.get("http://127.0.0.1:8000/api/Teacher/tea/subject")
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
                    this.$axios.get("http://127.0.0.1:8000/api/Teacher", {
                        params: {
                            subject: this.formInline.subject,
                            number: this.formInline.number,
                        }
                    }).then((response) => {
                        response.data['subject'] = this.formInline.subject;
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