<template>
  <div>
    <div class=" mx-auto max-w-3xl mt-10 p-4">
      <div class="flex justify-between items-center flex-col sm:flex-row">
        <p class=" font-bold text-2xl p-2">Q{{$route.params.problemId}} - 参数设置</p>
        <div class=" flex space-x-2">
          <button class="px-4 py-1 border-2 border-blue-600 text-blue-600" @click="$router.push('/problem/' + $route.params.problemId)">返回</button>
          <button class="px-4 py-1 border-2 border-blue-600 text-blue-600" @click="testNum = testNum==10?50:10">测试数量：{{testNum}}x</button>
          <button class="px-4 py-1 border-2 border-blue-600 text-blue-600" @click="$router.push('./result/' + testNum + '/' + getTime())">测试</button>
        </div>
      </div>
    </div>
    <div class="bg-gray-50 mx-auto max-w-3xl">
      <div class=" flex bg-white">
        <button class=" px-6 py-2" :class=" now == 1 ? ' bg-blue-600 text-white hover:bg-blue-500' : ' bg-gray-50  hover:bg-gray-200' " @click="jumpTo(1)">1 选择答案</button>
        <button class=" px-6 py-2" :class=" now == 2 ? ' bg-blue-600 text-white hover:bg-blue-500' : ' bg-gray-50  hover:bg-gray-200' " @click="jumpTo(2)">2 设置样例生成器</button>
        <button class=" px-6 py-2" :class=" now == 3 ? ' bg-blue-600 text-white hover:bg-blue-500' : ' bg-gray-50  hover:bg-gray-200' " @click="jumpTo(3)">3 填写待测代码</button>
      </div>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      now : 1,
      testNum: 10
    }
  },
  methods: {
    jumpTo(num) {
      if (num == this.now) return;
      let path;
      if (num == 1) path = 'answer';
      if (num == 2) path = 'case';
      if (num == 3) path = 'test';
      this.$router.push('./' + path);
      this.now = num;
    },
    getTime() {
      let date = new Date();
      return date.getTime();
    }
  },
  activated() {
    this.now = 1;
    if (this.$route.name != 'judgeAnswer') this.$router.push('./answer');
  }
}
</script>
