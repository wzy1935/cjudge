<template>
  <div>
    <div class="bg-gray-50 mx-auto max-w-3xl my-10 py-10 px-4 sm:px-10 space-y-4">
      <h1 class=" text-3xl font-bold">添加问题</h1>
      <hr/>

      <h2 class=" text-xl">标题：</h2>
      <input v-model="problemForm.title" class=" text-lg p-2 w-full rounded-none border-b-2 border-gray-400" type="text">

      <h2 class=" text-xl">描述：</h2>
      <textarea v-model="problemForm.description" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>

      <h2 class=" text-xl">样例1：</h2>
      <div class=" flex justify-between flex-col sm:flex-row sm:space-x-4">
        <div class=" w-full">
          input: 
          <textarea v-model="problemForm.testCases[0].input" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
        <div class=" w-full">
          output: 
          <textarea v-model="problemForm.testCases[0].output" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
      </div>

      <h2 class=" text-xl">样例2：</h2>
      <div class=" flex justify-between flex-col sm:flex-row sm:space-x-4">
        <div class=" w-full">
          input: 
          <textarea v-model="problemForm.testCases[1].input" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
        <div class=" w-full">
          output: 
          <textarea v-model="problemForm.testCases[1].output" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
      </div>

      <h2 class=" text-xl">样例3：</h2>
      <div class=" flex justify-between flex-col sm:flex-row sm:space-x-4">
        <div class=" w-full">
          input: 
          <textarea v-model="problemForm.testCases[2].input" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
        <div class=" w-full">
          output: 
          <textarea v-model="problemForm.testCases[2].output" class=" text-lg p-2 w-full rounded-none h-48 border-b-2 border-gray-400" type="text"></textarea>
        </div>
      </div>

      <h2 class=" text-xl">样例生成器：</h2>
      <base-code ref="tcg" class=" code h-96 border-b-2 border-gray-400"></base-code>

      <p class=" block mx-auto w-5/6 p-1 text-red-600 max-w-sm bg-red-200 rounded-md" v-show="errorMessage != ''">
        {{errorMessage}}
      </p>
      <button @click="addProblem" class=" block mx-auto w-5/6 p-2 text-white text-xl max-w-sm bg-blue-500">
        {{uploading ? '提交中' : '提交'}}
      </button>
    </div>
  </div>
</template>

<script>
import BaseCode from '../components/utils/BaseCode.vue';


export default {
  components: {
    BaseCode
  },
  data: function() {
    return {
      problemForm : {
        title : '',
        description : '',
        testCases : [
          {input: '', output: ''},
          {input: '', output: ''},
          {input: '', output: ''}
        ],
        defaultGenerator : ''
      },
      errorMessage : '',
      uploading : false
    }
  },
  methods: {
    addProblem() {
      this.problemForm.defaultGenerator = this.$refs.tcg.code;
      this.errorMessage = '';
      if (!this.formCheck()) {
        this.errorMessage = '请检查是否有值为空'
        return;
      }
      this.uploading = true;
      this.axios.post('./api/problem/add', this.problemForm).then(resp => {
        if (resp.data.result == false) {
          this.errorMessage = resp.data.message;
        } else {
          this.$router.push('/repository');
        }
        this.uploading = false;
      }).catch(()=>{
        this.uploading = false;
      });
    },
    formCheck() {
      let invalid = 
        this.problemForm.title == '' ||
        this.problemForm.description == '' ||
        this.problemForm.testCases[0].input == '' || this.problemForm.testCases[0].output == '' ||
        this.problemForm.testCases[1].input == '' || this.problemForm.testCases[1].output == '' ||
        this.problemForm.testCases[2].input == '' || this.problemForm.testCases[2].output == '';
      return !invalid;
    }
  }
}
</script>

<style scoped>
.code {
  /* overflow-y: auto;
  overflow-x:visible; */
}
</style>