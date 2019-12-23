<template>
    <div>
        
        <!-- <el-form :model="addPhotosForm" ref="addPhotosForm" label-width="20px" class="demo-addPhotosForm">
            <el-form-item>
                <el-upload
                    class="upload-demo"
                    ref="uploadPhotos"
                    accept="image/png,image/gif,image/jpg,image/jpeg"
                    drag
                    multiple
                    action="j"
                    :http-request="postPhotos"
                    :auto-upload="false"
                    :before-upload="beforeUpload"
                    :on-success="handleSuccess"
                    :on-remove="handleRemove"
                    :on-preview="handlePreview"
                    :on-error="handleError"
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
        </el-form> -->


        <form action="#" enctype="multipart/form-data" method="post">
            <table>
                <tr>
                    <td>上传图片</td>
                    <td>
                        <input class="fl" type="file" id="imgPath" name="file" multiple="multiple" @change="getFiles()"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="submit" class="uploadPhotos">
                    </td>
                </tr>
            </table>
        </form>


    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    },
    methods: {
        getFiles(data) {
            console.log(data)
        }
    }
}




/*
// addPhotosForm: {

            // },
            // fileList: [],
            // formData: []

submitPhotos() {
            this.$refs.uploadPhotos.submit()
            
            this.$axios({
                method: 'post',
                url: 'https://jsonplaceholder.typicode.com/posts/',
                data: {
                    // formData
                    fileList: this.fileList
                },
                header: {
                    'Content-Type': 'application/json; charset=utf-8'
                    
                }
            }).then((res) => {
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
            // let formData = new FormData();
            // formData.append('name', item.file)
            // formData.append('file', item.file)
            alert("进入http-require")
            this.fileList.push(item.file)
            console.log('上传图片接口-参数', item.file)
            console.log('上传图片名称', item.file.name)
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
        }

*/



</script>


<style lang='css' scoped>
.demo-addPhotosForm {
    border: 1px solid #000;
}

.uploadPhotos {
    width: 100px;
    height: 40px;
    font-size: 20px;
    font-family: 'Courier New', Courier, monospace;
    background-color: none;
    border-radius: 10px;
    cursor: pointer;
}
</style>



