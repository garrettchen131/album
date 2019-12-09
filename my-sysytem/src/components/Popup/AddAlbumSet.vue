<template>
  <div>
    <el-form :model="addAlbumSetForm" :rules="rules" ref="addAlbumSetForm" label-width="100px" class="demo-addAlbumSetForm">
      <el-form-item label="相册封面" prop="cover">
        <el-upload
          class="avatar-uploader"
          action="https://www.mocky.io/v2/5185415ba171ea3a00704eed/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="相册名称" prop="name">
        <el-input v-model="addAlbumSetForm.name"></el-input>
      </el-form-item>
      <el-form-item label="相册简介" prop="desc">
        <el-input type="textarea" v-model="addAlbumSetForm.desc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitAddAlbumForm('addAlbumSetForm')">立即创建</el-button>
        <el-button @click="resetAddAlbumForm('addAlbumSetForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    var checkCover = (rule, value, callback) => {
      if (this.imageUrl==='') {
          return callback(new Error('请选择相册封面'));
      } else {
        callback()
      }
    };
    return {
      imageUrl: '',
      addAlbumSetForm: {
        name: '',
        desc: ''
      },
      rules: {
        cover: [
          { validator: checkCover, trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitAddAlbumForm(formName) {
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
    resetAddAlbumForm(formName) {
      this.$refs[formName].resetFields();
      this.imageUrl = ''
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
}

</script>

<style lang="css">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.demo-addAlbumSetForm {
  width: 700px;
  /* border: 1px solid #ccc; */
  /* border-radius: 10px; */
  /* box-shadow: 20px 20px 15px rgba(0, 0, 0, 0.2); */
  padding: 20px;
}
</style>
