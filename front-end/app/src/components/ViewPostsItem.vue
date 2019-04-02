<template>
  <div class="post-list-item px-2 pt-2" @click="goToDetail(data.id)">
    <h3 class="text-left">{{ data.title }}</h3>
    <p class="text-left">{{ data.description }}</p>
  </div>
</template>

<script>
    export default {
        name: "ViewPostsItem",
        props: ['data'],
        methods: {
            goToDetail(proId) {
                this.$router.push({name: 'viewpost', params: {Pid: proId}})
            }
        }
    }
</script>

<style scoped>
  .post-list-item {
    border-bottom: 2px solid #e9e9e9;
    cursor: pointer;
  }

  .post-list-item:hover {
    background-color: #f6f6f6;
  }

  .post-list-item:active {
    background-color: #f3f3f3;
  }
</style>