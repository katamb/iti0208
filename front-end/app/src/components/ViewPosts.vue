<template>
    <div>
        <h2 v-if="topic === 'all'" >Last uploaded</h2>
        <div class="items" v-for="data in response" :key='data.id'>
            <h3>{{data.title}}</h3>
            <p>{{data.description}}</p>
            <!--p>{{data.rewardDescription}}</p>
            <a v-if="data.fileLocation" v-bind:href=data.fileLocation>Extra information</a-->
        </div>

        <button v-if="currentPageNum > 0" type="button"
                v-on:click="prevPage()">Previous!</button>
        <button v-if="currentPageNum < numOfPages" type="button"
                v-on:click="nextPage()">Next!</button>
    </div>
</template>

<script>
import axios from 'axios';

    export default {
        name: 'ViewPosts',
        methods: {
            loadContent() {
                this.currentPageNum = 0;
                const routeName = this.$route.name;
                let url = '';
                if (routeName === 'home') {
                    url = 'http://localhost:8090/api/posts';
                    this.topic = 'all';
                } else {
                    url = 'http://localhost:8090/api/posts?topic=' + routeName;
                    this.topic = routeName;
                }

                this.dataRequest(url);
            },
            nextPage() {
                this.currentPageNum += 1;
                let url = 'http://localhost:8090/api/posts?topic=' + this.topic
                    + '&page=' + this.currentPageNum;
                this.dataRequest(url);
            },
            prevPage() {
                this.currentPageNum -= 1;
                let url = 'http://localhost:8090/api/posts?topic=' + this.topic
                    + '&page=' + this.currentPageNum;
                this.dataRequest(url);
            },
            dataRequest(url) {
                axios
                    .get(url)
                    .then((response) => {
                        this.response = response.data.posts;

                        if (response.data.amountOfPages <= 0) {
                            this.numOfPages = 0;
                        } else {
                            this.numOfPages = response.data.amountOfPages - 1;
                        }
                    });
            },
        },
        watch: {
            $route() {
                this.loadContent();
            }
        },
        data() {
            return {
                response: [],
                numOfPages: 0,
                currentPageNum: 0,
                topic: 'all',
            };
        },
        mounted() {
            this.loadContent();
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
    border:1px solid #333;
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
