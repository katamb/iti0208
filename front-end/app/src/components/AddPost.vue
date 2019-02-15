<template>
    <div>
      <form id="post-form" @submit.prevent="processForm">
        Title:<br>
        <input type="text" name="title" placeholder="Title" v-model="title"><br>
        Description:<br>
        <input type="text" name="description" placeholder="Description" v-model="description"><br>
        Reward description:<br>
        <input type="text" name="reward_description" placeholder="Reward Description"
               v-model="reward_description"><br>
        <input type="submit" value="Submit">
      </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddPost',
  data() {
    return {
      title: '',
      description: '',
      reward_description: '',
    };
  },
  methods: {
    processForm() {
      axios.post('http://localhost:8090/api/addpost', {
        title: this.title,
        description: this.description,
        rewardDescription: this.reward_description,
      });
    },
  },
};
</script>

<style scoped>

</style>
