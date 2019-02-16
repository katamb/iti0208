<template>
  <div class="home">
    <ViewPosts/>
  </div>
</template>

<script>
// @ is an alias to /src
import ViewPosts from '@/components/ViewPosts.vue'

export default {
  name: 'home',
  components: {
    ViewPosts
  }
}
</script>
