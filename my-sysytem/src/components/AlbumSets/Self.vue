<template>
    <div class="startDiv">


        <div class="self_emptyTip" v-if="selfEmptyTip">
            你的相册空空如也呢~😄<br>
            赶紧创建相册开始你的照片管理之旅吧！
        </div>

        <el-row>
            <div :span="4" v-for="item in search(searchKeywords)" :key="item.id" class="cardContainer">
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
                                    <el-dropdown-item style="color:red;" @click.native="judgeAlbum(item.id)">删除</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>
                        
                    </div>
                </el-card>
            </div>
        </el-row>


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
        <el-dialog 
            title="编辑相册信息" 
            :visible.sync="editAlbum_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="800px"
            top="300px">
            <editAlbum></editAlbum>
        </el-dialog>




    </div>
</template>

<script>
    import editAlbum from '@/components/Popup/EditAlbumInfo';
    export default {
        data(){
            return {
                currentDate: new Date(),
                albumList: [    // 这里的数据用axios向后端发请求，请求数据
                    {
                        id: 1,
                        title: '美好时光',
                        introduction: '记录了我高中青春哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈',
                        createTime: '2019-12-18 Tue',
                        sets: [
                            {
                                uploadTime: '2019-12-15 Tue',
                                desc: '今天去了都江堰，记录一下通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。',
                                photos: [
                                    'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
                                    'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg',
                                    'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                                    'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg'
                                ]
                            },
                            {
                                uploadTime: '2019-12-18 Tue',
                                 desc: '今天去了都江堰，记录一下',
                                photos: [
                                    'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                                    'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
                                ]
                            },
                        ]
                    },
                    {
                        id: 2,
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！',
                        createTime: '2019-12-18 Tue',
                        sets: [
                            
                        ]
                    }
                ],
                deleteAlbum_dialogVisible: false,
                editAlbum_dialogTableVisible: false,
                selfEmptyTip: true
            }
        },
        methods:{
            search(searchKeywords) {
                var newalbumList = this.albumList.filter(item => {
                    if(item.title.includes(searchKeywords)) {
                        return item
                    }
                })
                return newalbumList
            },
            editAlbum() {
                // var htmlStr = '<h1>message</h1>'
                // this.$alert(htmlStr, '编辑《'+item.title+item.id+'》相册信息', {
                //     dangerouslyUseHTMLString: true
                // });
                this.editAlbum_dialogTableVisible=true;
            },
            openAlbum(item) {
                this.$router.push({ name: 'set', params: item })
            },
            deleteAlbum(id) {
                // this.deleteAlbum_dialogVisible = false
                // var index = this.albumList.indexOf(this.checkedPhotos[i])
                // this.photoList.splice(index, 1)
                // this.checkedPhotos = []
            },
            judgeAlbum() {
                this.deleteAlbum_dialogVisible = true
            },
            handleClose(done) {
                done()
            },
            checkData() {
                if(this.albumList.length !== 0) {
                    this.selfEmptyTip = false
                }
            }
        },
        components: {
            editAlbum
        },
        props: ['searchKeywords', 'newAlbumInfo'],
        mounted() {
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
    font-size: 60px;
    font-weight: bold;
    color: #7a735d;
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