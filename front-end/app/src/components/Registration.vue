<template>
    <div>
        <h3> {{ return_msg }} </h3>
        <form id="post-form" @submit.prevent="processForm">

            Username:<br>
            <input type="text" name="username" placeholder="Username" v-model="username"
                   v-validate="{ required: true, min: 4, max: 128 }"><br>
            <div class="error" v-if="errors.has('username')">{{errors.first('username')}}</div>

            First name:<br>
            <input type="text" name="firstname" placeholder="Firstname" v-model="firstName"
                   v-validate="{ required: true, min: 2, max: 128 }"><br>
            <div class="error" v-if="errors.has('firstname')">{{errors.first('firstname')}}</div>

            Last name:<br>
            <input type="text" name="lastname" placeholder="Lastname" v-model="lastName"
                   v-validate="{ required: true, min: 2 }"><br>
            <div class="error" v-if="errors.has('lastname')">{{errors.first('lastname')}}</div>

            Password:<br>
            <input type="password" name="password" placeholder="Password"
                   v-model="password" v-validate="{ required: true, min: 6 }"><br>
            <div class="error" v-if="errors.has('password')">{{errors.first('password')}}</div>

            Password confirmation:<br>
            <input type="password" name="matchingPassword" placeholder="Password again"
                   v-model="matchingPassword" v-validate="{ required: true, min: 6, confirmed: password }"><br>
            <div class="error" v-if="errors.has('matchingPassword')">{{errors.first('matchingPassword')}}</div>

            Email:<br>
            <input type="text" name="email" placeholder="Email"
                   v-model="email" v-validate="{ required: true, min: 4 }"><br>
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
                username: '',
                firstName: '',
                lastName: '',
                password: '',
                email: '',
                matchingPassword: '',
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
                        username: this.username,
                        email: this.email
                    })
                    .then((response) => {
                        if (response.status === 200) {
                            this.return_msg = "Registered!";
                            this.resetFields();
                        } else {
                            this.return_msg = "Sorry, there was a problem registering Your account!";
                        }
                    });
            },
            resetFields() {
                this.username = '';
                this.firstName = '';
                this.lastName = '';
                this.password = '';
                this.matchingPassword = '';
                this.email = '';
                this.return_msg ='';
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