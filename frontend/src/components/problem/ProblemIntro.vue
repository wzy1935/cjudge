<template>
  <div>
    <div class=" justify-between bg-gray-50 mx-auto max-w-3xl my-10 py-10 px-4 sm:px-10">
      <h1 class=" block text-center font-bold text-xl sm:text-2xl mt-4">Q{{problem.id}} - {{problem.title}}</h1>
      <p class=" block text-center font-bold">{{problem.promoter.name}}</p>
      <p class=" font-bold my-2">描述：</p>
      <pre class=" whitespace-pre-wrap  m-2 bg-gray-100 p-2">{{problem.description}}</pre>


      <div v-for="(tc,index) in problem.testCases" :key="index" class=" my-4">
        <p class=" font-bold my-2">样例{{index + 1}}：</p>
        <div>
          <p>输入：</p>
          <pre class=" m-2 bg-gray-100 p-2">{{problem.testCases[index].input}}</pre>
          <p>输出：</p>
          <pre class=" m-2 bg-gray-100 p-2">{{problem.testCases[index].output}}</pre>
        </div>
      </div>

      <div class=" flex justify-center sm:justify-between mt-8 flex-col sm:flex-row space-y-2">
        <problem-vote></problem-vote>
        <p>共 {{answerAmount}} 条答案</p>
        <div class=" flex flex-row space-x-2">
          <button class=" px-4 py-1 text-blue-600 border-2 border-blue-600"
          @click="$router.push('/problem/' + problem.id + '/addAnswer')">添加答案</button>
          <button class=" px-4 py-1 text-blue-600 border-2 border-blue-600" 
          @click="$router.push('/judge/'+ problem.id +'/answer')">进入</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProblemVote from './ProblemVote.vue'

export default {
  components: {
    ProblemVote
  },
  data: function() {
    return {
      answerAmount: 0
    }
  },
  props: {
      problem : {
        id: Number,
        title: String,
        promoter: {
          id: Number,
          name: String
        },
        description: String,
        testCases : [
          {input: String, output: String},
          {input: String, output: String},
          {input: String, output: String}
        ],
      }
  },
  methods: {
    getAnswerAmount() {
      this.axios.get('./api/problem/getAnswerAmount', {params: {problemId: this.$route.params.problemId}}).then(resp => {
        this.answerAmount = resp.data.amount;
      });
    }
  },
  activated() {
    this.getAnswerAmount();
  }
}
</script>

