<template>
  <div class="setsContainer" ref="setsContainer">
    <div class="selfEmptyTip" v-if="selfEmptyTip">
      你的相册集好空呢~😊快上传一组吧
      <br />
    </div>

    <el-button-group class="pageHeaderBtn">
      <el-button type="primary" icon="el-icon-arrow-left" @click="goBack">返回</el-button>
      <el-button type="primary">{{albumTitle}}</el-button>
    </el-button-group>

    <!-- 二方案 -->
    <div class="block setsTimeline">
      <el-timeline>
        <div v-for="set in albumSets" :key="set.id">
          <el-timeline-item :timestamp="set.title" placement="top">
            <el-card class="setCard">
              <p class="setDesc" @click="showAllDesc(set)">{{ set.desc }}</p>
              <div
                v-for="photo in selectThreePhoto(set.photoIdList)"
                :key="photo"
                @click="toPreview(set)">
                <div class="imgContainer">
                    <el-image 
                        class="setPhoto" 
                        :src="reqPhotoUrl(set.id, photo)" 
                        fit="contain">
                    </el-image>
                </div>
              </div>
              <el-tooltip class="item" effect="dark" content="上传图片" placement="right-start">
                <el-button
                  type="danger"
                  icon="el-icon-upload2"
                  circle
                  title="上传图片"
                  @click="uploadPhotos(set.id)"
                  class="setting"
                ></el-button>
              </el-tooltip>
              <el-tooltip class="item" effect="dark" content="删除相册集" placement="right-start">
                <i class="el-icon-close deleteAlbumSetBtn" @click="deleteAlbumSet(set.id)"></i>
              </el-tooltip>
            </el-card>
          </el-timeline-item>
        </div>
      </el-timeline>
    </div>

    <el-button
      type="danger"
      class="addSet"
      icon="el-icon-plus"
      circle
      title="新建相册集"
      @click="createAlbumSet()"
    ></el-button>

    <!-- <el-tooltip class="item" effect="dark" content="添加相册集" placement="top-end">
            <el-button 
                class="addSet"
                type="primary" 
                icon="el-icon-plus" 
                circle title="添加相册集" 
                @click="uploadPhotos">
            </el-button>
    </el-tooltip>-->

    <el-dialog
      title="添加图片"
      :visible.sync="uploadPhotos_dialogTableVisible"
      center
      :append-to-body="true"
      :lock-scroll="false"
      width="840px"
      position="absolute"
      top="50px"
    >
      <addPhotos :parentSetId="uploadSetId"></addPhotos>
      <!-- <addPhotos :parentSetId="uploadSetId" @func="getUploadPhotoList"></addPhotos> -->
    </el-dialog>

    <el-drawer :visible.sync="drawer" :with-header="false">
      <h3 v-if="editDesc">{{setInfo.setTitle}}</h3>
      <span v-if="editDesc">{{setInfo.detailDesc}}</span>

      <!-- <el-form ref="form" label-width="100px" v-if="!editDesc">
                <el-form-item label="相册集名称">
                    <el-input v-model="setInfo.setTitle"></el-input>
                </el-form-item>
                <el-form-item label="相册集描述">
                    <el-input type="textarea" v-model="setInfo.detailDesc"></el-input>
                </el-form-item>
      </el-form>-->

      <el-form :model="setInfo" ref="setInfo" label-width="100px" v-if="!editDesc">
        <el-form-item label="相册集名称" prop="title">
          <el-input v-model="setInfo.setTitle"></el-input>
        </el-form-item>
        <el-form-item label="相册集描述" prop="desc">
          <el-input type="textarea" v-model="setInfo.detailDesc"></el-input>
        </el-form-item>
      </el-form>

      <div class="editDescBtn">
        <el-tooltip class="item" effect="dark" content="编辑信息" placement="top-start">
          <el-button type="primary" icon="el-icon-edit" circle v-if="editDesc" @click="toEditDesc"></el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="保存编辑" placement="top-start">
          <el-button
            type="success"
            icon="el-icon-check"
            circle
            v-if="!editDesc"
            @click="toSaveDesc"
          ></el-button>
        </el-tooltip>
        <el-button type="danger" icon="el-icon-close" circle v-if="!editDesc" @click="toCancelDesc"></el-button>
      </div>
    </el-drawer>




  </div>
