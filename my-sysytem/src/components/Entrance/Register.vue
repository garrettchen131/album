<template>
    <div class="Rcontainer">
        <el-row>
            <el-col style="width:700px;">
                <el-card :body-style="{ padding: '20px' }">
                    <el-carousel :interval="3000" type="card" height="200px">
                        <el-carousel-item v-for="item in carouselList" :key="item.id">
                            <el-image :src="item.image"></el-image>
                        </el-carousel-item>
                    </el-carousel>
                    <div style="padding: 14px;">
                        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
                            <el-form-item label="邮箱" prop="mail">
                                <el-input v-model="ruleForm.mail"></el-input>
                            </el-form-item>
                            <el-form-item label="用户名" prop="name">
                                <el-input v-model="ruleForm.name"></el-input>
                            </el-form-item>
                            <el-form-item label="密码" prop="password">
                                <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="确认密码" prop="checkPass">
                                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="电话号码" prop="phone">
                                <el-input v-model="ruleForm.phone"></el-input>
                            </el-form-item>
                            <el-form-item class="commitBtn">
                                <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                                <el-button @click="resetForm('ruleForm')">重置</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    // import axios from 'axios';
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
            var checkName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                } else {
                    callback();
                }
            };
            var checkPhone = (rule, value, callback) => {
                var inputPattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/
                if (!value) {
                    return callback(new Error('电话号码不能为空'));
                }
                setTimeout(() => {
                    if (!inputPattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    return callback(new Error('请输入密码'));
                } else if (value.length < 6) {
                    callback(new Error('密码不能少于6位，请重新输入'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    } 
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                carouselList: [
                    { id: 1, image: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg' },
                    { id: 2, image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg' },
                    { id: 3, image: 'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg' },
                    { id: 4, image: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg' },
                    { id: 5, image: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg' },
                    { id: 6, image: 'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg' }
                ],
                ruleForm: {
                    mail: '',
                    name: '',
                    password: '',
                    checkPass: '',
                    phone: ''
                },
                rules: {
                    mail: [
                        { validator: checkEmail, trigger: 'blur' }
                    ],
                    name: [
                        { validator: checkName, trigger: 'blur' }
                    ],
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    phone: [
                        { validator: checkPhone, trigger: 'blur' }
                    ]
                }
                // registerFailMsg: ''
            }
        },
        methods:{
             submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        // this.$axios.post(this.GLOBAL.host+"/user/register",this.$qs.stringify({
                        //     mail: this.ruleForm.mail,
                        //     name: this.ruleForm.name,
                        //     password: this.ruleForm.password,
                        //     phone: this.ruleForm.phone
                        // }))
                        // .then(res => {
                        //     console.log(res)
                        // })
                        // .catch(err => {
                        //     console.log(err)
                        // })

                        this.$axios({
                            method: 'post',
                            url: 'http://139.9.205.50/user/register',
                            data: {
                                mail: this.ruleForm.mail,
                                name: this.ruleForm.name,
                                password: this.ruleForm.password,
                                phone: this.ruleForm.phone
                            },
                            header: {
                                'Content-Type': 'application/json;charset=UTF-8'
                            }
                        }).then((res) => {
                            console.log(res)
                            var code = res.data.code
                            switch(code) {
                                case 200: 
                                    this.$notify({
                                        title: '注册成功',
                                        message: '快去登录吧！',
                                        type: 'success'
                                    });
                                    this.$router.push('/home/login')
                                    break;
                                case 201: 
                                case 401: 
                                case 403: 
                                case 404: 
                                    this.$notify.error({
                                        title: '注册失败',
                                        message: '邮箱/用户名/电话 已被注册，请重试'
                                    });
                                    this.resetForm('ruleForm')
                                    break;
                            }
                        }).catch((err) => {
                            console.log(err)
                        })
                        
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        mounted() {
            // this.axios.post()
        }
    }
</script>

<style lang='css' scoped>
.Rcontainer {
    box-shadow: 20px 20px 5px rgba(0, 0, 0, 0.2);
}
.time {
  font-size: 13px;
  color: #999;
}
.commitBtn {
    margin-left: 175px;
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

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>