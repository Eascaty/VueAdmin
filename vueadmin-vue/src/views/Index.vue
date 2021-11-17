<template>
    <div>main</div>

</template>

<script>


    export default {
        name: "Index",


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