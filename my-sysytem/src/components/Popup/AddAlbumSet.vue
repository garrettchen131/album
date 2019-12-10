<template>
  <div>
    <el-form :model="addAlbumSetForm" :rules="rules" ref="addAlbumSetForm" label-width="100px" class="demo-addAlbumSetForm">
      <el-form-item label="相册名称" prop="name">
        <el-input v-model="addAlbumSetForm.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitAddAlbumForm('addAlbumSetForm',addAlbumSetForm)">立即创建</el-button>
        <el-button @click="resetAddAlbumForm('addAlbumSetForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      addAlbumSetForm: {
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
    submitAddAlbumForm(formName,formInfo) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.$emit('func', formInfo)


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
    }
  }
}

</script>

<style lang="css">
.demo-addAlbumSetForm {
  width: 700px;
  /* border: 1px solid #ccc; */
  /* border-radius: 10px; */
  /* box-shadow: 20px 20px 15px rgba(0, 0, 0, 0.2); */
  padding: 20px;
}
</style>
