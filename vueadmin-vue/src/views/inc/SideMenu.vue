<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"

          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

        <router-link to="/index">
          <el-menu-item index="Index">
            <template slot="title"><i class="el-icon-s-home"></i>
              <span slot="title">首页</span>
            </template>
          </el-menu-item>
        </router-link>

        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-s-operation"></i>
            <span>系统管理</span></template>

          <router-link to="/users">
          <el-menu-item index="1-1">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span slot="title">用户管理</span></template>
          </el-menu-item>
          </router-link>



          <router-link to="/roles">
          <el-menu-item index="1-2">
            <template slot="title"><i class="el-icon-rank"></i> <span slot="title">角色管理</span></template>
          </el-menu-item>
          </router-link>




            <router-link to="/menus">
          <el-menu-item index="1-3">
            <template slot="title"><i class="el-icon-menu"></i> <span slot="title">菜单管理</span></template>
          </el-menu-item>
            </router-link>


        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-s-tools"></i> <span>系统工具</span></template>
          <el-menu-item index="2-2">
            <template slot="title"><i class="el-icon-s-order"></i> <span slot="title">数字字典</span></template>
          </el-menu-item>
        </el-submenu>

      </el-menu>

    </el-aside>

  </el-container>


</template>

<script>

export default {
  name: "Index",
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