<template>
    <div class="startDiv">

        <!--当页面没有相册时提示 -->
        <div class="self_emptyTip" v-if="selfEmptyTip">
            你的相册空空如也呢
            赶紧创建相册开始你的照片管理之旅吧！
        </div>

        <!-- 循环相册列表，以卡片形式输出展示在页面 -->
        <el-row class="row">
            <div :span="4" v-for="item in albumList" :key="item.id" class="cardContainer">
                <el-card :body-style="{ padding: '0px' }" class="card">
                    <div style="padding: 20px;position:relative;">
                        <div class="text" @click="openAlbum(item)">
                            <i class="el-icon-camera" style="font-size:22px;margin-right:10px;"></i>
                            <span style="font-weight:bold;font-size:25px;">{{ item.title }}</span>
                        </div>
                        <div class="bottom">
                            <el-dropdown>
                                <i class="el-icon-more" style="font-size:20px;"></i>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>分享</el-dropdown-item>
                                    <el-dropdown-item>共享</el-dropdown-item>
                                    <el-dropdown-item @click.native="editAlbum(item.id)">编辑</el-dropdown-item>
                                    <el-dropdown-item style="color:red;" @click.native="deleteAlbum(item.id)">删除</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>
                        
                    </div>
                </el-card>
            </div>
        </el-row>

        <!-- 点击删除相册按钮的提示框 -->
        <el-dialog
            title="删除相册！"
            :visible.sync="deleteAlbum_dialogVisible"
            width="30%"
            top="300px"
            :before-close="handleClose">
            <span>确定要删除这个相册吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="deleteAlbum_dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteAlbum">确 定</el-button>
            </span>
        </el-dialog>

        <!-- <el-dialog 
            title="编辑相册信息" 
            :visible.sync="editAlbum_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="800px"
            top="300px">
            <editAlbum></editAlbum>
        </el-dialog> -->




    </div>
</template>

