<template>
  <div>
    <div class=" flex space-x-2 justify-center my-3">
      <div class=" bg-gray-100 w-8 h-8 flex justify-center items-center" @click="toPage(1)" v-if="maxPage >= 1" :class="page.now == 1 ? 'bg-gray-600 text-white' : ' cursor-pointer'">1</div>
      <div class=" bg-gray-100 w-8 h-8 flex justify-center items-center" v-if="leftDot">...</div> 
      <div v-for="l in lists" :key="l">
        <div class=" bg-gray-100 w-8 h-8 flex justify-center items-center" @click="toPage(l)" :class="page.now == l ? 'bg-gray-600 text-white' : ' cursor-pointer'">{{l}}</div>
      </div>
      <div class=" bg-gray-100 w-8 h-8 flex justify-center items-center" v-if="rightDot">...</div> 
      <div class=" bg-gray-100 w-8 h-8 flex justify-center items-center" @click="toPage(maxPage)" v-if="maxPage >= 2" :class="page.now == maxPage ? 'bg-gray-600 text-white' : ' cursor-pointer'">{{maxPage}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    page: {
      size : Number,
      max : Number,
      now : Number
    }
  },

  computed: {
    maxPage() {
      return Math.floor((this.page.max + this.page.size - 1) / this.page.size);
    },
    lists() {
      if (this.maxPage > 6) {
        if (this.page.now < 3) {
          return [2,3,4,5];
        } else if (this.maxPage - this.page.now > 3) {
          let num = this.page.now;
          return [num-1,num,num+1,num+2];
        } else {
          let max = this.maxPage;
          return [max-4,max-3,max-2,max-1];
        }
      } else {
        let _lists = [];
        for (let i = 2; i <= this.maxPage-1; i++) {
          _lists.push(i);
        }
        return _lists;
      }
    },
    leftDot() {
      return !(this.maxPage < 6 || this.page.now <= 3);
    },
    rightDot() {
      return !(this.maxPage < 6 || this.maxPage - this.page.now <= 3);
    }
  },

  data: function() {
    return {
      jump : 1,
    }
  },
  methods: {

    addPage(num) {
      this.jump = Number(this.jump) + Number(num);
      this.toPage();
    },
    toPage(num) {
      if (num < 1 || num > this.maxPage) return;
      this.$emit('toPage', num);
    }
  }
}
</script>
