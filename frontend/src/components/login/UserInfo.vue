<template>
  <div class=" mx-auto max-w-md my-20 p-4 bg-gray-50">
    <div class=" py-4 px-10 flex flex-col space-y-4">
      <p class=" font-bold text-xl">用户信息：</p>
      <p>用户ID：{{auth.id}}</p>
      <p>用户名：{{auth.name}}</p>
      <div class=" text-center">
        <button @click="logout" class=" px-4 py-1 bg-blue-600 text-white">退出登录</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      auth : {
        id : -1,
        name : ''
      }
    }
  },
  methods: {
    logout() {
      this.axios.post('./api/logout').then(() => {
        this.$emit('logout');
        this.$router.push('/');
      });

    },
    getAuth() {
      this.axios.get('./api/getUser/true').then(resp => {
        let _auth = resp.data;
        this.auth = _auth;
      });
    }
  },
  activated() {
    this.getAuth();
  }
}
</script>
