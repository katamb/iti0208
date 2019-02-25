<template>
    <div class="hello">
        <div class="item">
        <h2>{{response.title}}</h2>
            <div class="description">
                <h3>Description:</h3><br>
        <p>{{response.description}}</p>
                <h3>Reward description:</h3><br>
        <p>{{response.rewardDescription}}</p>
                <h3>File:</h3><br>
        <a v-if="response.fileLocation" v-bind:href=response.fileLocation>Extra information</a>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "ViewPost",
        data () {
            return {
                id : 0,
                response: [],
            };
        },
        created() {
            this.id = this.$route.params.id;
        },


        mounted() {
            axios
                .get('http://localhost:8090/api/posts/'+ this.id)
                .then((response) => {
                    (this.response = response.data);
                });
        }
    }
</script>

<style scoped>
    p {
        color: #333;
    }
    .item {
        width: 1250px;
        height: 600px;
        margin: 0px auto;
        background-color: lightgray;
        color: black;
        text-align: center;
        border:1px solid #333;
        border-radius: 4px;
    }

    .description {
        font-family: Arial, Helvetica, sans-serif;
        line-height: 1;
        font-size: medium;
        text-align: center;
        width: 1150px;
        height: 400px;
        margin: 0px auto;
        color: black;
        background-color: #fff;
    }
</style>
