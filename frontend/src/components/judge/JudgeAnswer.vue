<template>
  <div>
    <div class=" pt-4 px-4 flex justify-between items-center flex-col sm:flex-row">
      <p>已选中：<span class=" text-blue-600">#{{chosen}}</span></p>
      <div class="flex items-center">
        <p v-show="chosen != 0">评价本答案：</p>
        <answer-vote :id="chosen" ref="vote" @vote="updateVote" v-show="chosen != 0"></answer-vote>
      </div>
    </div>
    <div class=" flex flex-col sm:flex-row justify-between p-2">
      <div class=" sm:w-1/2 p-2">
        <p>已有答案</p>
        <div class=" max-h-96 sm:h-96 overflow-y-scroll">
          <div v-for="(ar,i) in answers.rows" :key="i">
            <div class=" px-4 py-1 bg-white flex flex-row justify-around cursor-pointer" 
            :class="chosen==ar.id ? ' bg-blue-600 text-white' : (i%2==0 ? ' bg-gray-200' : ' bg-gray-100')"
            @click="chooseId(ar.id,i)">
              <p class=" w-full">#{{ar.id}}</p>
              <div class=" w-full">
                <span>▲ {{ar.likes}} </span>
                <span>▼ {{ar.dislikes}} </span>
              </div>
              <p class=" w-full">{{ar.user.name}}</p>
            </div>
          </div>
        </div>
      </div>
      <div class=" sm:w-1/2 p-2">
        <p>测试</p>
        <div class=" flex flex-col space-y-2">
          <div class="flex justify-between items-center">
            <p>测试输入：</p>
            <button class=" mr-4 px-4 py-1 border-2 border-blue-600 text-blue-600" @click="testAnswer()">测试</button>
          </div>
          <textarea class=" w-full h-36 overflow-y-scroll overflow-x-auto p-2" v-model="input"></textarea>
          <p>测试输出：</p>
          <pre class=" bg-white h-36 overflow-y-scroll overflow-x-auto p-2">{{output}}</pre>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AnswerVote from './AnswerVote.vue'

export default {
  components: {
    AnswerVote
  },
  data: function() {
    return {
      answers : {
        page: 1,
        size: 1000,
        total: 0,
        rows: []
      },
      chosen: 0,
      input: '',
      output: ''
    }
  },
  methods: {
    getAnswer() {
      this.axios.get('./api/problem/getAnswer', {params: {
        page: this.answers.page,
        size: this.answers.size,
        problemId: this.$route.params.problemId
        }}).then(resp => {
          this.answers = resp.data;
        });
    },
    testAnswer() {
      this.axios.post('./api/problem/testAnswer', {answerId: this.chosen, input: this.input}).then(resp => {
        this.output = resp.data.output;
      })
    },
    chooseId(num,i) {
      this.chosen = num;
      this.$store.commit('setProblemStandard', num);
      this.$refs.vote.act(num,i);
    },
    updateVote(i, result) {
      this.answers.rows[i].likes = result.likes;
      this.answers.rows[i].dislikes = result.dislikes;
    }
  },
  activated() {
    this.getAnswer();
  }
}
</script>
