<template>
  <div>
    <router-link to="/addpost">Add a post</router-link>
    <h2>Sample data</h2>
    <div class="items" v-for="(data, index) in response" :key='index'>
      <h3>{{data.title}}</h3>
      <p>{{data.description}}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      msg: 'First Vue app',
      response: [],
    };
  },
  methods: {
  },
  mounted() {
    axios
      .get('http://localhost:8090/api/posts')
      .then((response) => { (this.response = response.data); });
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .items {
    background-color: #a3a3a3;
  }
</style>
