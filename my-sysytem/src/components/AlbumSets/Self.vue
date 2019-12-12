<template>
    <div class="startDiv">

        <!--当页面没有相册时提示 -->
        <div class="self_emptyTip" v-if="selfEmptyTip">
            你的相册空空如也呢
            赶紧创建相册开始你的照片管理之旅吧！
        </div>

        <!-- 循环相册列表，以卡片形式输出展示在页面 -->
        <el-row>
            <div :span="4" v-for="item in albumList" :key="item.id" class="cardContainer">
                <el-card :body-style="{ padding: '0px' }"  @click.native="openAlbum(item)" class="card">
                    <div style="padding: 14px;position:relative;">
                        <div class="text">
                            <span style="font-weight:bold;font-size:25px;">{{ item.title }}</span>
                        </div>
                        <div class="bottom">
                            <el-dropdown>
                                <i class="el-icon-more" style="font-size:17px;"></i>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>分享</el-dropdown-item>
                                    <el-dropdown-item>共享</el-dropdown-item>
                                    <el-dropdown-item @click.native="editAlbum">编辑</el-dropdown-item>
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
                selfEmptyTip: true  //是否显示相册为空的提示，默认为true（提示）
                // loginID: this.$route.params.id
                // albumEditedTitle: ''
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
            editAlbum() {
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
                        url: 'http://139.9.205.50/album/change',
                        data: {
                            id: 0,
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
                                // this.$emit('func', res.data)
                                break;
                            case 201: 
                                console.log(value)
                                this.$notify.error({
                                    title: '相册名已经被使用',
                                    message: '请重试'
                                });
                                break;
                            case 401: 
                            case 403: 
                            case 404: 
                                console.log(value)
                                this.$notify.error({
                                    title: '相册创建失败',
                                    message: '请重试'
                                });
                                this.newAlbumInfo = res.data
                                break;
                            case 500:
                                console.log(value)
                                this.$notify.error({
                                    title: '相册创建失败',
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
                this.$axios({
                    method: 'post',
                    url: 'http://139.9.205.50/album/delete/'+id,
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
                            break;
                        case 201:
                        case 401: 
                        case 403: 
                        case 404: 
                            this.$notify.error({
                                title: '相册删除失败',
                                message: '请重试'
                            });
                            this.newAlbumInfo = res.data
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
            // 检查页面加载时相册列表是否为空，空则显示空提示
            checkData() {
                console.log('md'+this.albumList.length+'   '+this.albumList)
                if(this.albumList.length !== 0) {
                    this.selfEmptyTip = false
                }
            },
            // 获取相册列表
            getAlbumList() {
                this.$axios.get('http://139.9.205.50/album/get')
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
                            this.albumList = this.updateAlbumList
                            console.log('md'+this.albumList.length+'   '+this.albumList)
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
        props: ['searchKeywords', 'newAlbumInfo', 'updateAlbumList'],
        mounted() {
            this.getAlbumList()
            this.checkData()
        }
    }
</script>

<style lang='css' scoped>
.startDiv {
    padding:0;
    /* background: url(https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg); */
    /* background-color: #fff; */
    /* opacity: 0.7; */
    /* height: auto; */
    overflow-x: hidden;
}
.cardContainer {
    width: 350px;
    margin-top: 50px;
    margin-left: 70px;
    float: left;
    box-shadow: 15px 10px 3px rgba(0, 0, 0, 0.2);
    transition: all .3s linear;
    cursor: pointer;
}
.cardContainer:hover {
    transition: all .3s linear;
    box-shadow: 30px 30px 10px rgba(0, 0, 0, 0.1);
}
.card {
    background-image: url(~@/assets/Bg15.jpg);
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
    bottom: 10px;
    right: 20px;
    cursor: pointer;
}
.image {
  width: 100%;
  height: 250px;
  display: block;
  cursor: pointer;
}
.text {
    text-align: left;
    padding: 10px;;
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

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>