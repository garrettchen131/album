<template>
    <div>
        
        <el-form :model="addPhotosForm" ref="addPhotosForm" label-width="20px" class="demo-addPhotosForm">
            <el-form-item>
                <el-upload
                    class="upload-demo"
                    ref="uploadPhotos"
                    accept="image/png,image/gif,image/jpg,image/jpeg"
                    drag
                    multiple
                    action="url"
                    :auto-upload="false"
                    :http-request="postPhotos"
                    style="padding:20px;">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitPhotos()">上传</el-button>
                <el-button @click="resetPhotos()">清空上传列表内容</el-button>
            </el-form-item>
        </el-form>

<!-- 

:before-upload="beforeUpload"
                    :on-success="handleSuccess"
                    :on-remove="handleRemove"
                    :on-preview="handlePreview"
                    :on-error="handleError" -->
<!-- :http-request="postPhotos" -->
 <!-- action="http://192.168.31.49/photo/upload/14" -->

        <!-- <form :action="url" enctype="multipart/form-data" method="post" id="myForm">
            <table>
                <tr>
                    <td>上传图片</td>
                    <td>
                        <input class="fl" type="file" id="imgPath" name="photo" multiple="multiple"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="submit">
                    </td>
                </tr>
            </table>
        </form> -->

    </div>
</template>

<script>
export default {
  data () {
    return {
        addPhotosForm: {

        },
        fileList: [],
        // formData: [],
        // photoList: [],
        url: 'http://192.168.31.49/photo/upload/' + this.parentSetId,
    }
  },
  methods: {
        submitPhotos() {
            this.$refs.uploadPhotos.submit()


            // this.$axios.post('http://192.168.31.49/photo/upload/'+this.parentSetId, this.formData)
            // .then((res) => {
            //     console.log(res)
            //     var code = res.data.code
            //     switch(code) {
            //         case 200: 
            //             this.$notify({
            //                 title: '上传成功',
            //                 message: '',
            //                 type: 'success'
            //             });
            //             this.resetPhotos()
            //             // this.photoList = res.data.data.photoIdList
            //             this.$emit('func')
            //             this.$router.push('set')
            //             break;
            //         case 201: 
            //         case 401: 
            //         case 403: 
            //         case 404:  
            //             this.$notify.error({
            //                 title: '失败',
            //                 message: '请重试'
            //             });
            //             // this.newAlbumInfo = res.data
            //             break;
            //         case 500:
            //             this.$notify.error({
            //                 title: '请先登录',
            //                 message: '500'
            //             });
            //             break;
            //     }
            // }).catch((err) => {
            //     console.log(err)
            // })

        },
        postPhotos(item) {
            // this.formData = new FormData();
            // this.formData.append('photo', item.file)
            // this.formData.append('file', item.file)
            let formData = new FormData();
            // formData.append('name', item.file.name)
            formData.append('photo', item.file)
            // console.log('上传图片接口-参数', item.file)
            // console.log('上传图片名称', item.file.name)
            // console.log('FORMDATA:' + this.formData)

            this.$axios.post('http://192.168.31.49/photo/upload/'+this.parentSetId, formData)
            .then((res) => {
                console.log(res)
                var code = res.data.code
                switch(code) {
                    case 200: 
                        this.$notify({
                            title: '上传成功',
                            message: '',
                            type: 'success'
                        });
                        this.resetPhotos()
                        // this.photoList = res.data.data.photoIdList
                        this.$emit('func')
                        this.$router.push('set')
                        break;
                    case 201: 
                    case 401: 
                    case 403: 
                    case 404:  
                        this.$notify.error({
                            title: '失败',
                            message: '请重试'
                        });
                        // this.newAlbumInfo = res.data
                        break;
                    case 500:
                        this.$notify.error({
                            title: '请先登录',
                            message: '500'
                        });
                        break;
                }
            }).catch((err) => {
                console.log(err)
            })
            
        },
        resetPhotos() {
            this.$refs.uploadPhotos.clearFiles();
        },
        handleSuccess(response, file, fileList) {
            alert("handleSuccess")
        },
        handleRemove(file, fileList) {
            alert("handleRemove")
            console.log(file)
        },
        handlePreview(file) {
            alert("handlePreview:"+file)
        },
        handleError(err, file, fileList) {
            alert("handleError:"+err)
        },
        beforeUpload(file) {
            alert("beforeUpload")
            console.log(file)
        },
        // uploadHeader() {
        //     return {'Content-Type': 'multipart/form-data'}
        // }
        uploadUrl() {
            return 'http://192.168.31.49/photo/upload/' + this.parentSetId
        } 
  },
  props: ['parentSetId']
}


/*
submitPhotos() {
            this.$refs.uploadPhotos.submit()


            this.$axios.post('http://192.168.31.49/photo/upload/'+this.parentSetId, this.formData)
            .then((res) => {
                console.log(res)
                var code = res.data.code
                switch(code) {
                    case 200: 
                        this.$notify({
                            title: 'upload成功',
                            message: '',
                            type: 'success'
                        });
                        break;
                    case 201: 
                    case 401: 
                    case 403: 
                    case 404:  
                        this.$notify.error({
                            title: '失败',
                            message: '请重试'
                        });
                        // this.newAlbumInfo = res.data
                        break;
                    case 500:
                        this.$notify.error({
                            title: '请上传图片',
                            message: '500'
                        });
                        break;
                }
            }).catch((err) => {
                console.log(err)
            })

        },
        postPhotos(item) {
            // this.formData = new FormData();
            // this.formData.append('name', item.file.name)
            // this.formData.append('file', item.file)
            let formData = new FormData();
            formData.append('name', item.file.name)
            formData.append('file', item.file)
            console.log('上传图片接口-参数', item.file)
            console.log('上传图片名称', item.file.name)
            console.log('FORMDATA:' + formData)

            this.$axios.post('http://192.168.31.49/photo/upload/'+this.parentSetId, formData)
            .then((res) => {
                console.log(res)
                var code = res.data.code
                switch(code) {
                    case 200: 
                        this.$notify({
                            title: 'upload成功',
                            message: '',
                            type: 'success'
                        });
                        break;
                    case 201: 
                    case 401: 
                    case 403: 
                    case 404:  
                        this.$notify.error({
                            title: '失败',
                            message: '请重试'
                        });
                        // this.newAlbumInfo = res.data
                        break;
                    case 500:
                        this.$notify.error({
                            title: '请上传图片',
                            message: '500'
                        });
                        break;
                }
            }).catch((err) => {
                console.log(err)
            })
            
        },
        resetPhotos() {
            this.$refs.uploadPhotos.clearFiles();
        },
        handleSuccess(response, file, fileList) {
            alert("handleSuccess")
        },
        handleRemove(file, fileList) {
            alert("handleRemove")
            console.log(file)
        },
        handlePreview(file) {
            alert("handlePreview:"+file)
        },
        handleError(err, file, fileList) {
            alert("handleError:"+err)
        },
        beforeUpload(file) {
            alert("beforeUpload")
            console.log(file)
        },
        // uploadHeader() {
        //     return {'Content-Type': 'multipart/form-data'}
        // }
        uploadUrl() {
            return 'http://192.168.31.49/photo/upload/' + this.parentSetId
        } 
*/




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
