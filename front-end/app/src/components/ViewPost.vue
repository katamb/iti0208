<template>
    <div>
        <h2>View this post</h2>
        <p>{{post.title}}</p>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        response: [],
        props: ['id'],
        computed: {
            post() {
                const id = this.id; // getting id
                return this.response // getting object from responce
            }
        },

        mounted() {
            axios
                .get('http://localhost:8090/api/posts/' + id)
                .then((response) => {
                    (this.response = response.data);
                });
        }
    }
</script>
