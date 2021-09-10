<template>
  <div>
    <div class=" flex flex-col my-4 space-y-4 bg-gray-50 w-5/6 max-w-3xl mx-auto p-3 sm:p-5 border-b-4">
      <h1 class=" font-bold text-lg sm:text-xl">Q{{problemShow.id}} - {{problemShow.title}}</h1>

      <div class=" flex justify-between space-x-4">
        <p class=" text-sm sm:text-base">{{descriptionSimplify}}</p>
        <button class=" bg-blue-600 text-white px-3 py-2 m-auto" @click="$router.push('/problem/' + problemShow.id)">Enter</button>
      </div>
      
      <div class=" flex justify-between">
        <div><i>{{problemShow.promoter.name}}</i></div>
        <div>
          <span class=" text-green-600">▲ {{problemShow.likes}} </span>
          <span class=" text-red-600">▼ {{problemShow.dislikes}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    problemShow : {
      id: Number,
      title: String,
      promoter: {
        id: Number,
        name: String
      },
      description: String,
      likes: Number,
      dislikes: Number
    }
  },
  computed: {
    descriptionSimplify() {
      if (this.problemShow.description.length <= 100) return this.problemShow.description;
      return this.problemShow.description.substring(0,100) + '...';
    }
  }
}
</script>
