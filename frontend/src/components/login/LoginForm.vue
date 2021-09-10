<template>
  <div>

    <div v-if="isLogin" class="flex space-y-3 flex-col p-10 shadow-lg m-auto w-5/6 max-w-md mt-20">
      <div class=" text-blue-600 font-bold text-2xl">登录</div>
      <hr/>
      <div>用户名：</div>
      <input class=" bg-gray-100 p-2 rounded-md" type="text" v-model="loginData.name"/>
      <div>密码：</div>
      <input class=" bg-gray-100 p-2 rounded-md" type="password" v-model="loginData.password">
      <div>
        <div class=" bg-red-100 text-red-500 p-1 my-2" :class="errorMessage == '' ? 'invisible' : ''">{{errorMessage}}</div>
        <div class=" text-white mx-0 text-center rounded-md bg-blue-600 py-2 cursor-pointer" @click="login">确定</div>
        <div class=" flex justify-between mt-4">
          <div></div>
          <div>没有账号？<span @click="change" class=" cursor-pointer text-blue-400">注册</span></div>
        </div>
      </div>
    </div>

    <div v-else class="flex space-y-3 flex-col p-10 shadow-lg m-auto w-5/6 max-w-md mt-20">
      <div class=" text-blue-600 font-bold text-2xl">注册</div>
      <hr/>
      <div>用户名：</div>
      <input class=" bg-gray-100 p-2 rounded-md" type="text" v-model="registerData.name"/>
      <div>密码：</div>
      <input class=" bg-gray-100 p-2 rounded-md" type="password" v-model="registerData.password">
      <div>确认密码：</div>
      <input class=" bg-gray-100 p-2 rounded-md" type="password" v-model="registerData.passwordConfirm">
      <div>
        <div class=" bg-red-100 text-red-500 p-1 my-2" :class="errorMessage == '' ? 'invisible' : ''">{{errorMessage}}</div>
        <div class=" text-white mx-0 text-center rounded-md bg-blue-600 py-2 cursor-pointer" @click="register">确定</div>
        <div class=" flex justify-between mt-4">
          <div></div>
          <div>已有账号？<span @click="change" class=" cursor-pointer text-blue-400">登录</span></div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data: function() {
    return {
      loginData : {
        id : 0,
        name : "",
        password : ""
      },
      registerData : {
        id : 0,
        name : "",
        password : "",
        passwordConfirm : ""
      },
      errorMessage : "",
      isLogin : true
    }
  },
  methods: {
    login() {
      this.errorMessage = '';
      this.axios.post(
        './api/login',this.loginData
      ).then(response => {
        let isValid = response.data.result;
        if (isValid) {
          this.$router.push('/');
          this.$emit('login');
        } else {
          this.showError(response.data.message);
        }
      })
    },
    register() {
      if (this.registerData.password != this.registerData.passwordConfirm) {
        this.errorMessage = '两次输入密码不一致';
        return;
      }
      this.errorMessage = '';
      this.axios.post(
        './api/register',this.registerData
      ).then(response => {
        let isValid = response.data.result;
        if (isValid) {
          this.change();
          alert('注册成功，请登录');
        } else {
          this.showError(response.data.message);
        }
      })
    },
    showError(message) {
      this.errorMessage = message;
    },
    change() {
      this.errorMessage = '';
      this.isLogin = !this.isLogin;
    }
  }

}
</script>
