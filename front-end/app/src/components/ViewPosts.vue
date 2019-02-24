<template>
    <div>
        <h2>Last uploaded</h2>
        <div class="items" v-for="(data, index) in response" :key='index'>
            <h3>{{data.title}}</h3>
            <p>{{data.description}}</p>
            <p>{{data.rewardDescription}}</p>
            <a v-if="data.fileLocation" v-bind:href=data.fileLocation>Extra information</a>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'ViewPosts',
        data() {
            return {
                response: []
            };
        },
        mounted() {
            axios
                .get('http://localhost:8090/api/posts')
                .then((response) => {
                    (this.response = response.data);
                });
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        color: #333;
    }

    .items {
        width: 80%;
        margin: 0px auto;
        background-color: lightgray;
        color: black;
        text-align: center;
        border: 1px solid #333;
        border-radius: 4px;
    }

    .items:hover {
        background-color: #fff;
        cursor: pointer;
    }

    .items:active {
        background-color: #F1F1F1;
        cursor: pointer;
    }

    body {
        background-color: lightgray;
    }

</style>
