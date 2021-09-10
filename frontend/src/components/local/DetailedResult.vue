<template>
  <div class=" text-left " v-show="index != null">
    <p class=" text-lg font-bold">测试 #{{index}}</p>
    <p>测试结果：<span :class="classMap.get(state)" class=" rounded-sm px-2 py-1 font-bold">{{resultMap.get(state)}}</span></p>
    <p>测试用时：{{timeCost}}ms</p>
    <p>样例：</p>
    <pre class=" bg-gray-200 px-2 py-1 m-1 overflow-auto">{{testCase}}</pre>
    <div v-if="errorMessage == null">
      <p>标准输出：</p>
      <pre class="bg-gray-200 px-2 py-1 m-1 overflow-auto">{{outputStandard}}</pre>
      <p>测试输出：</p>
      <pre class="bg-gray-200 px-2 py-1 m-1 overflow-auto">{{outputTest}}</pre>
    </div>
    <div v-else>
      <p>报错信息：</p>
      <p class="bg-gray-200 px-2 py-1 m-1 overflow-auto">{{errorMessage}}</p>
    </div>
    
  </div>
</template>

<script>
export default {
  props: {
    index: Number,
    state: String,
    timeCost: Number,
    testCase: String,
    outputStandard: String,
    outputTest: String,
    errorMessage: String
  },
  data: function() {
    return {
      resultMap: new Map([
        ['AC','Accepted'],
        ['WA','Wrong Answer'],
        ['TLE','Time Limit Exceeded'],
        ['RE','Runtime Error'],
        ['WI','Waiting'],
        ['CE','Compile Error'],
        ['SE','Standard Error']
      ]),
      classMap: new Map([
        ['AC','bg-green-200'],
        ['WA','bg-red-400'],
        ['TLE','bg-yellow-400'],
        ['RE','bg-yellow-400'],
        ['WI','bg-gray-200'],
        ['CE','bg-purple-300'],
        ['SE','bg-blue-300']
      ])
    }
  }
}
</script>

<style scoped>
.cell {
  background-color: gainsboro;
  border-radius: 5px;
  margin: 2px;
  padding: 2px;
  overflow-y: auto;
}
</style>