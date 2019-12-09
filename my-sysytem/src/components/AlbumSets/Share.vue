<template>
    <div class="startDiv">

        <div class="share_emptyTip" v-if="shareEmptyTip">
            还没有和小伙伴共享的相册哦~😁<br>
            去共享相册和大家分享你的美好生活吧！
        </div>

        <!-- <h1>这是 自己的相册 组件</h1> -->
        <el-row>
            <div :span="4" v-for="item in search(searchKeywords)" :key="item.id" class="card">
                <el-card :body-style="{ padding: '0px' }" @click.native="openAlbum(item)">
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
                                <el-dropdown-item>编辑</el-dropdown-item>
                                <el-dropdown-item style="color:red;">删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </div>
                </el-card>
            </div>
        </el-row>
    </div>
</template>

<script>
    export default {
        data(){
            return {
                currentDate: new Date(),
                albumList: [    // 这里的数据用axios向后端发请求，请求数据
                    // {
                    //     id: 1,
                    //     coverImage: 'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg',
                    //     title: '美好时光',
                    //     introduction: '记录了我高中青春哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈',
                    //     createTime: '2019-12-18 Tue',
                    //     sets: [
                    //         {
                    //             uploadTime: '2019-12-15 Tue',
                    //             desc: '今天去了都江堰，记录一下通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。',
                    //             photos: [
                    //                 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                    //                 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg',
                    //                 'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                    //                 'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
                    //             ]
                    //         },
                    //         {
                    //             uploadTime: '2019-12-18 Tue',
                    //             desc: '追星之路长漫漫',
                    //             photos: [
                    //                 'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                    //                 'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
                    //             ]
                    //         },
                    //     ]
                    // }
                ],
                shareEmptyTip: true
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
            openAlbum(item) {
                this.$router.push({ name: 'set', params: item })
            },
            checkData() {
                if(this.albumList.length !== 0) {
                    this.shareEmptyTip = false
                }
            }
        },
        props: ['searchKeywords'],
        mounted() {
            this.checkData()
        }
    }
</script>

<style lang='css' scoped>
.startDiv {
    padding:0 0 50px 70px;
    /* background: url('https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'); */
    /* height: 827px; */
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
.share_emptyTip{
    font-size: 60px;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.1);
    text-shadow: 10px 12px 5px rgba(0, 0, 0, 0.05);
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