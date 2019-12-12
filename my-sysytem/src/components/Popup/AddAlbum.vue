<template>
  <div>
    <el-form :model="addAlbumForm" :rules="rules" ref="addAlbumForm" label-width="100px" class="demo-addAlbumForm">
      <el-form-item label="相册名称" prop="title">
        <el-input v-model="addAlbumForm.title"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitAddAlbumForm('addAlbumForm')">立即创建</el-button>
        <el-button @click="resetAddAlbumForm('addAlbumForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      addAlbumForm: {
        title: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitAddAlbumForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('ok')
          } else {
            // console.log('error submit!!');
            this.$message.error('相册创建失败，请重新创建');
            return false;
          }
        });
    },
    resetAddAlbumForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}

</script>

<style lang="css">
.demo-addAlbumForm {
  width: 700px;
  /* border: 1px solid #ccc; */
  /* border-radius: 10px; */
  /* box-shadow: 20px 20px 15px rgba(0, 0, 0, 0.2); */
  padding: 20px;
}
</style>
