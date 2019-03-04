<template>
    <div>
        <h3> {{ return_msg }} </h3>
        <form id="post-form" @submit.prevent="processForm">

            Firstname:<br>
            <input type="text" name="firstname" placeholder="Firstname" v-model="firstName"
                   v-validate="{ required: true, min: 3, max: 128 }"><br>
            <div class="error" v-if="errors.has('firstname')">{{errors.first('firstname')}}</div>

            Lastname:<br>
            <input type="text" name="lastname" placeholder="Lastname" v-model="lastName"
                   v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('lastname')">{{errors.first('lastname')}}</div>

            Password:<br>
            <input type="text" name="password" placeholder="Password"
                   v-model="password" v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('password')">{{errors.first('password')}}</div>

            Password confrimation:<br>
            <input type="text" name="matchingPassword" placeholder="Password again"
                   v-model="matchingPassword" v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('matchingPassword')">{{errors.first('matchingPassword')}}</div>

            Email:<br>
            <input type="text" name="email" placeholder="Email"
                   v-model="email" v-validate="{ required: true, min: 5 }"><br>
            <div class="error" v-if="errors.has('email')">{{errors.first('email')}}</div>

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
                firstName: '',
                lastName: '',
                password: '',
                matchingPassword: '',
                email: '',
                return_msg: '',
            };
        },

        methods: {
            postInfo() {
                axios
                    .post('http://localhost:8090/api/register', {

                        firstName: this.firstName,
                        lastName: this.lastName,
                        password: this.password,
                        matchingPassword: this.matchingPassword,
                        email: this.email,

                    })
                    .then((response) => {
                        if (response.status === 200) {
                            this.return_msg = "Post successfully uploaded!";
                            this.resetFields();
                        } else {
                            this.return_msg = "Sorry, there was a problem uploading Your post!";
                        }
                    });
            },

            resetFields() {
                this.firstName = '',
                    this.lastName = '',
                    this.password = '',
                    this.matchingPassword = '',
                    this.email = '',
                    this.return_msg =''

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