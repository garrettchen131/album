<template>
    <div class="mainContainer">
        <div class="header">
             <el-dropdown>
                <div class="user">
                    <i class="el-icon-user" style="font-size: 28px;"></i>
                    <!-- <span style="margin-right:50px;">{{ userno }}</span> -->
                </div>
                <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toChangePass">修改密码</el-dropdown-item>
                <el-dropdown-item @click.native="toAboutUs">关于我们</el-dropdown-item>
                <el-dropdown-item @click.native="toDestroy">注销账户</el-dropdown-item>
                <el-dropdown-item  @click.native="toLogin">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <div class="albumBtn">
                <el-button @click="toSelf" type="danger" round>我的相册</el-button>
                <el-button @click="toShare" type="danger" round>共享相册</el-button>
            </div>
            <div class="searchBtn">
                <el-input
                    
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="keywords">
                </el-input>
            </div>
        </div>
        <div class="albumView">
            <router-view :searchKeywords='keywords' :newAlbumInfo="newAlbumInfo" class="showAlbum"></router-view>
        </div>
        <div class="bottomBtn">
            <el-button type="danger" icon="el-icon-plus" circle title="新建相册" @click="addAlbumSet" v-if="isSelf"></el-button>
        </div>
        <el-dialog 
            title="创建相册" 
            :visible.sync="addAlbum_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="800px">
            <addAlbum @func="createAlbum"></addAlbum>
        </el-dialog>
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
    import addAlbum from '@/components/Popup/AddAlbumSet';
    import changePass from '@/components/Popup/ChangePassword';
    export default {
        data(){
            return {

                isSelf: true,
                addAlbum_dialogTableVisible: false,
                changPass_dialogTableVisible: false,
                // userno: '1252005708@qq.com',
                keywords: '',
                newAlbumInfo: {}
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
            addAlbumSet() {
                this.addAlbum_dialogTableVisible=true;
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
                                this.$router.push('/home/login')
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
            },
            createAlbum(formInfo) {
                // console.log(formInfo)
                this.newAlbumInfo = formInfo
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
    height: 60px;
    width: 100%;
    background-color: pink;
    position: fixed;
    top: 0;
    z-index: 1;
    box-shadow: 2px 5px 5px rgba(0, 0, 0, 0.2);
}
.el-dropdown {
    position: absolute;
    right: 10px;
    top: 19px;
}
.el-dropdown .user {
    /* font-size: 18px; */
    cursor: pointer;
    margin-right: 30px;
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
    top: 10px;
    left: 20px;
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
    top: 120px;
    left: 100px;
    right: 100px;
    bottom: 60px;
    /* border: 1px solid #000; */
    /* border-radius: 20px; */
    /* border-top-left-radius: 20px; */
    box-shadow: 10px 20px 40px rgba(0, 0, 0, 0.2);
    /* opacity: 0.8; */
    overflow-y: scroll;
}
</style>