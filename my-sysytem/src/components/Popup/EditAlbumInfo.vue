<template>
  <div>
    <el-form :model="editAlbumForm" :rules="rules" ref="editAlbumForm" label-width="100px" class="demo-editAlbumForm">
      <el-form-item label="相册名称" prop="name">
        <el-input v-model="editAlbumForm.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitEditAlbumForm('editAlbumForm')">修改</el-button>
        <el-button @click="resetEditAlbumForm('editAlbumForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      editAlbumForm: {
        name: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitEditAlbumForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.$message({
              message: '恭喜你，相册信息修改成功',
              type: 'success'
            });
          } else {
            // console.log('error submit!!');
            this.$message.error('相册信息修改失败，请重新填写');
            return false;
          }
        });
    },
    resetEditAlbumForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}

</script>

<style lang="css">
.demo-addAlbumSetForm {
  width: 700px;
  padding: 20px;
}
</style>
