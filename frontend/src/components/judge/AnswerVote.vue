<template>
  <div>
    <div class="flex flex-row">
      <div class=" px-4 py-1 border-blue-600 border-2 border-r-0 cursor-pointer" 
      :class="result.vote > 0 ? ' bg-blue-600 text-white' : 'text-blue-600'"
      @click="vote(result.vote != 1 ? 1 : 0)">
        ▲ {{result.likes}}
      </div>
      <div class=" px-4 py-1 border-blue-600 border-2 border-l-0 cursor-pointer" 
      :class="result.vote < 0 ? ' bg-blue-600 text-white' : 'text-blue-600'"
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
      },
      i: 0
    }
  },
  props: {
    id: Number
  },
  methods: {
    vote(num) {
      this.axios.post('./api/answer/vote',{answerId: this.id, vote: num}).then(resp => {
      this.result = resp.data;
      this.$emit("vote", this.i, this.result);
    })
    },
    act(num,i) {
      this.i = i;
      this.axios.get('./api/answer/vote/get',{params:{answerId: num}}).then(resp => {
        this.result = resp.data;
      })
    }
  },

}
</script>
