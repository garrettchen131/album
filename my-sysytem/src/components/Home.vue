<template>
    <div class="container">

        <div class="link" @click="toLR">
            <span style="text-decoration: none;font-size:20px;">{{ swith?'去注册':'去登录' }}</span>
            <i class="el-icon-s-promotion" style="font-size:70px;"></i>
        </div>
        
        <transition mode="out-in">
            <router-view class="view"></router-view>
        </transition>

    </div>
</template>

<script>
    export default {
        data(){
            return {
               swith: true //   true to register, false to login
            }
        },
        methods:{
            toLR() {
                if(this.swith) {
                    //to register
                    this.$router.push('/home/register')
                    this.swith = false
                } else {
                    //to login
                    this.$router.push('/home/login')
                    this.swith = true
                }
            }
        }
    }
</script>

<style lang='css' scoped>
.v-enter,
.v-leave-to {
    opacity: 0;
    transform: translateY(100px);
    /* transform: translateX(-200px); */
}
.v-enter-active,
.v-leave-active {
    transition: all 0.5s ease;
}
.link {
    position: absolute;
    left: 47%;
    top: 50px;
    margin-left: -67.5px;
    cursor: pointer;
    color: #fff;
    transition: all .3s linear;
    text-shadow: 20px 20px 10px rgba(0,0,0,0.2);
}
.link:hover {
    /* color: rgb(236, 226, 226); */
    top: 40px;
    text-shadow: 30px 30px 15px rgba(0,0,0,0.15);
    transition: all .3s linear;
}
.container {
    position: absolute;
    height: 100%;
    width: 100%;
    /* margin-left: 6px; */
    background-image: url(~@/assets/Bg17.png);
    background-repeat: no-repeat;
    /* background-size: 100% 100%; */
    /* border: 1px solid #000; */
    /* overflow: hidden; */
}
.view {
    position: absolute;
    /* left: 73%; */
    right: 10%;
    margin-left: -350px;
    top: 160px;
}
</style>