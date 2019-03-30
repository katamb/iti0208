<template>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-xl-7 col-lg-8 col-md-9 col-sm-11">

        <div class="post-item text-left p-3 mb-3">
          <h3>{{response.title}}</h3>
          <p>{{response.description}}</p>
          <p v-if="response.rewardDescription">Reward for solving: {{response.rewardDescription}}</p>
          <a v-if="response.fileLocation" v-bind:href=response.fileLocation>Extra information</a>
          <p v-if="response.postedBy">
            <small>
              Posted by: {{response.postedBy}}
            </small>
          </p>
        </div>

        <div class="post-item text-left p-2 mx-2 mb-2" v-for="answer in response.answers" :key='answer.id'>
          <p class="font-weight-bolder" v-if="answer.postedBy">{{answer.postedBy}}:</p>
          <p>{{answer.reply}}</p>
          <a v-if="answer.fileLocation" v-bind:href=answer.fileLocation>Extra information</a>
        </div>

        <form class="my-3 p-2 reply-area" id="reply-form" @submit.prevent="replyInfo">
          <div class="form-group text-left">
            <label for="exampleFormControlTextarea1">Reply to this post:</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="reply" placeholder="Reply"
                      v-model="reply" v-validate="{ required: true, min: 5 }"></textarea>
            <div class="error" v-if="errors.has('reply')">{{errors.first('reply')}}</div>
          </div>
          <div class="form-group text-left">
            <label for="fileUpload">File:</label><br/>
            <label class="custom-file-upload" for="fileUpload">
              Choose a file
            </label>
            <input type="file" class="form-control-file" id="fileUpload" @change="loadTextFromFile">
            <p>
              <small>
                Max file size: 20MB <br/>
                Allowed file types: .txt, .pdf, .png, .jpg, .doc, .docx, .xls, .xlsx, .rtf, .jpeg, .tiff, .ppt
              </small>
            </p>
          </div>
          <input class="btn btn-lg btn-primary" type="submit" value="Submit reply">
        </form>

      </div>
      <br>
    </div>
  </div>
</template>

<script>
    import apiRequests from '../javascript/apiRequests.js';
    import errorHandling from './../javascript/errorHandling.js';

    export default {
        name: 'viewpost',
        data() {
            return {
                response: [],
                reply: '',
                file_location: '',
                file: null
            };
        },
        methods: {
            loadTextFromFile(input) {
                this.file = input.target.files[0];
            },
            resetFields() {
                this.reply = '';
                this.file_location = '';
                this.file = null;
                this.$nextTick(() => this.$validator.reset())
            },
            loadPost() {
                apiRequests
                    .getRequestToApi('/api/posts/' + this.$route.params.Pid)
                    .then((response) => {
                        this.response = response.data;
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("This post no longer exists!")
                        }
                    );
            },
            postReply() {
                apiRequests
                    .postRequestToApiWithAuthorization('/api/add/reply', {
                            postId: this.$route.params.Pid,
                            reply: this.reply,
                            fileLocation: this.file_location
                        }
                    )
                    .then(() => {
                        this.resetFields();
                        this.loadPost();
                    })
                    .catch((error) => {
                        if (error.response.status === 401 || error.response.status === 403) {
                            errorHandling.errorMsgWithButton("You need to be logged in to reply!");
                        } else {
                            errorHandling.errorMsgWithButton("Sorry, " +
                                "there was a problem and the post couldn't be uploaded!");
                        }
                    });
            },
            replyInfo() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        if (this.file === null || this.file === undefined) {
                            this.postReply();
                        } else {
                            const formData = new FormData();
                            formData.append('file', this.file);
                            apiRequests
                                .postRequestToApiWithAuthorization('/api/uploadFile', formData)
                                .then((response) => {
                                    this.file_location = response.data.fileDownloadUri;
                                    this.postReply();
                                })
                                .catch(() => {
                                        errorHandling.errorMsgWithButton("There was a problem uploading Your file!" +
                                            "Check the limitations!")
                                    }
                                );
                        }
                    } else {
                        errorHandling.errorMsg("Form wasn't filled in properly!!", 1200)
                    }
                })
            }
        },
        mounted() {
            this.loadPost();
        }
    }
</script>

<style scoped>
  .post-item {
    background-color: #f9f9f9;
    border-left: 4px solid #e9e9e9;
  }

  .reply-area {
    background-color: #f9f9f9;
  }

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
