<template>
  <div>
    <div class=" mx-auto max-w-3xl mt-10 p-4">
      <div class="flex justify-between items-center">
        <p class=" font-bold text-2xl">测试结果</p>
        <button class="px-4 py-1 border-2 border-blue-600 text-blue-600" @click="$router.go(-1)">返回</button>
      </div>
    </div>

    <div class="bg-gray-50 mx-auto max-w-3xl">
      <div class=" flex flex-col sm:flex-row justify-between p-2">
        <div class=" sm:w-1/2 m-1 overflow-y-scroll sm:h-96 max-h-96">
          <div v-for="r in results" :key="r.index">
            <single-result v-bind="r" @result-check="checkResult"></single-result>
          </div> 
        </div>
        <div class=" sm:w-1/2 p-2 m-1 overflow-y-scroll sm:h-96 max-h-96">
          <detailed-result v-bind="detail"></detailed-result>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SingleResult from '../components/local/SingleResult.vue'
import DetailedResult from '../components/local/DetailedResult.vue'

export default {
  components: {
    SingleResult,
    DetailedResult
  },
  data: function() {
    return {
      results : [],
      rightNum : 0,
      detail: {}   
    }

  },
  activated() {
    this.send();
  },
  methods: {
    async send() {

      const _this=this;
      _this.results = [];
      _this.rightNum = 0;
      let size = _this.$route.params.amount;
      let time = _this.$route.params.time;
      _this.detail = {};

      let stopCode = false;

      for (let i = 1; i <= size; i++) {
        if (stopCode) break;
        await _this.axios.post('./api/local/judge',{
          codeStandard: _this.$store.state.local.standard,
          codeTest: _this.$store.state.local.test,
          generator: _this.$store.state.local.generator,
          index: i
        }).then(response => {
          if (_this.$route.params.time != time) stopCode = true;
          let r = response.data;
          if (r.state == 'AC') _this.rightNum++;
          _this.results.push(r);
        }).catch(error => {
          console.log(error);
          stopCode = true;
        })
        
      }
        
    },
    checkResult(n) {
      this.detail = this.results[n-1];
    }
  }
}
</script>