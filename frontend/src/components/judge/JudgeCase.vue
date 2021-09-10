<template>
  <div>
    <div class="">
      <base-code v-model="code" ref="code"></base-code>
      <p class=" text-sm p-2">* 在此处填写能够生成样例需要的程序。</p>
      <div class="p-4">
        <button class="py-1 px-4 bg-blue-600 text-white hover:bg-blue-500" @click="getGenerator()">获取默认</button>
      </div>
    </div>
  </div>
</template>

<script>
import BaseCode from '../utils/BaseCode.vue'

export default {
  components: {
    BaseCode
  },
  data: function() {
    return {
      
    }
  },
  computed: {
    code: {
      get() {
        return this.$store.state.problem.generator;
      },
      set(value) {
        this.$store.commit('setProblemGenerator', value);
      }
    }
  },
  methods: {
    getGenerator() {
      this.axios.get('./api/problem/getGenerator', {params: {problemId: Number(this.$route.params.problemId)}}).then(resp => {
        this.code = resp.data.generator;
        this.$refs.code.code = this.code;
      })
    }
  }
}
</script>
