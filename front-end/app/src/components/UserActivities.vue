<template>
    <div>
        <h2>My Posts</h2>
        <div class="items" v-for="(data, index) in response" :key='data.id'>
            <h3> {{data.title}}</h3>
            <p>{{data.description}}</p>
            <div v-if="editing1 == data.id">
                <input v-model="newTitle">
                <input v-model="newDescription">
                <input v-model="newRewardDescription">
                <button @click="disableEditingPost"> Cancel </button>
                <button @click="saveEditPost(index, data.id)"> Save </button>
            </div>
            <input type="submit" value="Delete" @click="deletePost(data.id)">
            <input type="submit" value="Edit" @click="enableEditingPost(data)">

        </div>
            <!--p>{{data.rewardDescription}}</p>
            <a v-if="data.fileLocation" v-bind:href=data.fileLocation>Extra information</a-->

        <h2>My Replies</h2>
        <div class="items" v-for="(data, index) in response1" :key='data.id'>
            <h3> Reply:</h3>
            <p>{{data.reply}}</p>
            <!--p>{{data.rewardDescription}}</p>
            <a v-if="data.fileLocation" v-bind:href=data.fileLocation>Extra information</a-->
            <div v-if="editing == data.id">
                <input v-model="tempValue">
                <button @click="disableEditingReply"> Cancel </button>
                <button @click="saveEditReply(index, data.id)"> Save </button>
            </div>
            <input type="submit" value="Delete" @click="deleteReply(data.id)">
            <input type="submit" value="Edit" @click="enableEditingReply(data)">

        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {

        name: "userActivities",
        methods : {
            loadUserActivities() {
                axios
                    .get('http://localhost:8090/api/usersPosts',
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        })

                    .then((response) => {
                        this.response = response.data;
                    });
                axios
                    .get('http://localhost:8090/api/usersReplies',
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        })

                    .then((response) => {
                        this.response1 = response.data;
                    });
            },

            deletePost(postId) {
                axios.
                    delete('http://localhost:8090/api/delete/post/' + postId,  {
                    headers: {
                        "Authorization": localStorage.getItem("Authorization")
                    }
                });

                this.loadUserActivities();
            },

            deleteReply(replyId) {
                axios.
                delete('http://localhost:8090/api/delete/reply/' + replyId,
                    {
                        headers: {
                            "Authorization": localStorage.getItem("Authorization")
                        }
                    });
                this.loadUserActivities();
            },

            enableEditingReply(data){
                this.tempValue = data.reply;
                this.editing = data.id;
            },
            enableEditingPost(data){
                this.newTitle = data.title;
                this.newDescription = data.description;
                this.newRewardDescription = data.rewardDescription;
                this.editing1 = data.id;
            },

            disableEditingReply: function(){
                this.tempValue = null;
                this.editing = false;
            },
            disableEditingPost: function(){
                this.newTitle = null;
                this.newDescription = null;
                this.newRewardDescription = null;
                this.editing1 = false;
            },
            saveEditReply(index, replyId){

                this.response1[index].reply = this.tempValue;
                axios
                    .post('http://localhost:8090/api/edit/reply/' + replyId,

                        {
                            reply : this.tempValue

                        },
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        });
                this.disableEditingReply();
            },
            saveEditPost(index, postId) {
                this.response[index].title = this.newTitle;
                this.response[index].description = this.newDescription;
                this.response[index].rewardDescription = this.newRewardDescription;
                axios
                    .post('http://localhost:8090/api/edit/post/' + postId,

                        {
                             title: this.newTitle,
                             description: this.newDescription,
                             rewardDescription: this.newRewardDescription


                        },
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        });
                this.disableEditingPost();

            }

        },
        data() {
            return {
                response: [],
                response1: [],
                tempValue: null,
                newTitle: null,
                newDescription: null,
                newRewardDescription: null,
                editing: false,
                editing1 : false

            };

        },
        mounted() {
            this.loadUserActivities();
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

