<template>
  <div>
    <div class=" bg-gray-50 mx-auto max-w-3xl my-10 py-10 px-4 sm:px-10 space-y-4">
      <p class=" text-3xl font-bold mb-4">为 Q{{id}} 添加答案</p>
      <hr/>
      <base-code ref="code"></base-code>
      <p class=" block mx-auto w-5/6 p-1 text-red-600 bg-red-200 rounded-md" v-show="errorMessage != ''">
        {{errorMessage}}
      </p>
      <div class=" flex justify-between mt-6 space-x-4 w-5/6 mx-auto">
        <button @click="addAnswer" class=" block mx-auto p-2 w-full text-white text-xl max-w-sm bg-blue-600">
          {{uploading ? '提交中' : '提交'}}
        </button>
        <button @click="$router.go(-1)" class=" block mx-auto p-2 w-full text-white text-xl max-w-sm bg-blue-600">
          返回
        </button>
      </div>

    </div>
  </div>
</template>

<script>
import BaseCode from '../components/utils/BaseCode.vue'

export default {
  components: {
    BaseCode
  },
  data: function() {
    return {
      id: this.$route.params.problemId,
      errorMessage : '',
      uploading : false
    }
  },
  methods: {
    addAnswer() {
      let code = this.$refs.code.code;
      this.errorMessage = '';
      this.uploading = true;
      this.axios.post('./api/problem/addAnswer', {problemId: Number(this.id), content: code}).then(resp => {
        if (resp.data.result == false) {
          this.errorMessage = resp.data.message;
        } else {
          this.$router.push('./');
        }
        this.uploading = false;
      }).catch(()=>{
        this.uploading = false;
      });
    }
  },
  activated() {
    this.id = this.$route.params.problemId;
    this.errorMessage = '';
  }
}
</script>