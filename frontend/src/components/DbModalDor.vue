<template>
    <el-dialog title="Dormitory Info" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
        <el-form :model="form">

            <el-form-item v-if="user.jurisdiction !== 'Student'" label="name" :label-width="formLabelWidth">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item  v-else="user.jurisdiction !== 'Student'" label="name" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-if="user.jurisdiction !== 'Student'" label="state" :label-width="formLabelWidth">
                <el-input v-model="form.state" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-else="user.jurisdiction !== 'Student'" label="state" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.state" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-if="user.jurisdiction !== 'Student'" label="description" :label-width="formLabelWidth">
                <el-input v-model="form.description" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-else="user.jurisdiction !== 'Student'" label="description" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.description" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-if="user.jurisdiction !== 'Student'" label="dor" :label-width="formLabelWidth">
                <el-input v-model="form.dor" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item v-else="user.jurisdiction !== 'Student'" label="dor" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.dor" auto-complete="off"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
            <el-button :plain="true" @click="updateForm(form)">Save</el-button>
        </div>
    </el-dialog>
</template>


<script>
    import {mapState} from "vuex";
    export default {
        computed: mapState({ user: state => state.user }),
        data(){
            return {
                formLabelWidth: '120px',
            }
        },
        props: ['dialogFormVisible', 'form'],

        methods: {
            updateForm: function (formName) {
                let itemId = formName.id;
                let name = formName.name;
                let state = formName.state;
                let dor = formName.dor;
                let description = formName.description;
                this.$axios.put('http://127.0.0.1:8000/api/dormitory/detailOfDor/' + itemId, {
                    name: name,
                    state :state,
                    dor : dor,
                    description:description,
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            },
            canclemodal: function () {
                this.$emit('canclemodal');
            }
        }

    }

</script>