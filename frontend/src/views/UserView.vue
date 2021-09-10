<template>
  <div>
    <user-info @logout="$emit('logout')"></user-info>
  </div>
</template>

<script>
import UserInfo from '../components/login/UserInfo.vue'
export default {
  components: {
    UserInfo
  }
}
</script>
