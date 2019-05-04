<template>
  <div class="container-fluid pb-5">
    <div class="row justify-content-center">
      <div class="col-xl-7 col-lg-8 col-md-9 col-sm-11">

        <div class="post-item text-left p-3 mb-3">
          <div class="text-left">
            <h3>{{response.title}}</h3>
            <p>{{response.description}}</p>
            <a v-if="response.fileLocation" v-bind:href=response.fileLocation>Download file with extra information</a>
            <p v-if="response.rewardDescription" class="mt-2">Reward for solving: {{response.rewardDescription}}</p>
            <p class="mt-2 mb-1">
              <small>
                Posted by: {{response.postedBy}}
              </small>
            </p>
          </div>
            <div v-if="response.canDelete" class="text-right">
            <button class="btn btn-danger" @click="deletePost(response.id)">Delete post</button>
          </div>
            <div v-if="response.bestReplyId && response.canUnlock == true" class="text-right pt-3">
                <button class="btn btn-success" @click="unlockPost(response.id)">Unlock topic</button>
            </div>
        </div>
          <div v-for="bestreply in bestReply" :key='bestreply.id'
               class="post-item text-left border-5 border-success p-2 mx-2 mb-2">
              <div class="text-left">
                  <h5 class="font-weight-bolder" style="color: gold">  Best answer</h5>
                  <p class="font-weight-bolder" v-if="bestreply.postedBy">{{bestreply.postedBy}}:</p>
                  <p class="mb-1">{{bestreply.reply}}</p>

                  <a v-if="bestreply.fileLocation" v-bind:href=bestreply.fileLocation>Extra information</a>
              </div>
              <div v-if="bestreply.canDelete" class="text-right">
                  <button class="btn btn-danger" @click="deleteReply(bestreply.id)">Delete reply</button>
              </div>
              <div v-if="bestreply.canMarkAsBest && !response.bestReplyId" class="text-right pt-3">
                  <button class="btn btn-success" @click="markAsBest(bestreply.id)">Mark as best reply</button>
              </div>
              <div v-if="bestreply.canMarkAsBest && response.bestReplyId && !bestreply.bestAnswer" class="text-right pt-3">
                  <button class="btn btn-success" @click="markAsBest(bestreply.id)">Change best reply</button>
              </div>
          </div>
        <div class="post-item text-left p-2 mx-2 mb-2" :class="{ 'border-5 border-success' : answer.bestAnswer == true}"
             v-for="answer in response.replies" :key='answer.id'>
          <div class="text-left">
            <p class="font-weight-bolder" v-if="answer.postedBy">{{answer.postedBy}}:</p>
            <p class="mb-1">{{answer.reply}}</p>

            <a v-if="answer.fileLocation" v-bind:href=answer.fileLocation>Extra information</a>
          </div>
          <div v-if="answer.canDelete" class="text-right">
            <button class="btn btn-danger" @click="deleteReply(answer.id)">Delete reply</button>
          </div>
          <div v-if="answer.canMarkAsBest && !response.bestReplyId" class="text-right pt-3">
          <button class="btn btn-success" @click="markAsBest(answer.id)">Mark as best reply</button>
          </div>
            <div v-if="answer.canMarkAsBest && response.bestReplyId && !answer.bestAnswer" class="text-right pt-3">
                <button class="btn btn-success" @click="markAsBest(answer.id)">Change best reply</button>
            </div>
        </div>

        <form class="my-3 p-2 reply-area" id="reply-form" v-if="!response.bestReplyId" @submit.prevent="replyInfo">
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
                file: null,
                bestReply: []
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
                    .getRequestToApiWithAuthorization('/api/posts/' + this.$route.params.Pid)
                    .then((response) => {
                        this.response = response.data;
                        this.bestReply = [];
                        for (var i = 0; i < this.response.replies.length; i++) {
                            if (this.response.replies[i].bestAnswer === true) {
                                this.bestReply.unshift(this.response.replies[i]);
                                this.response.replies.splice(i, 1)
                            }
                        }
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("This post no longer exists!")
                        }
                    );
            },
            deletePost(postId) {
                apiRequests
                    .deleteRequestWithAuthorization('/api/delete/post/' + postId)
                    .then(() => {
                        this.$router.push("/");
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("Failed to delete this post!");
                        }
                    );
            },
            unlockPost(postId) {
                apiRequests
                    .postRequestToApiWithAuthorization('/api/unlock/' + postId)
                    .then(() => {
                        errorHandling.successMsg("Topic unlocked!", 1000);
                        this.loadPost();
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("Failed to unlock topic!");
                        }
                    );

            },
            deleteReply(replyId) {
                apiRequests
                    .deleteRequestWithAuthorization('/api/delete/reply/' + replyId)
                    .then(() => {
                        this.loadPost();
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("Failed to delete this reply!");
                        }
                    );
            },
          markAsBest(replyId) {
            apiRequests
                    .postRequestToApiWithAuthorization('/api/best_answer/' + replyId)
                    .then(() => {
                      errorHandling.successMsg("Marked as best!", 1000);
                        this.loadPost();
                    })
                            .catch(() => {
                                      errorHandling.errorMsgWithButton("Failed to mark as best!");
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
            },
        },
        mounted() {
            this.loadPost();
        }
    }
</script>

<style scoped>
  .post-item {
    background-color: #f9f9f9;
    border: 4px solid #e9e9e9;
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
