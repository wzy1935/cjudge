<template>
  <div>
    <div class=" mx-auto max-w-3xl my-10 py-10 sm:px-10">
      <p class=" font-bold my-2 mx-4">评论区</p>

      <div v-for="cs in commentShows" :key="cs.id">
        <div class=" bg-gray-50 py-2 px-5 my-2">
          <div class="flex justify-between">
            <p class=" text-sm my-2 font-bold">{{cs.user.name}}</p>
            <button class=" text-blue-600" v-show="cs.deletable" @click="deleteComment(cs.id)">删除</button>
          </div>
          <hr/>
          <p class=" whitespace-pre-wrap p-4 text-sm">{{cs.content}}</p>
        </div>
      </div>
      <base-pager :page="pagerData" @toPage="getComments"></base-pager>

      <div>
        <p>添加评论：</p>
        <textarea v-model="content" class=" w-full h-36 p-2 border-2"></textarea>
        <button class=" py-1 px-4 bg-blue-600 text-white" @click="addComment()">提交</button>
      </div>

    </div>
  </div>
</template>

<script>
import BasePager from '../utils/BasePager.vue'

export default {
  components: {
    BasePager
  },
  data: function() {
    return {
      pagerData : {
        size: 10,
        max: 2,
        now: 1
      },
      commentShows: [],
      content: ''
    }
  },
  methods: {
    getComments(page) {
      this.axios.get('./api/problem/comment/get', {params: {
        problemId: this.$route.params.problemId,
        page: page, 
        size: this.pagerData.size
        }}).then(response => {
        this.commentShows = response.data.rows;
        this.pagerData.size = response.data.size;
        this.pagerData.max = response.data.total;
        this.pagerData.now = response.data.page;
      });
    },
    addComment() {
      this.axios.post('./api/problem/comment/add', 
      {problemId: Number(this.$route.params.problemId), content: this.content}).then(() => {
        this.getComments(1);
      });
      this.content = '';
    },
    deleteComment(commentId) {
      this.axios.post('./api/problem/comment/delete', 
      {commentId: commentId}).then(() => {
        this.getComments(1);
      });
    }
  },
  activated() {
    this.getComments(1);
  }
}
</script>

