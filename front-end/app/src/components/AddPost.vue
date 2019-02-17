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
        File:<br>
        <input id="singleFileUploadInput" type="file" name="file" class="file-input"
               @change="loadTextFromFile"/><br>
        <input type="submit" value="Submit">
      </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'addpost',
  data() {
    return {
      title: '',
      description: '',
      reward_description: '',
      file: null,
    };
  },
  methods: {
    loadTextFromFile(input) {
      this.file = input.target.files[0];
    },
    processForm() {
      if (this.file === null) {
        // IF NO FILE
        axios.post('http://localhost:8090/api/addpost', {
          title: this.title,
          description: this.description,
          rewardDescription: this.reward_description,
        });
        // LET USER KNOW UPLOAD WAS SUCCESSFUL
      } else {
        const formData = new FormData();
        formData.append('file', this.file);
        axios.post('http://localhost:8090/api/uploadFile', formData)
          .then((response) => {
            if (response.status === 200) {
              axios.post('http://localhost:8090/api/addpost', {
                title: this.title,
                description: this.description,
                rewardDescription: this.reward_description,
                fileLocation: response.data.fileDownloadUri,
              });
              // LET USER KNOW UPLOAD WAS SUCCESSFUL
            } else {
              // ELSE LET USER KNOW UPLOAD WAS UNSUCCESSFUL
            }
          });
      }
      // uploadSingleFile(files[0]);
    },
  },
};
</script>

<style scoped>
  label {
    text-align: right;
  }
  form {
    display: compact;
  }
  input {
    flex: 10;
    padding: 5px;
  }

  input[type=text] {
    width: 30%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=submit] {
    width: 30%;
    background-color: #333;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }

</style>
