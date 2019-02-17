<template>
  <div>
    <h2>Sample data</h2>
    <div class="items" v-for="(data, index) in response" :key='index'>
      <h3>{{data.title}}</h3>
      <p>{{data.description}}</p>
      <p>{{data.rewardDescription}}</p>
      <a v-if="data.fileLocation" v-bind:href=data.fileLocation>Extra information</a>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ViewPosts',
  data() {
    return {
      response: []
    };
  },
  mounted() {
    axios
      .get('http://localhost:8090/api/posts')
      .then((response) => {
        (this.response = response.data);
      });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .items {
    background-color: #a3a3a3;
  }
</style>
