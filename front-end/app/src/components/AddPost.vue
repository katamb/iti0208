<template>
    <div>
      <h3>{{return_msg}}</h3>
      <form id="post-form" @submit.prevent="processForm">
        Title:<br>
        <input type="text" name="title" placeholder="Title" v-model="title"  v-validate="{ required: true, min: 3 }"><br>
        <div class="error" v-if="errors.has('title')">{{errors.first('title')}}</div>
        Description:<br>
        <input type="text" name="description" placeholder="Description" v-model="description" v-validate="{ required: true, min: 10 }"><br>
        <div class="error" v-if="errors.has('description')">{{errors.first('description')}}</div>
        Reward description:<br>
        <input type="text" name="reward_description" placeholder="Reward Description"
               v-model="reward_description" v-validate="{ required: true, min: 10
                }"><br>
        <div class="error" v-if="errors.has('reward_description')">{{errors.first('reward_description')}}</div>
        File:<br>
        <input id="singleFileUploadInput" type="file" name="file" class="file-input"
               @change="loadTextFromFile"/><br>
        <input type="submit"  value="Submit">
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
      return_msg: '',



    };

  },
  methods: {
    loadTextFromFile(input) {
      this.file = input.target.files[0];
    },
    emptyFields() {
      this.title = '';
      this.description = '';
      this.reward_description = '';
      this.file = null;

    },



    processForm() {

      this.$validator.validate().then(valid => {
        if (valid) {
          alert("Successful!");
          if (this.title === '') {
            return;
          }

          if (this.file === null) {
            axios
                    .post('http://localhost:8090/api/addpost', {
                      title: this.title,
                      description: this.description,
                      rewardDescription: this.reward_description })
                    .then((response) => {
                      if (response.status === 200) {
                        this.return_msg = "Post successfully uploaded!";
                        this.emptyFields();
                      } else {
                        this.return_msg = "Sorry, there was a problem uploading Your post!";
                      }
                    });
            // WITH FILE
          } else {
            const formData = new FormData();
            formData.append('file', this.file);
            axios
                    .post('http://localhost:8090/api/uploadFile', formData)
                    .then((response) => {
                      if (response.status === 200) {
                        axios
                                .post('http://localhost:8090/api/addpost', {
                                  title: this.title,
                                  description: this.description,
                                  rewardDescription: this.reward_description,
                                  fileLocation: response.data.fileDownloadUri })
                                .then((response) => {
                                  if (response.status === 200) {
                                    this.return_msg = "Post successfully uploaded!";
                                    this.emptyFields();
                                  } else {
                                    this.return_msg = "Sorry, there was a problem uploading Your post!";
                                  }
                                });
                      }
                    });
          }
        } else {
          alert("Fail to submit Form!");
        }
      });
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
