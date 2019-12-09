<template>
    <div style="padding-bottom:50px;">

        <!-- <div class="self_emptyTip" v-if="selfEmptyTip">
            你的相册集空空如也呢~😊<br>
            赶紧创建相册开始你的照片管理之旅吧！
        </div> -->

        <div class="pageHeader">
            <el-image :src="albumCoverImage" style="width:100%;height:100px;" fit="cover"></el-image>
            <div style="position: absolute;top:46px;left:10px;">
                <el-button icon="el-icon-arrow-left" circle @click="goBack" style="background:rgba(255,255,255,0.6);color:black;"></el-button>
                <el-button style="font-size:18px;background:rgba(255,255,255,0.6);color:black;">{{ albumTitle }}</el-button>
            </div>
        </div>

        <div class="set" v-for="set in albumSets" :key="set.uploadTime" @click="toPreview(set)">

            <el-divider content-position="left">
                {{ set.uploadTime }}
            </el-divider>

            <div style="padding:10px 50px 10px 200px;">
                {{ set.desc }}
            </div>

            <div style="padding: 10px 10px 20px 50px;">
                <div class="demo-image__preview" v-for="photo in set.photos" :key="photo" style="color:#fff;">
                    <div style="float:left;padding:10px;" >
                        <el-image
                            class="photo"
                            :src="photo"
                            fit="contain">
                        </el-image>
                    </div>
                </div>
            </div>

        </div>





        <!-- <div class="block" style="margin-top:50px;">
            <el-timeline>
                <div  v-for="set in albumSets" :key="set.uploadTime">
                     <el-timeline-item :timestamp="set.uploadTime" placement="top">
                        <div class="el-timeline-item__tail"></div>
                        <el-card class="setCard">
                            <p class="setDesc">{{ set.desc }}</p>
                            <div style="padding: 10px 10px 20px 50px;">
                                <div class="demo-image__preview" v-for="photo in set.photos" :key="photo" style="color:#fff;">
                                    <div style="float:left;padding:10px;" >
                                        <el-image
                                            class="photo"
                                            :src="photo"
                                            fit="contain">
                                        </el-image>
                                    </div>
                                </div>
                            </div>
                        </el-card>
                    </el-timeline-item>
                </div>
            </el-timeline>
        </div> -->


       


    </div>
</template>

<script>
    export default {
        data(){
            return {
                albumTitle: '《'+this.$route.params.title+'》',
                albumCreateTime: this.$route.params.createTime,
                albumCoverImage: this.$route.params.coverImage,
                albumSets: this.$route.params.sets,

                activeNames: ['1']
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
            }


        }
    }
</script>

<style lang='css' scoped>
.pageHeader {
    height:100px;
}
.content {
    text-align: right;
}
.setting {
    position: fixed;
    bottom: 20px;
    right: 20px;
}
.photo {
    width: 50px;
    height: 50px;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 3px;
}
.el-divider__text, .el-link {
    font-size: 18px;
}
/* .el-divider__text[data-v-24f327a2], .el-link[data-v-24f327a2] {
    font-size: 16px;
    color: rgb(194, 189, 189);
} */
.el-divider__text, .el-link {
    font-size: 18px;
    color: black;
}
.set {
    width: 90%;
    margin-left: 2.5%;
    padding: 20px 50px 90px 50px;
    margin-top: 20px;
    /* border: 1px solid #000; */
    box-shadow: 3px 2px 15px rgba(0, 0, 0, 0.15);
}
.set:hover {
    cursor: pointer;
}
.setDesc {
    margin: 0;
}
.el-timeline {
    padding-right: 40px;
}
.setCard {
    padding: 5px 30px 10px 10px;
}
</style>