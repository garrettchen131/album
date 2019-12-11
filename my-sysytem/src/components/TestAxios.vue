<template>
    <div>
        <el-button type="primary" @click='sendForm'>发送注册数据测试</el-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    },
    methods: {
        
        sendForm() {
            // var mydata = {
            //     mail: '1252005708@qq.com',
            //     name: 'cxlncjdsh',
            //     password: '123456',
            //     phone: '1777272186'
            // }
            // console.log(data)
            // this.$axios.post("http://139.9.205.50/user/register",data,{
            //     headers: {
            //         'Content-Type': 'application/json;charset=UTF-8'
            //     }
            // })
            // .then(res=>{
            //     console.log(res)
            // })
            // .catch(err=>{
            //     console.log(err)
            // })
            this.$axios({
                method: 'post',
                url: 'http://139.9.205.50/user/register',
                data: {
                    mail: '1252005708@qq.com',
                    name: 'cxlncjdsh',
                    password: '123456',
                    phone: '1777272186'
                },
                header: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then((res) => {
                console.log(res)
            }).catch((err) => {
                console.log(err)
            })
        }
    }
}
</script>


<style lang='css' scoped>

</style>
