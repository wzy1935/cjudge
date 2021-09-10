<template>
  <div>
    <login-form @login="$emit('login')"></login-form>
  </div>
</template>

<script>
import LoginForm from '../components/login/LoginForm.vue'
export default {
  components: {
    LoginForm
  },
}
</script>

