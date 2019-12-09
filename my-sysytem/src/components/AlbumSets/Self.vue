<template>
    <div class="startDiv">



        <el-row>
            <div :span="4" v-for="item in search(searchKeywords)" :key="item.id" class="card">
                <el-card :body-style="{ padding: '0px' }"  @click.native="openAlbum(item)">
                    <el-image :src="item.coverImage" class="image" fit="cover"></el-image>
                    <div style="padding: 14px;position:relative;">
                        <div class="text">
                            <span style="font-weight:bold;font-size:18px;">《{{ item.title }}》</span>
                            <p class="info" :title="item.introduction">{{ item.introduction }}</p>
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
            width="800px">
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
                        coverImage: 'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg',
                        title: '美好时光',
                        introduction: '记录了我高中青春哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈',
                        photos: [
                            'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                            'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg',
                            'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
                        ]
                    },
                    {
                        id: 2,
                        coverImage: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    },
                    {
                        id: 3,
                        coverImage: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                        title: '小鸟',
                        introduction: '你徐家汇反对车u的成分货色间出现'
                    },
                    {
                        id: 4,
                        coverImage: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                        title: '小鸟',
                        introduction: '你徐家汇反对车u的成分货色间出现'
                    },
                    {
                        id: 5,
                        coverImage: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    },
                    {
                        id: 6,
                        coverImage: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    },
                    {
                        id: 7,
                        coverImage: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    },
                    {
                        id: 8,
                        coverImage: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    },
                    {
                        id: 9,
                        coverImage: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                        title: '环游世界',
                        introduction: '珠穆朗玛峰我来啦！'
                    }
                ],
                deleteAlbum_dialogVisible: false,
                editAlbum_dialogTableVisible: false
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
                this.$router.push({ name: 'album', params: item })
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
            }
        },
        components: {
            editAlbum
        },
        props: ['searchKeywords']
    }
</script>

<style lang='css' scoped>
.startDiv {
    padding:0 0 100px 70px;
    /* background: url(.././assets/Bg2.jpg); */
    height: auto;
}
.card {
    width: 350px;
    margin-top: 50px;
    margin-left: 70px;
    float: left;
    box-shadow: 15px 10px 3px rgba(0, 0, 0, 0.4);
    transition: all .3s linear;
    
}
.card:hover {
    transition: all .3s linear;
    box-shadow: 30px 30px 10px rgba(0, 0, 0, 0.3);
}
/* .card:nth-child(4n+1) {
    clear: left;
    margin-left: 130px;
} */
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
    text-align: left
}
.info {
  line-height: 45px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
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