<template>
    <div class="startDiv">
        <!-- <h1>这是 自己的相册 组件</h1> -->
        <el-row>
            <div :span="4" v-for="item in search(searchKeywords)" :key="item.id" class="card">
                <el-card :body-style="{ padding: '0px' }">
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
                    {
                        id: 1,
                        coverImage: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
                        title: '美好时光',
                        introduction: '记录了我高中青春哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈'
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
                    }
                ]
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
            }
        },
        props: ['searchKeywords']
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
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>