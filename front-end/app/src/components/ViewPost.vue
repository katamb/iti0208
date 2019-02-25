<template>
    <div>
        <h2>View this post</h2>
        <p>{{response.title}}</p>
        <p>{{response.description}}</p>
        <p>{{response.reward_description}}</p>
        <h3>Replies</h3>
        <div v-if = "response.answers!=null" v-for="answer in response.answers">
            Reply :<br>
            {{answer.reply}}
        </div>
        <h3> {{ return_msg }} </h3>
        <form id="reply-form" @submit.prevent="replyInfo">

            Your reply:<br>
            <input type="text" name="reply" placeholder="Reply" v-model="reply"
                   v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('reply')">{{errors.first('reply')}}</div>
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
                return_msg: ''

            };
        },

            methods: {

                resetFields() {
                    this.reply = '';
                    this.$nextTick(() => this.$validator.reset())
                },

                replyInfo() {
                    this.$validator.validate().then(valid => {
                        if (valid) {
                            console.log(this.$route.params.Pid);
                            console.log(this.reply);
                            axios
                                .post('http://localhost:8090/api/add/reply', {

                                    reply: this.reply,
                                    postId: this.$route.params.Pid
                                })
                                .then((response) => {
                                    if (response.status === 200) {
                                        this.return_msg = "Reply successfully uploaded!";
                                        this.resetFields();
                                        window.location.reload()
                                    } else {
                                        this.return_msg = "Sorry, there was a problem uploading Your reply!";
                                    }
                                });
                        }
                    })
                }
                },

            mounted() {
                axios
                    .get('http://localhost:8090/api/posts/' + this.$route.params.Pid)
                    .then((response) => {
                        (this.response = response.data);
                    });
            }
    }
</script>
