<template>
  <div>
    <p>{{response.title}}</p>
    <p>{{response.description}}</p>
    <p>{{response.rewardDescription}}</p>
    <a v-if="response.fileLocation" v-bind:href=response.fileLocation>Extra information</a>


    <h3>Replies</h3>
    <div v-for="answer in response.answers" :key='answer.id'>
      Reply :<br>
      {{answer.reply}}<br>
      <a v-if="answer.fileLocation" v-bind:href=answer.fileLocation>Extra information</a>
    </div>

    <h3> {{ return_msg }} </h3>
    <form id="reply-form" @submit.prevent="replyInfo">
      Your reply:<br>
      <input type="text" name="reply" placeholder="Reply" v-model="reply"
             v-validate="{ required: true, min: 5 }"><br>
      <div class="error" v-if="errors.has('reply')">{{errors.first('reply')}}</div>

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
        name: 'viewpost',
        data() {
            return {
                response: [],
                return_msg: '',
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
                axios
                    .get('http://localhost:8090/api/posts/' + this.$route.params.Pid)
                    .then((response) => {
                        this.response = response.data;
                    });
            },
            postReply() {
                axios
                    .post('http://localhost:8090/api/add/reply', {
                        postId: this.$route.params.Pid,
                        reply: this.reply,
                        fileLocation: this.file_location
                    })
                    .then((response) => {
                        if (response.status === 200) {
                            this.loadPost();
                            this.resetFields();
                        } else {
                            this.return_msg = "Sorry, there was a problem uploading Your reply!";
                        }
                    });
            },
            replyInfo() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        if (this.file === null) {
                            this.postReply();
                        } else {
                            const formData = new FormData();
                            formData.append('file', this.file);
                            axios
                                .post('http://localhost:8090/api/uploadFile', formData)
                                .then((response) => {
                                    if (response.status === 200) {
                                        this.file_location = response.data.fileDownloadUri;
                                        this.postReply();
                                    }
                                });
                        }
                    }
                })
            }
        },
        mounted() {
            this.loadPost();
        }
    }
</script>
