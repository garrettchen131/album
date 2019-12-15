<template>
    <div class="startDiv">

        <div class="share_emptyTip" v-if="shareEmptyTip">
            还没有和小伙伴共享的相册哦~😁<br>
            去共享相册和大家分享你的美好生活吧！
        </div>


        <el-row class="row">
            <div :span="4" v-for="item in albumList" :key="item.id" class="cardContainer">
                <el-card :body-style="{ padding: '0px' }" class="card">
                    <div style="padding: 20px;position:relative;">
                        <div class="text" @click="openAlbum(item)">
                            <i class="el-icon-connection" style="font-size:28px;margin-right:10px;"></i>
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


    </div>
</template>

<script>
    export default {
        data(){
            return {
                currentDate: new Date(),
                albumList: [],
                shareEmptyTip: true
            }
        },
        methods:{
            // search(searchKeywords) {
            //     var newalbumList = this.albumList.filter(item => {
            //         if(item.title.includes(searchKeywords)) {
            //             return item
            //         }
            //     })
            //     return newalbumList
            // },
            openAlbum(item) {
                this.$router.push({ name: 'set', params: item })
            }
            // checkData() {
            //     if(this.albumList.length !== 0) {
            //         this.shareEmptyTip = false
            //     }
            // }
        },
        // props: ['searchKeywords'],
        mounted() {
            // this.checkData()
        }
    }
</script>

<style lang='css' scoped>
.startDiv {
    padding: 20px 0 20px 20px;
    /* padding:0 0 50px 70px; */
    background: url(~@/assets/Bg5.png);
    overflow-x: hidden;
    /* height: 827px; */
    /* background: url(https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg); */
}
.cardContainer {
    width: 300px;
    margin-top: 50px;
    margin-left: 100px;
    float: left;
    box-shadow: 20px 20px 8px rgba(0, 0, 0, 0.15);
    transition: all .3s linear;
    /* opacity: 0.9; */
    /* background-color: green; */
    /* border: 1px solid yellow; */
    /* cursor: pointer; */
    /* border-radius: 20px; */
}
.cardContainer:hover {
    transition: all .3s linear;
    box-shadow: 5px 5px 13px rgba(0, 0, 0, 0.3);
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
    height: 100px;
    text-align: left;
    padding: 50px;
    cursor: pointer;
    text-overflow: ellipsis;
    /* overflow: hidden;
    display:-webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient:vertical; */
    /* border: 1px solid #000; */
    border-bottom-right-radius: 50%;
    border-top-left-radius: 50%;
    background-image: url(~@/assets/Bg16.png);
    /* background-size: 100% 50%; */
    /* opacity: 0.8; */
    /* background-color: #fff; */
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
    color: #7a735d;
    font-family: 幼圆;
    text-shadow: 8px 8px 5px rgba(204, 159, 159, 0.952);
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -540px;
    margin-top: -80px;
}
.row {
    /* background-image: url(~@/assets/Bg16.png); */
    /* background: linear-gradient(45deg,pink,lightblue); */
    /* background-color: rgba(255, 255, 255, 0.5); */
    /* padding: ; */
    /* border: 1px solid red; */
    position: absolute;
    bottom: 20px;
    top: 20px;
    left: 50px;
    right: 0px;
    padding: 20px 20px 50px 20px;
    overflow-y: scroll;
    /* box-shadow: -10px 0px 15px rgba(0, 0, 0, 0.3); */
    /* opacity: 0.8; */
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