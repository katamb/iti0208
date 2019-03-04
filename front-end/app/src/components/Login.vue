<template>
  <div>
    <h3> {{ return_msg }} </h3>
    <form id="post-form" @submit.prevent="processForm">

      Email:<br>
      <input type="text" name="username" placeholder="Username"
             v-model="username" v-validate="{ required: true, min: 4 }"><br>
      <div class="error" v-if="errors.has('username')">{{errors.first('username')}}</div>

      Password:<br>
      <input type="password" name="password" placeholder="Password"
             v-model="password" v-validate="{ required: true, min: 6 }"><br>
      <div class="error" v-if="errors.has('password')">{{errors.first('password')}}</div>

      <br>
      <input type="submit" value="Submit">
    </form>
  </div>



</template>

<script>
    import axios from 'axios';

    export default {
        name: 'registration',
        data() {
            return {
                username: '',
                password: '',
                return_msg: '',
            };
        },
        methods: {
            postInfo() {
                axios
                    .post('http://localhost:8090/login', {
                        username: this.username,
                        password: this.password
                    })
                    .then((response) => {
                        if (response.status === 200) {
                            this.return_msg = "Logged in!";
                            this.resetFields();
                        } else {
                            this.return_msg = "Error!";
                        }
                    });
            },
            resetFields() {
                this.username = '';
                this.password = '';
                this.$nextTick(() => this.$validator.reset());
            },
            processForm() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.postInfo();
                    } else {
                        alert("Failed to submit the form!");
                    }
                });
            }


        }
    }
</script>

<style scoped>

</style>