<script>
    import editAlbum from '@/components/Popup/EditAlbumInfo';
    export default {
        data(){
            return {
                albumList: [],  //相册列表
                deleteAlbum_dialogVisible: false,   //是否打开删除相册的确认框，默认为false（不打开）
                // editAlbum_dialogTableVisible: false,
                selfEmptyTip: true,  //是否显示相册为空的提示，默认为true（提示）
                // loginID: this.$route.params.id
                // albumEditedTitle: ''
                loginInfo: this.$route.params
            }
        },
        methods:{
            // 根据相册标题为关键字搜索
            // search(searchKeywords) {
            //     var newalbumList = this.albumList.filter(item => {
            //         if(item.title && item.title.includes(searchKeywords)) {
            //             return item
            //         }
            //     })
            //     return newalbumList
            // },
            // 编辑相册
            editAlbum(albumID) {
                this.$prompt('新相册名称', '编辑相册', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputValidator: (value) => {
                        if(!value) {
                            return '请输入新相册名称'
                        } else {
                            return true
                        }
                    },
                    inputErrorMessage: ''
                }).then(({ value }) => {
                    this.$axios({
                        method: 'post',
                        url: 'http://192.168.31.49/album/change',
                        data: {
                            id: albumID,
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
                                    title: '相册修改成功',
                                    message: '快去看看',
                                    type: 'success'
                                });
                                this.getAlbumList()
                                // this.$emit('func', res.data)
                                break;
                            case 201: 
                            case 401: 
                            case 403: 
                            case 404: 
                                console.log(value)
                                this.$notify.error({
                                    title: '相册修改失败',
                                    message: '请重试'
                                });
                                // this.newAlbumInfo = res.data
                                break;
                            case 500:
                                console.log(value)
                                this.$notify.error({
                                    title: '相册修改失败',
                                    message: '重复？'
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
            // 打开相册，进入相册集详情页面
            openAlbum(item) {
                this.$router.push({ name: 'set', params: item })
            },
            // 删除相册
            deleteAlbum(id) {
                this.$confirm('此操作将永久删除该相册, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios({
                        method: 'post',
                        url: 'http://192.168.31.49/album/delete/'+id,
                        header: {
                            'Content-Type': 'application/json;charset=UTF-8'
                        }
                    }).then((res) => {
                        console.log(res)
                        var code = res.data.code
                        switch(code) {
                            case 200: 
                                this.$notify({
                                    title: '相册删除成功',
                                    message: '快去看看',
                                    type: 'success'
                                });
                                console.log('length'+this.albumList.length)
                                this.getAlbumList()
                                break;
                            case 201:
                            case 401: 
                            case 403: 
                            case 404: 
                                this.$notify.error({
                                    title: '相册删除失败',
                                    message: '请重试'
                                });
                                // this.newAlbumInfo = res.data
                                break;
                            case 500:
                                this.$notify.error({
                                    title: '相册删除失败',
                                    message: '请先登录！'
                                });
                                break;
                        }
                    }).catch((err) => {
                        console.log(err)
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                });
            },
            // 判断是否显示删除相册的弹框
            judgeAlbum() {
                this.deleteAlbum_dialogVisible = true
            },
            // 处理关闭
            handleClose(done) {
                done()
            },
            ///////////////////////////////////////////////////【mounted执行】
            // 获取相册列表
            getAlbumList() {
                this.$axios.get('http://192.168.31.49/album/get')
                .then((res) => {
                    console.log(res)
                    var code = res.data.code
                    switch(code) {
                        case 200: 
                            this.$notify({
                                title: '相册获取成功',
                                message: '快去看看',
                                type: 'success'
                            });
                            this.albumList = res.data.data.myself
                            // console.log('相册长度'+this.albumList.length)
                            if(this.albumList.length>0) {
                                this.selfEmptyTip = false
                            }
                            break;
                        case 201: 
                        case 401: 
                        case 403: 
                        case 404: 
                            this.$notify.error({
                                title: '相册创建失败',
                                message: '请重试'
                            });
                            break;
                        case 500:
                            this.$notify.error({
                                title: '500',
                                message: '请先登录！'
                            });
                            break;
                    }
                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        components: {
            editAlbum
        },
        props: ['searchKeywords', 'updateAlbumList'],
        mounted() {
            this.getAlbumList()
            this.$emit('func', this.loginInfo)
        }
    }
</script>

<style lang='css' scoped>
.startDiv {
    padding: 20px 0 20px 20px;
    background-image: url(~@/assets/Bg13.png);
    background-repeat: no-repeat;
    background-size: 100%;
    /* background-color: #fff; */
    /* opacity: 0.7; */
    /* height: auto; */
    overflow-x: hidden;
    /* overflow-y: scroll; */
}
.cardContainer {
    width: 300px;
    margin-top: 50px;
    margin-left: 100px;
    float: left;
    box-shadow: 20px 20px 8px rgba(0, 0, 0, 0.25);
    transition: all .3s linear;
    /* opacity: 0.9; */
    /* background-color: green; */
    /* border: 1px solid yellow; */
    /* cursor: pointer; */
    /* border-radius: 20px; */
}
.cardContainer:hover {
    transition: all .3s linear;
    box-shadow: 5px 5px 13px rgba(0, 0, 0, 0.4);
}
.card {
    /* border: 1px solid #7a735d; */
    /* border-radius: 20px; */
    opacity: 1;
    /* background-color: rgb(156, 209, 245); */
    /* background-image: url(~@/assets/Bg10.png); */
    /* background-size: 100%; */
}
.row {
    /* background-image: url(~@/assets/Bg16.png); */
    /* background: linear-gradient(45deg,pink,lightblue); */
    /* background-color: rgba(255, 255, 255, 0.5); */
    /* padding: ; */
    /* border: 1px solid red; */
    position: absolute;
    bottom: 0px;
    top: 0px;
    left: 0px;
    right: 0px;
    padding: 20px 20px 50px 20px;
    overflow-y: scroll;
    box-shadow: -10px 0px 15px rgba(0, 0, 0, 0.3);
    /* opacity: 0.8; */
}
.el-main {
    line-height: 10px;
}
.time {
  font-size: 13px;
  color: #999;
  /* float: left;
  margin-right: 10px; */
}

.bottom {
    position: absolute;
    top: 15px;
    right: 25px;
    cursor: pointer;
}
.image {
  width: 100%;
  height: 250px;
  display: block;
  cursor: pointer;
}
.text {
    height: 100px;
    text-align: left;
    padding: 40px;
    cursor: pointer;
    text-overflow: ellipsis;
    /* overflow: hidden;
    display:-webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient:vertical; */
    /* border: 1px solid #000; */
    border-top-right-radius: 45%;
    /* border-top-left-radius: 50%; */
    background-image: url(~@/assets/Bg9.png);
    background-size: 100% ;
    /* opacity: 0.8; */
    /* background-color: #fff; */
}
.info {
  line-height: 45px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.self_emptyTip {
    font-size: 30px;
    /* font-weight: bold; */
    /* color: #7a735d; */
    color: #000;
    font-family: 幼圆;
    text-shadow: 8px 8px 5px rgba(204, 159, 159, 0.952);
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -540px;
    margin-top: -80px;
}
.el-card, .el-message {
    border-radius: 0;
    border: none;
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