</template>

<script>
import addPhotos from "@/components/Popup/AddPhotos";
export default {
  data() {
    return {
      albumID: this.$route.params.id,
      albumTitle: "《" + this.$route.params.title + "》",
      // albumCreateTime: this.$route.params.createTime,
      // albumCoverImage: this.$route.params.coverImage,
      albumSets: [],
      drawer: false,
      setInfo: {
        setID: "",
        detailDesc: "",
        setTitle: ""
      },
      oldDesc: "",
      oldTitle: "",
      activeNames: ["1"],
      uploadPhotos_dialogTableVisible: false,
      selfEmptyTip: true,
      editDesc: true,
      visible: false,
      uploadSetId: ""
      // photoList: []
    };
  },
  methods: {
    goBack() {
        // this.$router.go(-1);
        this.$router.push('/main/self')
    },
    handleClose(done) {
      done();
    },
    handleChange(val) {
      console.log(val);
    },
    toPreview(set) {
        this.$router.push({ name: 'album', params: { set: set, albumID: this.albumID, albumTitle: this.albumTitle } })
    },
    uploadPhotos(setId) {
      this.uploadPhotos_dialogTableVisible = true;
      this.uploadSetId = setId;
    },
    showAllDesc(set) {
      this.drawer = true;
      this.setInfo.detailDesc = set.desc;
      this.setInfo.setTitle = set.title;
      this.setInfo.setID = set.id;
      this.oldDesc = set.desc;
      this.oldTitle = set.title
    },
    createAlbumSet() {
      this.$confirm("是否要在该相册中新建一个相册集?", "新建相册集", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      })
        .then(() => {
          this.$axios({
            method: "post",
            url: "http://192.168.31.49/folder/add/" + this.albumID,
            header: {
              "Content-Type": "application/json;charset=UTF-8"
            }
          })
            .then(res => {
              console.log(res);
              var code = res.data.code;
              switch (code) {
                case 200:
                  this.$notify({
                    title: "相册集创建成功",
                    message: "快去上传一组照片试试吧！",
                    type: "success"
                  });
                  // this.albumSets = res.data.data
                  this.getAlbumSets();
                  break;
                case 201:
                case 401:
                case 403:
                case 404:
                  this.$notify.error({
                    title: "相册集创建失败",
                    message: "请重试"
                  });
                  // this.newAlbumInfo = res.data
                  break;
                case 500:
                  this.$notify.error({
                    title: "相册集创建失败",
                    message: "500"
                  });
                  break;
              }
            })
            .catch(err => {
              console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消创建"
          });
        });
    },
    toEditDesc() {
      this.editDesc = false;
    //   this.oldDesc = this.detailDesc;
    },
    toSaveDesc() {
      // console.log(this.setInfo.setID+"  "+this.setInfo.setTitle+"  "+this.setInfo.detailDesc)
      if (this.setInfo.setTitle === "" || this.setInfo.detailDesc === "") {
        this.$notify.error({
          title: "相册集名称和相册集描述不能为空",
          message: "请填写"
        });
      } else {
        this.$axios({
          method: "post",
          url: "http://192.168.31.49/folder/change",
          data: {
            id: this.setInfo.setID,
            title: this.setInfo.setTitle,
            desc: this.setInfo.detailDesc
          },
          header: {
            "Content-Type": "application/json;charset=UTF-8"
          }
        })
          .then(res => {
            console.log(res);
            var code = res.data.code;
            switch (code) {
              case 200:
                this.$notify({
                  title: "相册集简介修改成功",
                  message: "",
                  type: "success"
                });
                // this.editDesc = false
                this.toCancelDesc();
                this.getAlbumSets();
                break;
              case 201:
              case 401:
              case 403:
              case 404:
                this.$notify.error({
                  title: "相册集简介修改失败",
                  message: "请重试"
                });
                this.toCancelDesc();
                // this.editDesc = false
                // this.newAlbumInfo = res.data
                break;
              case 500:
                this.$notify.error({
                  title: "相册集简介修改失败",
                  message: "500"
                });
                this.toCancelDesc();
                // this.editDesc = false
                break;
            }
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    toCancelDesc() {
      this.editDesc = true;
      this.setInfo.detailDesc = this.oldDesc;
      this.setInfo.setTitle = this.oldTitle
    },
    deleteAlbumSet(setID) {
      this.$confirm("此操作将永久删除该相册集, 是否继续?", "删除相册集", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios({
            method: "post",
            url: "http://192.168.31.49/folder/delete/" + setID,
            header: {
              "Content-Type": "application/json;charset=UTF-8"
            }
          })
            .then(res => {
              console.log(res);
              var code = res.data.code;
              switch (code) {
                case 200:
                  this.$notify({
                    title: "相册集删除成功",
                    message: "",
                    type: "success"
                  });
                  // this.albumSets = res.data.data
                  this.getAlbumSets();
                  break;
                case 201:
                case 401:
                case 403:
                case 404:
                  this.$notify.error({
                    title: "相册集删除失败",
                    message: "请重试"
                  });
                  // this.newAlbumInfo = res.data
                  break;
                case 500:
                  this.$notify.error({
                    title: "相册集删除失败",
                    message: "500"
                  });
                  break;
              }
            })
            .catch(err => {
              console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    reqPhotoUrl(setId, photoId) {
        return "http://192.168.31.49/photo/thumb/" + setId + "/" + photoId
    },
    selectThreePhoto(photoList) {
        var len = photoList.length
        if(len >= 5) {
            return photoList.slice(0,5)
        } else if(len>0 && len<5) {
            return photoList 
        } else {
            return 0
        }
    },
    // getUploadPhotoList(photoList) {
    //     this.photoList = photoList
    // },
    ///////////////////////////////////////////////////【mounted执行】
    // 获取相册集列表
    getAlbumSets() {
      this.$axios
        .get("http://192.168.31.49/folder/get/" + this.albumID)
        .then(res => {
          console.log(res);
          var code = res.data.code;
          switch (code) {
            case 200:
              this.$notify({
                title: "相册集获取成功",
                message: "快去看看",
                type: "success"
              });
              this.albumSets = res.data.data;
              if (this.albumSets.length > 0) {
                this.selfEmptyTip = false;
              }
              break;
            case 201:
            case 401:
            case 403:
            case 404:
              this.$notify.error({
                title: "相册集创建失败",
                message: "请重试"
              });
              break;
            case 500:
              this.$notify.error({
                title: "500",
                message: "请先登录！"
              });
              break;
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
    // checkIsHavedSet() {
    //     if(this.albumSets.length !== 0) {
    //         this.selfEmptyTip = false
    //     }
    // }
  },
  components: {
    addPhotos
  },
  mounted() {
    // this.checkIsHavedSet()
    this.getAlbumSets();
  }
};
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
  position: absolute;
  width: 100%;
  height: 200%;
  /* border: 1px solid #000; */
  /* height: auto; */
  /* overflow-y: scroll; */
  background: linear-gradient(-90deg, pink, lightblue);
  /* background-image: url(~@/assets/Bg13.png);
    background-repeat: no-repeat;
    background-size: 100% 100%; */
  overflow: hidden;
}
.setsTimeline {
  position: absolute;
  left: 300px;
  right: 300px;
  top: 0px;
  bottom: 0px;
  /* margin-left: 300px; */
  /* margin-right: 300px; */
  /* margin-top: -10px; */
  overflow-y: scroll;
  /* border: 1px solid rgba(0, 0, 0, 0); */
  box-shadow: 0px 20px 40px rgba(0, 0, 0, 0.25);
  /* margin: 0 auto; */
}
.setting {
  position: absolute;
  bottom: 10px;
  right: 10px;
}
.setPhoto {
  width: 100px;
  height: 100px;
  border: 1px solid rgba(0, 0, 0, 0.15);
  border-radius: 3px;
  margin-right: 20px;
}

.el-divider__text,
.el-link {
  font-size: 18px;
  color: black;
}
.setDesc {
  /* position: absolute;
    bottom: 0;
    right: 0; */
  width: 260px;
  height: 135px;
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
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
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
  float: left;
  padding: 10px;
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
.editDescBtn {
  position: absolute;
  bottom: 15px;
  left: 20px;
}
.deleteAlbumSetBtn {
  position: absolute;
  top: 60px;
  right: 20px;
  cursor: pointer;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>