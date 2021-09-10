<template>
  <div>
    <div class="">
      <base-code v-model="code"></base-code>
      <p class=" text-sm p-2">* 在此处填写需要测试的程序。</p>
    </div>
  </div>
</template>

<script>
import BaseCode from '../utils/BaseCode.vue'

export default {
  components: {
    BaseCode
  },
  computed: {
    code: {
      get() {
        return this.$store.state.problem.test;
      },
      set(value) {
        this.$store.commit('setProblemTest', value);
      }
    }
  }
}
</script>
