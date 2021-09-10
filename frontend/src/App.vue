<template>
  <div id="app" class="">
    <the-header ref="header" class=""></the-header>
    <keep-alive>
      <router-view class="" @logout="logout" @login="login"></router-view>
    </keep-alive>
  </div>
</template>

<script>
import TheHeader from './components/utils/TheHeader.vue'

export default {
  components: {
    TheHeader
  },
  methods: {
    logout() {
      this.$refs.header.getName();
    },
    login() {
      this.$refs.header.getName();
    }
  }
}
</script>