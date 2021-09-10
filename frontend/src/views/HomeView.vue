<template>
  <div>
    <div class="pl-8 md:pl-48 py-56 sm:py-72 bg-center " :style="{backgroundImage:'url('+require('../assets/topography.svg')+')'}">
      <div class=" font-mono text-5xl sm:text-6xl font-bold">
        <div class="md:inline">C<span class=" text-blue-500">ommunity</span></div> 
        <div class="md:inline show-anim">Judge</div>
      </div>
      <div>
        <button class="block sm:inline font-mono text-xl hover:bg-blue-600 bg-blue-500 px-5 py-2 text-white my-5 mx-3">Local Test</button>
        <button class="block sm:inline font-mono text-xl hover:bg-blue-600 bg-blue-500 px-5 py-2 text-white my-5 mx-3">PC version</button>
      </div>
    </div>
    <div>
      <the-help></the-help>
    </div>
  </div>
</template>

<script>
import TheHelp from '../components/home/TheHelp.vue'
export default {
  components: {
    TheHelp
  }
}
</script>
