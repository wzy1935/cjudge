<template>
  <div>
    <div class="flex flex-row">
      <div class=" px-4 py-1 border-green-600 border-2 border-r-0 cursor-pointer" 
      :class="result.vote > 0 ? ' bg-green-600 text-white' : 'text-green-600'"
      @click="vote(result.vote != 1 ? 1 : 0)">
        ▲ {{result.likes}}
      </div>
      <div class=" px-4 py-1 border-red-600 border-2 border-l-0 cursor-pointer" 
      :class="result.vote < 0 ? ' bg-red-600 text-white' : 'text-red-600'"
      @click="vote(result.vote != -1 ? -1 : 0)">
        ▼ {{result.dislikes}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      result : {
        vote: 0,
        likes: 0,
        dislikes: 0
      }
    }
  },
  methods: {
    vote(num) {
      this.axios.post('./api/problem/vote',{problemId: Number(this.$route.params.problemId), vote: num}).then(resp => {
      this.result = resp.data;
    })
    }
  },
  activated() {
    this.axios.get('./api/problem/vote/get',{params:{problemId: Number(this.$route.params.problemId)}}).then(resp => {
      this.result = resp.data;
    })
  }
}
</script>
