<template>
  <div class=" bg-gray-50 lg:mx-44 my-10 p-12">
    <h1>使用说明</h1>
    <hr/>
    <h2>简介</h2>
    <p>CJudge是一个在线代码评测平台。不同的是，评测所用的样例并不使用设定好的样例，而是由生成器动态生成的。其前身是LocalJudge版本，与本站的本地测试功能一致。</p>
    <p> <a href="/local">在线试用</a></p>
    <p>github上的<a href="https://github.com/wzy1935/local-judge">Local Judge</a></p>
    <hr/>
    <h2>试用</h2>
    <p>提交三个测试文件即可启用本地测试。建议使用电脑评测。</p>
    <p><b>参考代码</b>：作为正确答案的代码。</p>
    <p><b>待测代码</b>：需要测试的代码。将使用其与参考代码运行结果比对。</p>
    <p><b>样例生成器</b>：能够自动生成样例的程序。使用System.out即可。</p>
    <p><i>* 上述类名必须为Main</i></p>
    <hr/>
    <h2>测试结果</h2>
    <p class=" text-green-400">AC - Accepted: 测试通过</p>
    <p class=" text-red-600">WA - Wrong Answer：答案错误</p>
    <p class=" text-yellow-600">TLE - Time Limit Exceeded：时间超限</p>
    <p class=" text-yellow-600">RE - Runtime Error：运行错误</p>
    <p class=" text-gray-400">WI - Waiting：等待测试</p>
    <p class=" text-purple-500">CE - Compile Error：编译错误</p>
    <p class=" text-blue-500">SE - Standard Error：样例生成错误或参考代码错误</p>
  </div>
</template>

<style scoped>
h1 {
  font-size: 3rem;
  padding: 1rem 0;
}
h2 {
  font-size: 2rem;
  padding: 0.5rem 0;
}
p {
  padding: 0.5rem 0;
  font-size: 1.125rem;
}
hr {
  margin: 1rem 0;
}
a, a:hover, a:active, a:visited, a:link {
  color: #38bdf8;
  text-decoration: underline;
}
</style>

<script>
export default {
  
}
</script>
