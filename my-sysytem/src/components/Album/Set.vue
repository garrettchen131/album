<template>
    <div class="setsContainer" ref="setsContainer">

        <!-- <div class="self_emptyTip" v-if="selfEmptyTip">
            你的相册集空空如也呢~😊<br>
            赶紧创建相册开始你的照片管理之旅吧！
        </div> -->

<!-- 二方案 -->
        <div class="block setsTimeline">
            <el-timeline>
                <div  v-for="set in albumSets" :key="set.uploadTime">
                     <el-timeline-item :timestamp="set.uploadTime" placement="top">
                        <!-- <div class="el-timeline-item__tail"></div> -->
                        <el-card class="setCard">
                            <p class="setDesc">{{ set.desc }}</p>
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

                activeNames: ['1'],
                uploadPhotos_dialogTableVisible: false
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
            }
        },
        components: {
            addPhotos
        },
        mounted() {
            
        }
    }
</script>

<style lang='css' scoped>
.setsContainer {
    /* width: 1300px; */
    /* border: 1px solid #000; */
    height: auto;
    /* overflow: hidden; */
    /* background-image: url(~@/assets/Bg6.jpg); */
    background-repeat: no-repeat;
    background-size: 100% 100%;
}
.setsTimeline {
    width: 1200px;
    height: 100%;
    /* position: absolute; */
    /* margin-left: 50px; */
    /* left: 50%; */
    /* border: 1px solid #000; */
    /* padding: 50px; */
    border-left: 3px solid rgb(240, 153, 153);
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
    border-left: 1px solid #ccc;
    overflow-y: scroll;
    margin-right: 10px;
    /* border-bottom: 1px solid #ccc; */
    /* border-radius: 10px; */
    
}
.el-timeline {
    padding: 50px 50px 50px 10px;
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
    position: absolute;
    bottom: 20px;
    right: 40px;
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