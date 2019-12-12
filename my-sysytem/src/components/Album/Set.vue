<template>
    <div class="setsContainer" ref="setsContainer">

        <div class="selfEmptyTip" v-if="selfEmptyTip">
            你的相册集好空呢~😊快上传一组吧<br>
        </div>

        <el-button-group class="pageHeaderBtn">
            <el-button type="primary" icon="el-icon-arrow-left" @click="goBack">返回</el-button>
            <el-button type="primary">{{albumTitle}}</el-button>
        </el-button-group>

<!-- 二方案 -->
        <div class="block setsTimeline">
            <el-timeline>
                <div  v-for="set in albumSets" :key="set.uploadTime">
                     <el-timeline-item :timestamp="set.uploadTime" placement="top">
                        <!-- <div class="el-timeline-item__tail"></div> -->
                        <el-card class="setCard">
                            <p class="setDesc" @click="showAllDesc(set.desc)">{{ set.desc }}</p>
                            <div v-for="photo in set.photos.slice(0,3)" :key="photo" @click="toPreview(set)">
                                <div class="imgContainer">
                                    <el-image
                                        class="setPhoto"
                                        :src="photo"
                                        fit="contain">
                                    </el-image>
                                </div>
                            </div>
                            <el-tooltip class="item" effect="dark" content="上传图片" placement="right-start">
                                <el-button type="danger" icon="el-icon-upload2" circle title="上传图片" @click="uploadPhotos" class="setting"></el-button>
                            </el-tooltip>
                        </el-card>
                    </el-timeline-item>
                </div>
            </el-timeline>
        </div>


        <el-tooltip class="item" effect="dark" content="添加相册集" placement="top-end">
            <el-button 
                class="addSet"
                type="primary" 
                icon="el-icon-plus" 
                circle title="添加相册集" 
                @click="uploadPhotos">
            </el-button>
        </el-tooltip>


        <el-dialog 
            title="添加图片" 
            :visible.sync="uploadPhotos_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="840px"
            position="absolute"
            top="50px">
            <addPhotos></addPhotos>
        </el-dialog>

        <el-drawer
            :visible.sync="drawer"
            :with-header="false">
            <span>{{detailDesc}}</span>
        </el-drawer>




    </div>
</template>

<script>
    import addPhotos from '@/components/Popup/AddPhotos';
    export default {
        data(){
            return {
                albumTitle: '《'+this.$route.params.title+'》',
                albumCreateTime: this.$route.params.createTime,
                albumCoverImage: this.$route.params.coverImage,
                albumSets: this.$route.params.sets,
                drawer: false,
                detailDesc: '',
                activeNames: ['1'],
                uploadPhotos_dialogTableVisible: false,
                selfEmptyTip: true
            }
        },
        methods:{
            goBack() {
                this.$router.go(-1)
            },
            handleClose(done) {
                done()
            },
            handleChange(val) {
                console.log(val);
            },
            toPreview(set) {
                this.$router.push({ name: 'album', params: set })
            },
            uploadPhotos() {
                this.uploadPhotos_dialogTableVisible = true
            },
            showAllDesc(msg) {
                this.drawer = true
                this.detailDesc = msg
            },
            checkIsHavedSet() {
                if(this.albumSets.length !== 0) {
                    this.selfEmptyTip = false
                }
            }
        },
        components: {
            addPhotos
        },
        mounted() {
            // this.checkIsHavedSet()
        }
    }
</script>

<style lang='css' scoped>
.pageHeaderBtn {
    position: fixed;
    top: 0;
    left: 0;
}
.selfEmptyTip {
    font-size: 50px;
    font-family: 幼圆;
    font-weight: bold;
    text-shadow: 8px 8px 5px rgba(204, 159, 159, 0.952);
    color: #7a735d;
    opacity: 0.7;
    height: 80px;
    position: absolute;
    top: 60px;
    left: 50%;
    margin-left: -404.5px;
}
.setsContainer {
    /* width: 1300px; */
    /* border: 1px solid #000; */
    height: auto;
    /* overflow: scroll; */
    /* background-image: url(~@/assets/Bg6.jpg); */
    /* background-repeat: no-repeat; */
    /* background-size: 100% 100%; */
}
.setsTimeline {
    position: absolute;
    left: 300px;
    right: 300px;
    top: 50px;
    /* border: 1px solid #000; */
    box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.15);
    margin: 0 auto;
}
.setting {
    position: absolute;
    bottom: 10px;
    right: 10px;
}
.setPhoto {
    width: 170px;
    height: 170px;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 3px;
    margin-right: 20px;
}

.el-divider__text, .el-link {
    font-size: 18px;
    color: black;
}
.setDesc {
    /* position: absolute;
    bottom: 0;
    right: 0; */
    width: 260px;
    height: 195px;
    margin: 0;
    float: right;
    padding: 0 10px 0 40px;
    font-size: 18px;
    color: rgb(133, 128, 128);
    line-height: 37px;
    border-left: 1px solid #ccc;
    overflow-y: hidden;
    margin-right: 10px;
    cursor: pointer;
    text-overflow: ellipsis;
    overflow: hidden;
    display:-webkit-box;
    -webkit-line-clamp: 5;
    -webkit-box-orient:vertical;
    /* border-bottom: 1px solid #ccc; */
    /* border-radius: 10px; */
    
}
.el-timeline {
    padding: 0px 50px 100px 20px;
}
.setCard {
    padding: 50px;
    opacity: 0.9;
}
.setThumbnail {
    /* float: left; */
    border: 1px solid #000;
}
.imgContainer {
    float:left;
    padding:10px;
    /* border: 1px solid #000; */
}
.imgContainer:hover {
    cursor: pointer;
}
.addSet {
    position: fixed;
    bottom: 20px;
    right: 30px;
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