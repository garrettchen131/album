<template>
    <div>
        <el-form :model="changePassForm" status-icon :rules="rules" ref="changePassForm" label-width="80px" class="demo-changePassForm">
            <el-form-item label="旧密码" prop="oldPass">
                <el-input type="password" v-model="changePassForm.oldPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="changePassForm.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="changePassForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('changePassForm')">修改</el-button>
                <el-button @click="resetForm('changePassForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    data() {
        var validateOldPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入旧密码'));
            } else {
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入新密码'));
            } else if (value.length < 6) {
                callback(new Error('密码不能少于6位，请重新输入'));
            } else {
                if (this.changePassForm.checkPass !== '') {
                    this.$refs.changePassForm.validateField('checkPass');
                } 
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入新密码'));
            } else if (value !== this.changePassForm.pass) {
                callback(new Error('两次输入新密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            changePassForm: {
                oldPass: '',
                pass: '',
                checkPass: ''
            },
            rules: {
                oldPass: [
                    { validator: validateOldPass, trigger: 'blur' }
                ],
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // alert('submit!');
                    this.$message({
                        type: 'success',
                        message: '密码修改成功'
                    });
                } else {
                    // console.log('error submit!!');
                    this.$message.error('密码修改失败，请重试');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>

<style lang="css">
.demo-changePassForm {
    width: 400px;
    padding: 20px;
}
</style>