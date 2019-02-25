<template>
    <div>
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
        name: "addreply",
        data() {
            return {

                reply: '',
                return_msg: '',
            };
        },
        methods: {

            replyInfo() {
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
                        } else {
                            this.return_msg = "Sorry, there was a problem uploading Your reply!";
                        }
                    });
            },

            resetFields() {
                this.reply = '';
            },
        }
    }

</script>

<style scoped>

</style>