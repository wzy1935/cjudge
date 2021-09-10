<template>
  <div class=" top bg-blue-50">
    <the-result-title class=" title" :amount="Number($route.params.amount)" :results="results" :rightNum="rightNum"></the-result-title>
    <div class=" medium text-center rounded-lg">
      <div class="left bg-white">
        <div v-for="r in results" :key="r.index">
          <single-result v-bind="r" @result-check="checkResult"></single-result>
        </div>
      </div>
      <div class="right bg-white">
        <detailed-result v-bind="detail"></detailed-result>
      </div>
    </div>
  </div>
</template>
 
<script>
import SingleResult from './SingleResult.vue'
import DetailedResult from './DetailedResult.vue'
import TheResultTitle from './TheResultTitle.vue'

export default {
  components: {
    SingleResult,
    DetailedResult,
    TheResultTitle
  },
  props: {
    results : [],
    codes : {},
    rightNum : Number
  },
  data: function() {
    return {
      detail:{}
    }
  },
  methods: {
    add(oneResult) {
      this.now++;
      this.results.push(oneResult);
    },
    checkResult(n) {
      this.detail = this.results[n-1];
    }
  }
}
</script>

<style scoped>
.left {
  height: 450px;
  width: 450px;
  overflow-y: scroll;
  display: inline-block;
}

.right {
  height: 450px;
  width: 450px;
  overflow-y: scroll;
  display: inline-block;
}

.medium {
  height: 500px;
  width: 1000px;
}

.title {
  height: 100px;
  width: 1000px;
}

.top {
  height: 600px;
  width: 1000px;
}
</style>