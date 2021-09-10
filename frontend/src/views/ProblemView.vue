<template>
  <div>
    <problem-intro :problem="problem"></problem-intro>
    <problem-comments></problem-comments>
  </div>
</template>

<script>
import ProblemIntro from '../components/problem/ProblemIntro.vue'
import ProblemComments from '../components/problem/ProblemComments.vue'

export default {
  components: {
    ProblemIntro,
    ProblemComments
  },
  data: function() {
    return {
      problem : {
        id: -1,
        title: 'null',
        promoter: {
          id: -1,
          name: 'null'
        },
        description: 'null',
        likes: 0,
        dislikes: 0,
        testCases : [
          {input: String, output: String},
          {input: String, output: String},
          {input: String, output: String}
        ],
        defaultGenerator: 'null',
        comments: [],
        answers: []
      }
    }
  },
  activated() {
    this.axios.get('./api/problem/get', {params: {id: this.$route.params.problemId}}).then(resp => {
      this.problem = resp.data;
    });
  }
}
</script>