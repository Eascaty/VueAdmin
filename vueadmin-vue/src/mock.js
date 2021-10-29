const Mock = require('mockjs')

const Random = Mock.Random

let Result = {
    code: 200,
    msg: '操作成功',
    data: null
}

Mock.mock('/captcha', 'get', () => {

    Result.data = {
        token: Random.string(32),
        captchaImg: Random.dataImage('120x40', 'p7n5w')
    }
    return Result
})


// Mock.mock('/login','post',(config) => {
//
//     //无法在header中传入数jwt
//     return Result
// })
Mock.mock(RegExp('/login'), 'post', () => {
    // 这里无法在header添加authorization，直接跳过
    console.log("mock----------------login")

    Result.code = 400
    Result.msg = "验证码错误"

    return Result
})

Mock.mock("/sys/userInfo", 'get', () => {

    Result.data = {
        id: "1",
        username: "test",
        avatar: "https://img2.baidu.com/it/u=3380323029,2286064569&fm=26&fmt=auto"


    }
    return Result
})

Mock.mock('/logout', 'post', () => {

    return Result
})


Mock.mock('/sys/menu/nav', 'get', () => {

    let nav = [

        {
            name: 'SysManga',
            title: '系统管理',
            icon: 'el-icon-s-operation',
            component: '',
            path: '',
            children: [
                {
                    name: 'SysUser',
                    title: '用户管理',
                    icon: 'el-icon-s-custom',
                    path: '/sys/users',
                    component: 'sys/User',
                    children: []
                }
            ]
        },
        {
            name: 'SysTools',
            title: '系统工具',
            icon: 'el-icon-s-tools',
            component: '',
            path: '',
            children: [
                {
                    name: 'SysDict',
                    title: '数字字典',
                    icon: 'el-icon-s-order',
                    component: '',
                    path: '/sys/dicts',
                    children: []
                },
            ]
        }

    ]
    let authoritys = []

    Result.data = {
        nav: nav,
        authoritys: authoritys,
    }

    return Result
})