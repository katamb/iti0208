<template>
  <div class="hello">
    <h2>Sample data</h2>
    <button @click="retrieveMsg()">Click this to retrieve info from backend</button>
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
    retrieveMsg() {
      axios
        .get('http://localhost:8090/api/posts')
        .then((response) => { (this.response = response.data); });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .items {
    background-color: #686868;
  }
</style>
