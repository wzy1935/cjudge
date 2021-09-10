<template>
  <div>
    <div class=" flex justify-between pt-10 px-16 content-bottom">
      <div class=" text-2xl font-bold">
        测试结果
        <div @click="focus = 0" class="inline font-normal py-2 px-3 mx-2 rounded-md shadow-md text-white" :class="rightNum == finishNum ? 'bg-green-400' : 'bg-red-500'">{{rightNum}} / {{amount}}</div>
      </div>
      <div>
        <button @click="$router.push('/local')" class=" py-2 px-3 mx-2  rounded-md shadow-md text-white" :class="amount == finishNum ? 'bg-lime-500' : 'bg-gray-300'" :disabled="amount != finishNum">返回&lt;&lt;</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    results: [],
    amount: Number,
    rightNum: Number
  },
  computed: {
    finishNum() {
      return this.results.length;
    }
  },
  data: function() {
    return {

    }
  },
}
</script>
