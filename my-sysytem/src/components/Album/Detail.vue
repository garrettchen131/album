<template>
    <div>


        <el-container>
            <el-aside width="350px">
                <div class="photoListContainer"> 
                    <div class="demo-image__preview showPhotoList" v-for="photo in photoList" :key="photo" style="color:#fff;">
                        <div 
                            style="float:left;padding:10px;margin-top:10px;margin-left:10px;cursor:pointer;"
                            @click="showPreviewViewer(photo-1)"
                            v-if="!canCheckDelete">
                            <el-image
                                class="photo"
                                :src="reqPhotoUrl(previewSetId, photo)"
                                fit="contain">
                            </el-image>
                        </div>
                    </div>
                </div>
            </el-aside>
            <el-container>
                <el-main>
                    <el-image-viewer
                        v-if="viewer"
                        width="500px"
                        height="500px"
                        :on-close="closePreviewViewer"
                        :url-list="reqHDPhotoUrl(previewSetId, photoList, this.previewPhotoId)">
                    </el-image-viewer>
                    <p class="noPreviewImgTip" v-if="!viewer">选择要预览的图片</p>
                </el-main>
            </el-container>
        </el-container>







        <el-checkbox 
            class="checkAllBox"
            v-if="canCheckDelete"
            :indeterminate="isIndeterminate" 
            v-model="checkAll" 
            @change="handleCheckAllChange">
            选择所有图片
        </el-checkbox>
        <el-checkbox-group 
            class="checkboxGroup"
            v-model="checkedPhotos" 
            @change="handleCheckedPhotosChange" 
            v-if="canCheckDelete">
            <el-checkbox v-for="photo in photoList" :label="photo" :key="photo" >
                <el-image
                    class="photo"
                    :src="reqPhotoUrl(previewSetId, photo)"
                    fit="contain"
                    >
                </el-image>
            </el-checkbox>
        </el-checkbox-group>


        <div class="setting">
            <el-button type="info" icon="el-icon-arrow-left" circle title="返回" @click="goBack"></el-button>
            <el-button type="primary" icon="el-icon-edit" circle title="批量管理" @click="editPhotos" v-if="!canCheckDelete"></el-button>
            <el-button type="primary" icon="el-icon-close" circle title="取消编辑" @click="canCheckDelete = !canCheckDelete;checkedPhotos = []" v-if="canCheckDelete"></el-button>
            <el-button type="danger" icon="el-icon-delete" circle title="删除图片" @click="judgePhotos" v-if="canCheckDelete"></el-button>
            <!-- <el-button type="success" icon="el-icon-position" circle title="移动到..." @click="addPhotos" v-if="canCheckDelete"></el-button> -->
            <el-button type="danger" icon="el-icon-upload2" circle title="上传图片" @click="addPhotos" v-if="!canCheckDelete"></el-button>
        </div>


        <el-dialog 
            title="添加图片" 
            :visible.sync="addPhotos_dialogTableVisible" 
            center :append-to-body='true' 
            :lock-scroll="false" 
            width="840px"
            position="absolute"
            top="50px">
            <addPhotos></addPhotos>
        </el-dialog>
        <el-dialog
            title="删除图片！"
            :visible.sync="deletePhotos_dialogVisible"
            width="30%"
            top="300px"
            :before-close="handleClose">
            <span>确定要删除这些图片吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="deletePhotos_dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="deletePhotos">确 定</el-button>
            </span>
        </el-dialog>



    </div>
</template>

