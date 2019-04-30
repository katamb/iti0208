<template>

  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-xl-7 col-lg-8 col-md-9 col-sm-11">

        <h2 class="my-2">My Posts</h2>

        <div class="post-list-item my-2 p-2 text-left" v-for="(data, index) in userPosts" :key='data.id'>
          <h3>{{data.title}}</h3>
          <p>{{data.description}}</p>
          <div class="form-group mx-4 mb-2" v-if="editingPost === data.id">
            <input class="form-control mb-2" name="newTitle" v-bind:id="data.title + 2"
                   v-model="newTitle" v-validate="{ required: true, min: 3, max: 128 }">
            <div class="error" v-if="errors.has('newTitle')">{{errors.first('newTitle')}}</div>
            <textarea class="form-control mb-2" rows="3" name="newDescription" v-bind:id="data.title + 3"
                      v-model="newDescription" v-validate="{ required: true, min: 5 }"></textarea>
            <div class="error" v-if="errors.has('newDescription')">{{errors.first('newDescription')}}</div>
            <button class="btn btn-primary m-1" @click="saveEditPost(index, data.id)" v-bind:id="data.title + 4">Save
            </button>
            <button class="btn btn-secondary m-1" @click="disableEditingPost" v-bind:id="data.title + 5">Cancel</button>
          </div>
          <button class="btn btn-danger m-1" type="submit" v-bind:id="data.title + 1"
                  @click="deletePost(data.id)">Delete
          </button>
          <button class="btn btn-warning m-1" type="submit" v-bind:id="data.title"
                  @click="enableEditingPost(data)">Edit
          </button>
        </div>

        <h2 class="my-2">My Replies</h2>

        <div class="post-list-item my-2 p-2 text-left" v-for="(data, index) in userReplies" :key='data.id'>
          <p>{{data.reply}}</p>
          <div class="form-group mx-4 mb-2" v-if="editingReply === data.id">
            <textarea class="form-control mb-2" rows="3" name="newReply"
                      v-model="tempValue" v-validate="{ required: true, min: 5 }"></textarea>
            <div class="error" v-if="errors.has('newReply')">{{errors.first('newReply')}}</div>
            <button class="btn btn-primary m-1" @click="saveEditReply(index, data.id)"> Save</button>
            <button class="btn btn-secondary m-1" @click="disableEditingReply"> Cancel</button>
          </div>
          <button class="btn btn-danger m-1" type="submit" @click="deleteReply(data.id)">Delete</button>
          <button class="btn btn-warning m-1" type="submit" @click="enableEditingReply(data)">Edit</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
    import apiRequests from '../javascript/apiRequests.js';
    import errorHandling from './../javascript/errorHandling.js';

    export default {
        name: "userActivities",
        methods: {
            loadUserActivities() {
                apiRequests
                    .getRequestToApiWithAuthorization('/api/usersPosts')
                    .then((response) => {
                        this.userPosts = response.data;
                    })
                    .catch(() => {
                            localStorage.removeItem("Authorization");
                            this.$router.push("/");
                        }
                    );
                apiRequests
                    .getRequestToApiWithAuthorization('/api/usersReplies')
                    .then((response) => {
                        this.userReplies = response.data;
                    })
                    .catch(() => {
                            localStorage.removeItem("Authorization");
                            this.$router.push("/");
                        }
                    );
            },
            deletePost(postId) {
                apiRequests
                    .deleteRequestWithAuthorization('/api/delete/post/' + postId)
                    .then(() => {
                        this.loadUserActivities();
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("Failed to delete this post!");
                            localStorage.removeItem("Authorization");
                            this.$router.push("/");
                        }
                    );
            },
            deleteReply(replyId) {
                apiRequests
                    .deleteRequestWithAuthorization('/api/delete/reply/' + replyId)
                    .then(() => {
                        this.loadUserActivities();
                    })
                    .catch(() => {
                            errorHandling.errorMsgWithButton("Failed to delete this reply!")
                        }
                    );
            },
            enableEditingReply(data) {
                this.tempValue = data.reply;
                this.editingReply = data.id;
            },
            enableEditingPost(data) {
                this.newTitle = data.title;
                this.newDescription = data.description;
                this.editingPost = data.id;
            },
            disableEditingReply: function () {
                this.tempValue = null;
                this.editingReply = false;
            },
            disableEditingPost: function () {
                this.newTitle = null;
                this.newDescription = null;
                this.editingPost = false;
            },
            saveEditReply(index, replyId) {
                this.$validator.validate().then(valid => {
                    if (!valid) {
                        errorHandling.errorMsg("These changes are forbidden!", 1000);
                    } else {
                        this.userReplies[index].reply = this.tempValue;
                        apiRequests
                            .patchRequestWithAuthorization('/api/edit/reply/' + replyId, {
                                reply: this.tempValue
                            })
                            .catch(() => {
                                    errorHandling.errorMsgWithButton("Failed to update this reply!")
                                }
                            );
                        this.disableEditingReply();
                    }
                });
            },
            saveEditPost(index, postId) {
                this.$validator.validate().then(valid => {
                    if (!valid) {
                        errorHandling.errorMsg("These changes are forbidden!", 1000);
                    } else {
                        this.userPosts[index].title = this.newTitle;
                        this.userPosts[index].description = this.newDescription;
                        apiRequests
                            .patchRequestWithAuthorization('/api/edit/post/' + postId, {
                                title: this.newTitle,
                                description: this.newDescription
                            })
                            .catch(() => {
                                    errorHandling.errorMsgWithButton("Failed to update this post!")
                                }
                            );
                        this.disableEditingPost();
                    }
                });
            }
        },
        data() {
            return {
                userPosts: [],
                userReplies: [],
                tempValue: null,
                newTitle: null,
                newDescription: null,
                editingReply: false,
                editingPost: false
            };
        },
        mounted() {

            this.loadUserActivities();

        }
    }
</script>

<style scoped>
  .post-list-item {
    background-color: #f9f9f9;
    border-left: 4px solid #e9e9e9;
  }
</style>

