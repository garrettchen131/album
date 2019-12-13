<template>
    <div class="mainContainer">

        <!-- 导航栏头部 -->
        <div class="header">
             <el-dropdown>
                <div class="user">
                    <i class="el-icon-user" style="font-size: 28px;"></i>
                </div>
                <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toChangePass">修改密码</el-dropdown-item>
                <el-dropdown-item @click.native="toAboutUs">关于我们</el-dropdown-item>
                <el-dropdown-item @click.native="toDestroy">注销账户</el-dropdown-item>
                <el-dropdown-item  @click.native="toLogin">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <!-- <div class="albumBtn">
                <el-button @click="toSelf" type="danger" round class="alBtn">我<br>的<br>相<br>册</el-button><br>
                <el-button @click="toShare" type="danger" round class="alBtn">共<br>享<br>相<br>册</el-button>
            </div> -->
            <!-- 添加相册按钮 -->
            <div class="bottomBtn">
                <el-button @click="toSelf" type="danger" round class="alBtn">我<br>的<br>相<br>册</el-button><br>
                <el-button @click="toShare" type="danger" round class="alBtn">共<br>享<br>相<br>册</el-button><br>
                <el-button type="danger" icon="el-icon-plus" circle title="新建相册" @click="addAlbum" v-if="isSelf" class="alBtn alCirBtn"></el-button>
            </div>

            <!-- <div class="searchBtn">
                <el-input
                    
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="keywords">
                </el-input>
            </div> -->
        </div>

        <!-- 我的相册 和 共享相册 子组件展示的地方 -->
        <div class="albumView">
            <router-view :searchKeywords='keywords' class="showAlbum" ref="child"></router-view>
            <!-- <router-view :searchKeywords='keywords' :newAlbumInfo="newAlbumInfo" class="showAlbum" :func="getCompMsg"></router-view> -->
        </div>

        


        <!-- <el-dialog 
            title="创建相册" 
            :visible.sync="addAlbum_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="800px">
            <addAlbum @func="createAlbum"></addAlbum>
        </el-dialog> -->


        <el-dialog 
            title="修改密码" 
            :visible.sync="changPass_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="500px">
            <changePass></changePass>
        </el-dialog>

        
    </div>
</template>

<script>
    import addAlbum from '@/components/Popup/AddAlbum';
    import changePass from '@/components/Popup/ChangePassword';
    export default {
        data(){
            return {
                isSelf: true,
                addAlbum_dialogTableVisible: false,
                changPass_dialogTableVisible: false,
                keywords: '',   //用于绑定搜索的关键字
                // newAlbum: []
            }
        },
        methods:{
            toSelf() {
                this.$router.push('/main/self')
                // this.isSelf = true
            },
            toShare() {
                this.$router.push('/main/share')
                // this.isSelf = false
            },
            toLogin() {
                this.$router.push('/home/login')
            },
            toAboutUs() {
                this.$alert('你好，我是🥦🐔陈香伶', '关于我', {
                    confirmButtonText: '很高兴认识你，🥦🐔陈香伶'
                });
            },
            addAlbum() {
                this.$prompt('相册名称', '新建相册', {
                    confirmButtonText: '创建',
                    cancelButtonText: '取消',
                    inputValidator: (value) => {
                        if(!value) {
                            return '请输入相册名称'
                        } else {
                            return true
                        }
                    },
                    inputErrorMessage: ''
                }).then(({ value }) => {
                    this.$axios({
                        method: 'post',
                        url: 'http://139.9.205.50/album/add',
                        data: {
                            title: value
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
                                    title: '相册创建成功',
                                    message: '快去上传一组照片试试吧！',
                                    type: 'success'
                                });
                                this.$refs.child.getAlbumList()
                                break;
                            case 201: 
                            case 401: 
                            case 403: 
                            case 404: 
                                this.$notify.error({
                                    title: '相册创建失败',
                                    message: '请重试'
                                });
                                this.newAlbumInfo = res.data
                                break;
                            case 500:
                                this.$notify.error({
                                    title: '相册创建失败',
                                    message: '相册已经存在,请勿重复添加'
                                });
                                break;
                        }
                    }).catch((err) => {
                        console.log(err)
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });       
                });
            },
            toChangePass() {
                this.changPass_dialogTableVisible=true;
            },
            toDestroy() {
                this.$confirm('此操作将永久删除该账户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                })
                .then(() => {
                    this.$axios.post('http://139.9.205.50/user/logout')
                    .then((res) => {
                        console.log(res)
                        var code = res.data.code
                        switch(code) {
                            case 200: 
                                this.$notify({
                                    title: '注销成功',
                                    message: '该用户已永久删除',
                                    type: 'success'
                                });
                                this.$router.replace('/home/login')
                                break;
                            case 201: 
                            case 401: 
                            case 403: 
                            case 404: 
                                this.$notify.error({
                                    title: '注销失败',
                                    message: '找不到对应账户'
                                });
                                break;
                        }
                    }).catch((err) => {
                        console.log('登陆失败'+err)
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                });
            }
        },
        components: {
            addAlbum,
            changePass
        },
        watch: {
            $route(to, from) {
                // console.log(window.location.href)
                // console.log(this.$route.path)
                // console.log(this.$route.params)
                if(this.$route.path === '/main/self') {
                    this.isSelf = true
                } else if (this.$route.path === '/main/share') {
                    this.isSelf = false
                }
            }
        }
    }
</script>

<style lang='css' scoped>
/* .mainContainer {
    background: url(https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg);
} */
.header {
    height: 100%;
    width: 90px;
    /* background-color: pink; */
    position: fixed;
    top: 0;
    right: 0;
    z-index: 1;
    background-image: url(~@/assets/Bg12.png);
    /* background-size: 100%;
    background-repeat: no-repeat; */
    /* box-shadow: -9px 0px 15px rgba(0, 0, 0, 0.2); */
}
.el-dropdown {
    position: absolute;
    right: 10px;
    top: 19px;
}
.el-dropdown .user {
    /* font-size: 18px; */
    cursor: pointer;
    margin-right: 20px;
}
.bottomBtn {
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 1;
}
.albumView {
    margin-top: 60px;
}
.albumBtn {
    position: absolute;
    top: 80px;
    right: 15px;
}
.searchBtn {
    position: absolute;
    top: 10px;
    left: 480px;
    right: 110px;
    margin-left: -200px;
}
.showAlbum {
    position: absolute;
    top: 0px;
    left: 0px;
    right: 90px;
    bottom: 0px;
    /* border: 1px solid #000; */
    /* border-radius: 20px; */
    /* border-top-left-radius: 20px; */
    /* box-shadow: 10px 20px 40px rgba(0, 0, 0, 0.2); */
    /* opacity: 0.8; */
    /* overflow-y: scroll; */
}
.alBtn {
    width: 50px;
    height: 150px;
    font-size: 18px;
    font-family: 幼圆;
    line-height: 25px;
    margin-top: 20px;
}
.alCirBtn {
    width: 50px;
    height: 50px;
}
.el-button.is-round {
    padding: 0;
    border-radius: 5px;
}
</style>