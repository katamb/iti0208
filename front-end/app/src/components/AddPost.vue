<template>
    <div>
        <h3> {{ return_msg }} </h3>
        <form id="post-form" @submit.prevent="processForm">
            <h4>Topic:</h4><br>
            <select name="topic" v-model="topic"
                    v-validate="{ required: true }">
                <option value="Mathematics">Mathematics</option>
                <option value="Physics">Physics</option>
                <option value="Chemistry">Chemistry</option>
                <option value="Biology">Biology</option>
                <option value="Computer_Science">Computer Science</option>
                <option value="Varia" selected="selected">Varia</option>
            </select><br>
            <div class="error" v-if="errors.has('topic')">{{errors.first('topic')}}</div>

            <h4>Title:</h4><br>
            <input type="text" name="title" placeholder="Title" v-model="title"
                   v-validate="{ required: true, min: 3, max: 128 }"><br>
            <div class="error" v-if="errors.has('title')">{{errors.first('title')}}</div>

            <h4>Description:</h4><br>
            <input type="text" name="description" placeholder="Description" v-model="description"
                   v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('description')">{{errors.first('description')}}</div>

            <h4>Reward description:</h4><br>
            <input type="text" name="reward_description" placeholder="Reward Description"
                   v-model="reward_description"><br>
            <div class="error" v-if="errors.has('reward_description')">{{errors.first('reward_description')}}</div>

            <h4>File:</h4><br>
            <div class="upload-btn-wrapper">
                <button class="btn">Upload a file</button>
                <input id="singleFileUploadInput" type="file" name="file" class="file-input"
                       @change="loadTextFromFile"/>
            </div>
            <br>
            <input type="submit" value="Submit" >
        </form>
    </div>
</template>


<script>
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
            postInfo() {
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
                        }
                        else {
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
                                title: "Please, check if you are logged in!",
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
                            this.postInfo();
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
                                        this.postInfo();
                                    }
                                });
                        }
                    } else {
                        Swal.fire({
                            type: 'error',
                            title: "Sorry, there was a problem uploading Your post!",
                        });
                    }
                });
            },
        },
    };
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

    select {
        width: 30%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 10%;
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
