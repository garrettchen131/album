<template>
  <div>
    <el-form :model="addPhotosForm" ref="addPhotosForm" label-width="20px" class="demo-addPhotosForm">
            <el-upload
                class="upload-demo"
                ref="uploadPhotos"
                drag
                action="https://www.mocky.io/v2/5185415ba171ea3a00704eed/posts/"
                multiple
                style="padding:20px;">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        <el-form-item>
            <el-button type="primary" @click="submitPhotos('addPhotosForm')">立即添加</el-button>
            <el-button @click="resetPhotos()">重新选择</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
        addPhotosForm: {

        }
    }
  },
  methods: {
    submitPhotos(formName) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.$message({
              message: '恭喜你，相册创建成功',
              type: 'success'
            });
          } else {
            // console.log('error submit!!');
            this.$message.error('相册创建失败，请重新创建');
            return false;
          }
        });
    },
    resetPhotos() {
      this.$refs.uploadPhotos.clearFiles();
    }
  }
}

</script>

<style lang="css">
.demo-addPhotosForm {
    width: 740px;
    padding: 0 20px 0 20px;
    /* border: 1px solid #000; */
}
.el-upload-dragger {
    /* width: 380px; */
    width: 700px;
}
.upload-demo {
    width: 700px;
}
/* .el-upload-list__item-name {
    padding: 2px;
} */
.el-upload-list {
  height: 350px;
  padding: 10px;
  overflow-y: scroll;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 10px 0 0 10px;
  margin-top: 20px;
}
</style>
