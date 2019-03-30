<template>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-xl-5 col-lg-6 col-md-8 col-sm-11">

        <h3>Create a task for others to solve!</h3>
        <h3> {{ return_msg }} </h3>

        <form id="post-form" @submit.prevent="processForm">

          <div class="form-group text-left">
            <label for="topic">Topic:</label>
            <select class="form-control" id="topic" name="topic" v-model="topic" v-validate="{ required: true }">
              <option value="Mathematics">Mathematics</option>
              <option value="Physics">Physics</option>
              <option value="Chemistry">Chemistry</option>
              <option value="Biology">Biology</option>
              <option value="Computer_Science">Computer Science</option>
              <option value="Varia" selected="selected">Varia</option>
            </select>
            <div class="error" v-if="errors.has('topic')">{{errors.first('topic')}}</div>
          </div>

          <div class="form-group text-left">
            <label for="title">Title:</label>
            <input id="title" class="form-control" type="text" name="title" placeholder="Title"
                   v-model="title" v-validate="{ required: true, min: 3, max: 128 }">
            <div class="error" v-if="errors.has('title')">{{errors.first('title')}}</div>
          </div>

          <div class="form-group text-left">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" rows="3"
                      name="description" placeholder="Description"
                      v-model="description" v-validate="{ required: true, min: 5 }"
            ></textarea>
            <div class="error" v-if="errors.has('description')">{{errors.first('description')}}</div>
          </div>

          <div class="form-group text-left">
            <label for="reward_description">Reward description:</label>
            <input id="reward_description" class="form-control" type="text" name="reward_description"
                   placeholder="Reward description" v-model="reward_description">
          </div>


          <div class="form-group text-left">
            <label for="fileUpload">File:</label><br/>
            <label class="custom-file-upload" for="fileUpload">
              Choose a file
            </label>
            <input type="file" class="form-control-file" id="fileUpload">
            <p><small>Max file size 20MB <br/>
              Allowed file types: .txt, .pdf, .png, .jpg, .doc, .docx, .xls, .xlsx, .rtf, .jpeg, .tiff, .ppt</small></p>
          </div>

          <input class="btn btn-lg btn-primary mb-3" type="submit" value="Submit">
        </form>
      </div>
    </div>
  </div>
</template>


<script>
    import apiRequests from './../javascript/apiRequests.js';
    import errorHandling from './../javascript/errorHandling.js';
    import axios from 'axios';
    import Swal from 'sweetalert2';

    export default {
        name: 'addpost',
        data() {
            return {
                topic: 'Varia',
                title: '',
                description: '',
                reward_description: '',
                file_location: '',
                file: null,
                return_msg: '',
            };
        },
        methods: {

            loadTextFromFile(input) {
                this.file = input.target.files[0];
            },
            resetFields() {
                this.topic = 'Varia';
                this.title = '';
                this.description = '';
                this.reward_description = '';
                this.file_location = '';
                this.file = null;
                this.$nextTick(() => this.$validator.reset())
            },
            postFormData() {
                axios
                    .post('http://localhost:8090/api/add/post',
                        {
                            topic: this.topic,
                            title: this.title,
                            description: this.description,
                            rewardDescription: this.reward_description,
                            fileLocation: this.file_location,

                        },
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        })
                    .then((response) => {
                        if (response.status === 200) {
                            Swal.fire({
                                position: 'center',
                                type: 'success',
                                title: "Post successfully uploaded!",
                                showConfirmButton: false,
                                timer: 1200
                            });
                            this.return_msg = "Post successfully uploaded!";
                            this.resetFields();
                        } else if (response.status === 401 || response.status === 500) {
                            Swal.fire({
                                type: 'error',
                                title: "Please log in to do post",
                            });
                            this.return_msg = "Please log in to do post!";
                        } else {
                            Swal.fire({
                                type: 'error',
                                title: "Sorry, there was a problem uploading Your post!",
                            });
                            this.return_msg = "Sorry, there was a problem uploading Your post!";
                        }
                    })
                    .catch((error) => {
                            Swal.fire({
                                position: 'center',
                                type: 'error',
                                title: "Wrong username or password, try again!",
                                showConfirmButton: false,
                                timer: 1200
                            });
                            this.resetFields();
                            this.return_msg = error;

                        }
                    );
            },
            processForm() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        if (this.file === null) {
                            this.postFormData();
                        } else {
                            const formData = new FormData();
                            formData.append('file', this.file);

                            apiRequests
                                .postRequestToApiWithAuthorization('/api/uploadFile', formData)
                                .then((response) => {
                                    if (response.status === 200) {
                                        this.file_location = response.data.fileDownloadUri;
                                        this.postFormData();
                                    } else {
                                        errorHandling.errorMsgWithButton("There was a problem uploading the file, " +
                                            "it's either larger than 20MB or not an accepted file type!");
                                    }
                                });
                        }
                    } else {
                        errorHandling.errorMsg("The form wasn't filled in properly!", 1000);
                    }
                });
            },
        },
    };
</script>


<style scoped>
  .custom-file-upload {
    border: 2px solid gray;
    color: gray;
    background-color: white;
    padding: 8px 20px;
    border-radius: 8px;
    font-size: 20px;
    font-weight: bold;
  }

  input[type=file] {
    display: none;
  }
</style>
