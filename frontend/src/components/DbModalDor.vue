<template>
    <el-dialog title="Edit" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
        <el-form :model="form">
            <el-form-item label="name" :label-width="formLabelWidth">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="state" :label-width="formLabelWidth">
                <el-input v-model="form.state" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="is_use" :label-width="formLabelWidth">
                <el-input v-model="form.is_use" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="description" :label-width="formLabelWidth">
                <el-input v-model="form.description" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="dor" :label-width="formLabelWidth">
                <el-input v-model="form.dor" auto-complete="off"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
            <el-button :plain="true" @click="updateForm(form)">Save</el-button>
        </div>
    </el-dialog>
</template>


<script>
    export default {
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
                let is_use = formName.is_use;
                let dor = formName.dor;
                let description = formName.description;
                this.$axios.put('http://127.0.0.1:8000/api/dormitory/detailOfDor/' + itemId, {
                    name: name,
                    state :state,
                    is_use :is_use,
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