const Mock = require('mockjs')

const Random =  Mock.Random

let Result = {
    code: 200,
    msg: '操作成功',
    data: null
}

Mock.mock('/captcha','get',() => {

    Result.data = {
        token: Random.string(32),
        captchaImg: Random.dataImage('120x40','p7n5w')
    }
    return Result
})


// Mock.mock('/login','post',(config) => {
//
//     //无法在header中传入数jwt
//     return Result
// })
Mock.mock(RegExp('/login'), 'post', (config) => {
    // 这里无法在header添加authorization，直接跳过
    console.log("mock----------------login")
    return Result
})