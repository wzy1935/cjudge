<template>
  <div>
    <div class=" flex justify-between bg-green-200 text-green-600 p-4 my-2 w-5/6 max-w-3xl mx-auto">
      <div @click="addProblem" class=" cursor-pointer">新建问题</div>
      <div @click="addProblem" class=" cursor-pointer">+</div>
    </div>
    <div v-for="ps in problemShows" :key="ps.id">
    <problem-card :problemShow="ps"></problem-card>
    </div>
    <base-pager :page="pagerData" @toPage="getData"></base-pager>
  </div>
</template>

<script>
import ProblemCard from '../components/repository/ProblemCard.vue'
import BasePager from '../components/utils/BasePager.vue'

export default {
  components: {
    ProblemCard,
    BasePager
  },
  data: function() {
    return {
      problemShows : [],
      pagerData : {
        size: 10,
        max: 2,
        now: 1
      }
    }
  }, 
  methods: {
    getData(page) {
      this.axios.get('./api/problem/getAll', {params: {page: page, size: this.pagerData.size}}).then(response => {
        this.problemShows = response.data.rows;
        this.pagerData.size = response.data.size;
        this.pagerData.max = response.data.total;
        this.pagerData.now = response.data.page;
      });
    },
    addProblem() {
      this.$router.push('/repository/add');
    }
  },
  activated() {
    this.getData(1);
  }
}
</script>
