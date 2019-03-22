<template>
    <div>
        <div class="item">

            <h1>{{response.title}}</h1>
            <div class="description">
                <div class="post">
                <h4>{{response.description}}</h4>
                <p>{{response.rewardDescription}}</p>
                <a v-if="response.fileLocation" v-bind:href=response.fileLocation>Extra information</a>

                </div>
            <br>

            <h2>Replies</h2>
                <div v-for="answer in response.answers" :key='answer.id'>d                    <div class="reply-description">

                    <h3>Reply:</h3><br>
                    {{answer.reply}}<br>
                    <a class="a-button" v-if="answer.fileLocation" v-bind:href=answer.fileLocation>Extra information</a>
                        <h3> {{ return_msg }} </h3>
                    </div>
                    <br>
            </div>
                <form id="reply-form" @submit.prevent="replyInfo">

                    <h3>Your reply:</h3>
                    <input type="text" name="reply" placeholder="Reply" v-model="reply"
                           v-validate="{ required: true, min: 5 }"><br>
                    <div class="error" v-if="errors.has('reply')">{{errors.first('reply')}}</div>
                    <br>
                    <div class="upload-btn-wrapper">
                        <button class="btn">Upload a file</button>
                        <input id="singleFileUploadInput" type="file" name="file" class="file-input"
                               @change="loadTextFromFile"/>
                    </div>

                    <br>

                    <input type="submit" value="Submit">


                </form>
                <br>
            </div>
            <br>
        </div>
        <br>
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
                    .post('http://localhost:8090/api/add/reply',
                        {
                        postId: this.$route.params.Pid,
                        reply: this.reply,
                        fileLocation: this.file_location
                        },
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
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
                                .post('http://localhost:8090/api/uploadFile', formData,
                                    {
                                        headers: {
                                            "Authorization": localStorage.getItem("Authorization")
                                        }
                                    })
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

<style scoped>

    .upload-btn-wrapper {
        position: relative;
        overflow: hidden;
        display: inline-block;
    }

    .btn {
        border: 2px solid gray;
        color: gray;
        background-color: white;
        padding: 8px 20px;
        border-radius: 8px;
        font-size: 20px;
        font-weight: bold;
    }

    .upload-btn-wrapper input[type=file] {
        font-size: 100px;
        position: absolute;
        left: 0;
        top: 0;
        opacity: 0;
    }


    p {
        color: #333;
    }

    .item {
        width: 80%;
        height: auto;
        margin: 0px auto;
        background-color: lightgray;
        color: black;
        text-align: center;
        border: 1px solid #333;
        border-radius: 4px;
    }

    .description {
        border-radius: 4px;
        padding-right: 30px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: medium;
        text-align: center;
        width: 80%;
        height: auto;
        margin: 0px auto;
        color: black;
        background-color: #fff;
    }

    .reply-description {
        padding-right: 30px;
        border-radius: 4px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: medium;
        text-align: center;
        width: 100%;
        height: auto;
        margin: 0px auto;
        color: black;
        background-color: #fff;
        border-style: groove;
    }

    input[type=text] {
        color: black;
        width: 60%;
        height: auto;
        padding: 12px 20px;
        margin: 0px auto;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;

    }


    input[type="submit"] {
        display: inline-block;
        width: 80px;
        height: 30px;
        border: 1px solid #333;
        border-radius: 4px;
        background-color: #333;
        color: white;
        cursor: pointer;
    }

    h2{
        overflow:hidden;
    }
    h2:after {
        content:'';
        display:inline-block;
        width:100%; height:100%;
        margin-right:-100%;
        border-bottom:1px solid #000;
    }
    h2:before {
        content:'';
        display:inline-block;
        width:100%; height:100%;
        margin-left:-100%;
        border-bottom:1px solid #000;
    }

    #reply-form {
        text-align: left;
        padding: 30px;
    }

    .post {
        text-align: left;
        padding: 30px;
    }

    .a-button {
        display: block;
        width: 115px;
        height: 25px;
        background: #4E9CAF;
        padding: 10px;
        text-align: center;
        border-radius: 5px;
        color: white;
        font-weight: bold;
    }
</style>