<script>
    import addPhotos from '@/components/Popup/AddPhotos';
    export default {
        data(){
            return {
                addPhotos_dialogTableVisible: false,
                photoList: this.$route.params.set.photoIdList,
                checkAll: false,
                checkedPhotos: [],
                isIndeterminate: true,
                canCheckDelete: false,
                deletePhotos_dialogVisible: false,
                viewer: false,
                previewSetId: this.$route.params.set.id,
                previewPhotoId: "",
                hadPreview: false,
                goBackAlbumId: this.$route.params.albumID,
                goBackAlbumTitle: this.$route.params.albumTitle
            }
        },
        methods:{
            goBack() {
                // this.$router.go(-1)
                this.$router.push({name: 'set', params: {id: this.goBackAlbumId, title: this.goBackAlbumTitle}})
                // this.showViewer = true
            },
            addPhotos() {
                this.addPhotos_dialogTableVisible = true
            },
            handleCheckAllChange(val) {
                this.checkedPhotos = val ? this.photoList : [];
                this.isIndeterminate = false;
            },
            handleCheckedPhotosChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.photoList.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.photoList.length;
            },
            editPhotos() {
                this.canCheckDelete = true
            },
            judgePhotos() {
                if(this.checkedPhotos.length==0) {
                    this.$message.error('你未选中任何图片，请先选择图片');
                } else {
                    this.deletePhotos_dialogVisible = true
                }
            },
            deletePhotos() {
                this.deletePhotos_dialogVisible = false
                var checkLen = this.checkedPhotos.length
                for(var i = 0; i < checkLen; i++) {
                    var index = this.photoList.indexOf(this.checkedPhotos[i])
                    this.photoList.splice(index, 1)
                }
                this.checkedPhotos = []
            },
            handleClose(done) {
                done()
            },
            closePreviewViewer() {
                this.viewer = false
                // $('.el-image-viewer__wrapper')
            },
            showPreviewViewer(photoId) {
                this.viewer = true
                this.previewPhotoId = photoId
            },
            reqPhotoUrl(setId, photoId) {
                return "http://192.168.31.49/photo/thumb/" + setId + "/" + photoId
            },
            reqHDPhotoUrl(setId, photoList, previewId) {
                var HDList = []
                var len = photoList.length
                // alert(previewId)
                if(this.hadPreview) {
                    // 已经打开了一个预览窗口
                    this.$emit(this.closePreviewViewer)
                    this.hadPreview = false
                }
                if(previewId==len-1) {
                    HDList.push("http://192.168.31.49/photo/view/" + setId + "/" + photoList[previewId])
                    for(var i = 0; i < previewId; i++) {
                        HDList.push("http://192.168.31.49/photo/view/" + setId + "/" + photoList[i])
                    }
                } else if(previewId==0) {
                    for(var i = 0; i < len; i++) {
                        HDList.push("http://192.168.31.49/photo/view/" + setId + "/" + photoList[i])
                    }
                } else {
                    for(var i = previewId; i < len; i++) {
                        HDList.push("http://192.168.31.49/photo/view/" + setId + "/" + photoList[i])
                    }
                    for(var j = 0; j <previewId; j++) {
                        HDList.push("http://192.168.31.49/photo/view/" + setId + "/" + photoList[j])
                    }
                }
                
                
                
                return HDList
            }
        },
        components: {
            addPhotos,
            'el-image-viewer':()=>import('element-ui/packages/image/src/image-viewer')
        }
    }
</script>

<style lang='css' scoped>
.content {
    text-align: right;
}
.setting {
    position: fixed;
    bottom: 10px;
    left: 20px;
}
.photo {
    width: 120px;
    height: 120px;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 5px;
    /* margin-left: 5px; */
}
.checkAllBox {
    position: absolute;
    bottom: 20px;
    left: 200px;
}
.checkboxGroup {
    /* padding: 20px 50px 100px 50px; */
    margin-top:15px;
    width: 400px;
}
.el-checkbox {
    margin-right: 0;
    margin-left: 10px;
    margin-top: 20px;
}
/* .el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
 
} */

.el-aside {
  background-color: rgb(253, 239, 239);
  color: #333;
  text-align: center;
  padding: 7px ;
  height: 100%;
  width: 300px;
  /* line-height: 200px; */
  position: fixed;
  top: 0;
  left: 0;
}

.el-main {
  /* background-color: #ccc; */
  color: #333;
  text-align: center;
  /* line-height: 160px; */
  padding: 0;
  /* border-left: 1px solid #000; */
  position: fixed;
  top: 0;
  left: 350px;
}

.noPreviewImgTip {
    font-size: 60px;
    color: #7a735d;
    font-weight: bold;
    font-family: 幼圆;
    text-shadow: 8px 8px 5px rgba(204, 159, 159, 0.952);
    position: fixed;
    left: 50%;
    top: 50%;
    margin-left: -53px;
    margin-top: -33px;
}



body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}


.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.photoListContainer {
    position: fixed;
    width: 320px;
    top: 0;
    left: 0;
    bottom: 65px;
    padding: 15px;
    overflow-y: scroll;
    /* border: 1px solid red; */
}
</style>