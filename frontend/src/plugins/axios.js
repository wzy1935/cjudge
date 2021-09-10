import axios from 'axios'
import router from '../router/index.js'

const instance = axios.create({
    headers: {
        'content-type': 'application/json;charset=UTF-8',
        // 'token': 'one' 
    },
    baseURL: 'http://',
    timeout: 10000,
    withCredentials: true
})

// 添加请求拦截器
instance.interceptors.request.use(config => {
    // 在发送请求之前做某事，比如说 设置token
    //config.headers['token'] = 'token';
    return config;
}, error => {
    // 请求错误时做些事
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(response => {
    // 对响应数据做些事

    return response;
}, error => {
  console.log(error);
  if (error.response.status == 401) {
    router.push('/login');
  }
  return Promise.reject(error); // 返回接口返回的错误信息
})

export default instance;
