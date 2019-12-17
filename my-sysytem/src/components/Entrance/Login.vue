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
                        <el-form-item label="账号" prop="account">
                            <el-input v-model="ruleForm.account" placeholder="邮箱 / 用户名 / 电话"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item class="commitBtn">
                            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                            <el-button @click="resetForm('ruleForm')">重置</el-button>
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
            var checkAccount = (rule, value, callback) => {
                var emailPattern = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/
                var phonePattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/
                if (!value) {
                    return callback(new Error('账号不能为空'));
                }
                setTimeout(() => {
                    if (!emailPattern.test(value)) {
                        // callback(new Error('请输入正确的邮箱地址'));
                        // 不是邮箱
                        this.ruleForm.email = ''
                        if(!phonePattern.test(value)) {
                            // 不是电话号码
                            this.ruleForm.phone = ''
                            // 是用户名
                            this.ruleForm.name = value
                            callback();
                        } else {
                            // 是电话号码
                            this.ruleForm.phone = value
                            // 不是用户名
                            this.ruleForm.name = ''
                            callback();
                        }
                    } else {
                        // 是邮箱
                        this.ruleForm.email = value
                        // 不是电话号码
                        this.ruleForm.phone = ''
                        // 不是用户名
                        this.ruleForm.name = ''
                        callback();
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请输入密码'));
                } else {
                    // callback(new Error('密码错误!'));
                    callback();
                } 
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
                    account: '',
                    email: '',
                    name: '',
                    phone: '',
                    password: ''
                },
                rules: {
                    account: [
                        { validator: checkAccount, trigger: 'blur' }
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
                    if (valid) {
                        // alert('submit!');
                        this.$axios({
                            method: 'post',
                            url: 'http://192.168.31.49/user/login',
                            withCredentials : true,
                            data: {
                                mail: this.ruleForm.email,
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
                                        title: '登录成功',
                                        message: 'Welcom to your Photo-Management-system',
                                        type: 'success'
                                    });
                                    // this.$router.push('/main/self')
                                    this.$router.push({ name: 'self', params: res.data.data })
                                    break;
                                case 201: 
                                case 401: 
                                case 403: 
                                case 404:
                                case 500:
                                    this.$notify.error({
                                        title: '登录失败',
                                        message: '请检查账号和密码是否正确'
                                    });
                                    // this.resetForm('ruleForm')
                                    break;
                            }
                        }).catch((err) => {
                            console.log('登陆失败'+err)
                        })
                    } else {
                        // console.log('error submit!!');
                        this.$message.error('请检查账号和密码');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
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
.commitBtn {
    margin-left: 215px;
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