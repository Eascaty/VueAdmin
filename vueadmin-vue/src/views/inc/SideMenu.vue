<template>
    <el-container>
        <el-aside width="200px">
            <el-menu
                    :default-active="this.$store.state.menus.editableTabsValue"
                    class="el-menu-vertical-demo"

                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">

                <router-link to="/index">
                    <el-menu-item index="Index" @click="selectMenu({name:'Index',title: '首页'})">
                        <template slot="title"><i class="el-icon-s-home"></i>
                            <span slot="title">首页</span>
                        </template>
                    </el-menu-item>
                </router-link>

                <el-submenu :index="menu.name" v-for="menu in menuList">
                    <template slot="title">
                        <i :class="menu.icon"></i>
                        <span>{{menu.title}}</span></template>

                    <router-link :to="item.path" v-for="item in menu.children">
                        <el-menu-item :index="item.name" @click="selectMenu(item)">
                            <template slot="title">
                                <i :class="item.icon"></i>
                                <span slot="title">{{item.title}}</span></template>
                        </el-menu-item>
                    </router-link>


                </el-submenu>


            </el-menu>

        </el-aside>

    </el-container>


</template>

<script>

    export default {
        name: "SideMenu",
        data() {
            return {}
        },
        //动态监测
        computed: {
            menuList: {
                get() {

                    return this.$store.state.menus.menuList
                }
            }
        },
        methods: {
            selectMenu(item) {
                this.$store.commit("addTab", item);
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