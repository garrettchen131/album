<template>
  <div id="app">
      <router-view style="height:100%;"/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
html,body {
  margin: 0;
  padding: 0;
  overflow-y: scroll;
  overflow-x: hidden;
  /* overflow-y: invisible; */
  /* height: 100%; */
  width: 100%;
  /* background-color: ; */
  /* background-image: url(~@/assets/Bg13.jpg); */
  /* background-repeat: no-repeat; */
  background-size: 100%;
  /* overflow: -moz-scrollbars-none; */
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

/* body::-webkit-scrollbar {
    display: none;
} */

::-webkit-scrollbar {
    width: 6px;
    background-color: rgb(255, 211, 218);
}

::-webkit-scrollbar-thumb {
    background-color: #f78ea0;
}

/* Self.vue */
.el-message-box {
  width: 700px;
}


/* Set.vue */
.el-card__body {
    padding: 0;
}
.el-timeline-item__timestamp {
  font-size: 22px;
  padding: 10px;
  color: black;
}
.el-timeline-item__wrapper {
  margin-top: 70px;
}
.el-drawer__body {
  padding: 50px 50px 75px 50px;
  line-height: 35px;
  font-size: 20px;
  overflow-y: scroll;
  background-color: rgb(226, 244, 250);
}
.el-drawer__body {
  overflow: hidden;
}
.el-textarea__inner {
  height: 400px;
}

/* detail.vue */
/* .el-carousel__arrow {
  height: 1000px;
} */
.el-image-viewer__wrapper {
  /* top: 60px; */
  left: 350px;
  overflow: hidden;
}
.el-image-viewer__mask {
  background: #ccc;
  /* border-left: 1.2px solid rgb(141, 140, 140); */
}
.el-image-viewer__close {
  display: inline-block;
}
.el-image-viewer__img {
  transform: scale(2) rotate(0deg);
}
</style>
