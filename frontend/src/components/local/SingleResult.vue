<template>
<div>
  <div class=" flex justify-around py-1 px-4 " 
  :class=" index%2==0 ? ' bg-gray-300' : ' bg-gray-200'">
    <div class=" w-full">{{index}}</div>
    <div class=" w-full">
      <div class=" rounded-sm w-10 font-bold text-center cursor-pointer" :class=" classMap.get(state)" @click="$emit('result-check',index)">{{state}}</div>
    </div>
    <div class=" w-full">{{timeCost}}ms</div>
  </div>
</div>

</template>
 
<script>
export default {
  props: {
    index: Number,
    state: String,
    timeCost: Number,
    dark: Boolean
  },
  data: function() {
    return {
      classMap: new Map([
        ['AC','bg-green-200'],
        ['WA','bg-red-400'],
        ['TLE','bg-yellow-400'],
        ['RE','bg-yellow-400'],
        ['WI','bg-gray-200'],
        ['CE','bg-purple-300'],
        ['SE','bg-blue-300']
      ])
    }
  }
}
</script>

<style scoped>
</style>

