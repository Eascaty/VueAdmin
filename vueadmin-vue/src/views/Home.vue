<template>
  <el-container>
    <el-aside width="200px">


      <SideMenu>

      </SideMenu>
    </el-aside>
    <el-container>
      <el-header>
        <strong>VueAdmin后台管理系统</strong>

        <div class="header-avatar">

          <el-avatar size="medium"
                     src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>

          <el-dropdown>
  <span class="el-dropdown-link">
    Admin<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>

            </el-dropdown-menu>
          </el-dropdown>

          <el-link href="https://space.bilibili.com/489000814/" target="_blank">网站</el-link>
          <el-link href="" target="_blank">b站</el-link>
        </div>


      </el-header>
      <el-main>
        <router-view></router-view>

      </el-main>
    </el-container>
  </el-container>


</template>

<script>

import SideMenu from "./inc/SideMenu";
export default {
  name: "Index",
  components:{
    SideMenu
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }

}
</script>

<style scoped>


.el-container {
  padding: 0;
  margin: 0;
  height: 100vh;
}

.header-avatar {
  float: right;
  width: 210px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.el-header {
  background-color: #17B3A3;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  /*text-align: center;*/
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  line-height: 160px;
}

.el-dropdown-link {
  cursor: pointer;
  /*color: #409EFF;*/
}

.el-icon-arrow-down {
  font-size: 12px;
}


</style>