<template>
  <div class=" p-3 bg-blue-600">
    <div class=" flex justify-between">
      <div>
        <ul class=" p-1 text-white">
          <li class=" font-bold text-lg inline font-sans"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/">CJudge</router-link></li>
          <li class=" text-lg hidden sm:inline font-sans"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/repository">仓库</router-link></li>
          <li class=" text-lg hidden sm:inline font-sans"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/local">试用</router-link></li>
        </ul>
      </div>
      <div>
        <ul class=" p-1 text-white">
          <li class=" text-lg font-sans hidden sm:inline"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/user">{{name == '' ? '用户' : welcome + name}}</router-link></li>
          <li class=" text-lg font-sans inline sm:hidden py-2 px-5 hover:bg-blue-500" @click="expand = !expand">{{expand ? 'x' : '='}}</li>
        </ul>
      </div>
    </div>
    <div v-show="expand" class="block sm:hidden">
      <ul class=" p-1 text-white">
        <li class=" text-lg font-sans py-2"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/repository">仓库</router-link></li>
        <li class=" text-lg font-sans py-2"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/local">试用</router-link></li>
        <li class=" text-lg font-sans py-2"><router-link class=" py-2 px-5 hover:bg-blue-500" to="/user">{{name == '' ? '用户' : welcome + name}}</router-link></li>
      </ul>
    </div>

  </div>
</template>

<script>
export default {
  data: function() {
    return {
      name : '',
      welcome : '',
      expand : false
    }
  },
  methods: {
    getName() {
      this.welcome = this.getWelcomeWord();
      this.axios.get('./api/getUser/false').then(response => {
        let _name = response.data.name;
        if (_name != '') {
          this.name = _name;
        } else {
          this.name = '';
        }
      })
    },
    getWelcomeWord() {
      let date = new Date();
      let hour = date.getHours();
      if (hour >= 18) return '晚上好！';
      if (hour >= 14) return '下午好！';
      if (hour >= 11) return '中午好！';
      if (hour >= 9) return '上午好！';
      if (hour >= 5) return '早上好！';
      return '晚上好！';
    }
  },
  mounted() {
    this.getName();
  }
}
</script>
