<template>
    <div>
        <div class="header">
             <el-dropdown>
                <div class="user">
                    <i class="el-icon-user" style="font-size: 25px;"></i>
                    <span style="margin-right:50px;">{{ userno }}</span>
                </div>
                <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toChangePass">修改密码</el-dropdown-item>
                <el-dropdown-item @click.native="toAboutUs">关于我们</el-dropdown-item>
                <el-dropdown-item  @click.native="toLogin">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <div class="albumBtn">
                <el-button @click="toSelf" type="danger" round>我的相册</el-button>
                <el-button @click="toShare" type="danger" round>共享相册</el-button>
            </div>
            <div class="searchBtn">
                <el-input style="width:350px;"
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="keywords">
                </el-input>
            </div>
        </div>
        <div class="albumView">
            <router-view :searchKeywords='keywords'></router-view>
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
            <addAlbum></addAlbum>
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
                userno: '1252005708@qq.com',
                keywords: ''
            }
        },
        methods:{
            toSelf() {
                this.$router.push('/main/self')
                this.isSelf = true
            },
            toShare() {
                this.$router.push('/main/share')
                this.isSelf = false
            },
            toLogin() {
                this.$router.push('/home/login')
            },
            toAboutUs() {
                this.$alert('前端小白陈香伶1252005708@qq.com', '关于我', {
                    confirmButtonText: '确定'
                });
            },
            addAlbumSet() {
                this.addAlbum_dialogTableVisible=true;
            },
            toChangePass() {
                this.changPass_dialogTableVisible=true;
            }
        },
        components: {
            addAlbum,
            changePass
        }
    }
</script>

<style lang='css' scoped>
/* body {
    overflow: none !important;
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
    font-size: 18px;
    cursor: pointer;
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
    left: 50%;
    margin-left: -200px;
}
</style>