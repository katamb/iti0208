<template>
    <div>
        <h3> {{ return_msg }} </h3>
        <form id="post-form" @submit.prevent="processForm">
            <div id="legend">
                <legend class=""><h3>Register</h3></legend>
            </div>

           <h4>Username:</h4><br>
            <input type="text" name="username" placeholder="Username" v-model="username"
                   v-validate="{ required: true, min: 4, max: 128 }"><br>
            <div class="error" v-if="errors.has('username')">{{errors.first('username')}}</div>

            <h4>First name:</h4><br>
            <input type="text" name="firstname" placeholder="Firstname" v-model="firstName"
                   v-validate="{ required: true, min: 2, max: 128 }"><br>
            <div class="error" v-if="errors.has('firstname')">{{errors.first('firstname')}}</div>

            <h4>Last name:</h4><br>
            <input type="text" name="lastname" placeholder="Lastname" v-model="lastName"
                   v-validate="{ required: true, min: 2 }"><br>
            <div class="error" v-if="errors.has('lastname')">{{errors.first('lastname')}}</div>

            <h4>Password:</h4><br>
            <input type="password" name="password" placeholder="Password"
                   v-model="password" v-validate="{ required: true, min: 6 }"><br>
            <div class="error" v-if="errors.has('password')">{{errors.first('password')}}</div>

            <h4>Password confirmation:</h4><br>
            <input type="password" name="matchingPassword" placeholder="Password again"
                   v-model="matchingPassword" v-validate="{ required: true, min: 6, confirmed: password }"><br>
            <div class="error" v-if="errors.has('matchingPassword')">{{errors.first('matchingPassword')}}</div>

            <h4>Email:</h4><br>
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

    input[type=text], input[type=password] {
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
