<template>
    <div class="Lcontainer">
        <el-row>
            <el-col style="width:700px;">
                <el-card :body-style="{ padding: '20px' }">
                    <el-carousel :interval="3000" type="card" height="200px">
                        <el-carousel-item v-for="item in carouselList" :key="item.id">
                            <el-image :src="item.image"></el-image>
                        </el-carousel-item>
                    </el-carousel>
                    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px" class="demo-ruleForm">
                        <el-form-item label="邮箱" prop="email">
                            <el-input v-model="ruleForm.email"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="pass">
                            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        data(){
            var checkEmail = (rule, value, callback) => {
                var inputPattern = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/
                if (!value) {
                    return callback(new Error('邮箱不能为空'));
                }
                setTimeout(() => {
                    if (!inputPattern.test(value)) {
                        callback(new Error('请输入正确的邮箱地址'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if (value !== '123456') {
                    callback(new Error('密码错误!'));
                } 
                callback();
            };
            return {
                carouselList: [
                    { id: 1, image: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg' },
                    { id: 2, image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg' },
                    { id: 3, image: 'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg' },
                    { id: 4, image: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg' },
                    { id: 5, image: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg' },
                    { id: 6, image: 'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg' }
                ],
                ruleForm: {
                    email: '',
                    pass: ''
                },
                rules: {
                    email: [
                        { validator: checkEmail, trigger: 'blur' }
                    ],
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid && (this.ruleForm.email==='1252005708@qq.com'&&this.ruleForm.pass==='123456')) {
                        // alert('submit!');
                        this.$router.push('/main/self')
                    } else {
                        // console.log('error submit!!');
                        this.$message.error('登录失败，请检查用户名和密码是否正确');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style lang='css' scoped>
.Lcontainer {
    box-shadow: 20px 20px 5px rgba(0, 0, 0, 0.2);
}
.time {
  font-size: 13px;
  color: #999;
}
.demo-ruleForm {
    position: relative;
}
.el-button {
    margin-left: 245px;
}
.image {
  width: 100%;
  display: block;
}
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}
</style>