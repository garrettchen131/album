<template>
    <div>

        <div style="padding: 10px 50px 100px 50px;" v-if="!canCheckDelete">
            <div class="demo-image__preview" v-for="photo in photoList" :key="photo" style="color:#fff;">
                <div style="float:left;padding:10px;margin-top:20px;" >
                    <el-image
                        class="photo"
                        :src="photo"
                        :preview-src-list="photoList" fit="contain">
                    </el-image>
                </div>
            </div>
        </div>


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
            <el-checkbox v-for="phpto in photoList" :label="phpto" :key="phpto" >
                <el-image
                    class="photo"
                    :src="phpto"
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
                albumName: '《'+this.$route.params.title+'》',
                coverImage: this.$route.params.coverImage,
                photoList: this.$route.params.photos,
                checkAll: false,
                checkedPhotos: [],
                isIndeterminate: true,
                canCheckDelete: false,
                deletePhotos_dialogVisible: false
            }
        },
        methods:{
            goBack() {
                this.$router.go(-1)
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
            }
        },
        components: {
            addPhotos
        }
    }
</script>

<style lang='css' scoped>
.pageHeader {
    height:200px;
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
    width: 150px;
    height: 150px;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 5px;
}
.checkAllBox {
    position: absolute;
    bottom: 20px;
    left: 20px;
}
.checkboxGroup {
    padding: 20px 50px 100px 50px;
    margin-top:20px;
}
</style>