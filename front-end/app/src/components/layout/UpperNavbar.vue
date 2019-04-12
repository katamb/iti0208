<template>
  <nav class="p-2">
    <div class="text-right">

      <router-link class="btn btn-dark mx-1" id="postProblems" tag="button" to="/addpost" v-if="loggedIn" exact>
        Post a problem
      </router-link>

      <router-link class="btn btn-dark mx-1" id="register" tag="button" to="/registration" v-if="!loggedIn" exact>
        Register
      </router-link>

      <router-link class="btn btn-dark mx-1" id="myActivities" tag="button" to="/userActivities" v-if="loggedIn"
                   exact>
        My Activities
      </router-link>

      <button class="btn btn-dark mx-1" id="logout" v-if="loggedIn" @click="logout">Logout
      </button>

      <div class="dropdown" v-if="!loggedIn">
        <button type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                class="btn btn-dark dropdown-toggle">Login
        </button>
        <div class="dropdown-menu dropdown-menu-right py-0">
          <div class="px-3 pt-3 login-dropdown">

            <form @submit.prevent="logIn">
              <div class="form-group">
                <input id="usernameInput" placeholder="Username" name="username"
                       class="form-control form-control-sm custom-input" type="text"
                       v-model="username" v-validate="{ required: true, min: 4, max: 128 }"
                       autocomplete="username">
                <div class="error" v-if="errors.has('username')">{{errors.first('username')}}</div>
              </div>

              <div class="form-group">
                <input id="passwordInput" placeholder="Password" name="password"
                       class="form-control form-control-sm" type="password"
                       v-model="password" v-validate="{ required: true, min: 6 }"
                       autocomplete="new-password">
                <div class="error" v-if="errors.has('password')">{{errors.first('password')}}</div>
              </div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" name="login">Login</button>
              </div>
            </form>

            <div class="form-group text-center">
              <small>
                <a href="#" data-toggle="modal">Forgot password?</a>
              </small>
            </div>

          </div>
        </div>
      </div>

    </div>
  </nav>
</template>

<script>
    import errorHandling from './../../javascript/errorHandling.js';
    import apiRequests from './../../javascript/apiRequests.js';

    export default {
        name: "Login",
        data() {
            return {
                username: '',
                password: '',
                loggedIn: false,
            };
        },
        methods: {
            logIn() {
                apiRequests
                    .postRequestToApi('/api/login', {
                        username: this.username,
                        password: this.password
                    })
                    .then((response) => {
                        if (response.status === 200) {
                            localStorage.setItem("Authorization", response.headers.authorization);
                            this.loggedIn = true;
                            this.resetFields();
                            errorHandling.successMsg("You are logged in!", 1000);
                        } else {
                            errorHandling.errorMsg("Wrong username or password, try again!", 1000);
                        }
                    })
                    .catch(() => {
                        errorHandling.errorMsg("Wrong username or password, try again!", 1200);
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
                        this.logIn();
                    } else {
                        alert("Failed to submit the form!");
                    }
                });
            },
            logout() {
                this.loggedIn = false;
                localStorage.removeItem("Authorization");
                this.$router.push("/")
            }
        },
        mounted() {
            if (localStorage.getItem("Authorization") !== null) {
                apiRequests.getRequestToApiWithAuthorization('/api/check')
                    .then(response => {
                        if (response.status === 200) {
                            this.loggedIn = true;
                        }
                    })
                    .catch(() => {
                        this.loggedIn = false;
                        this.logout();
                    });
            }
        }
    }


</script>

<style scoped>
  .dropdown {
    display: inline-block !important;
  }

  nav {
    background-color: #D6BDF3;
  }

  .login-dropdown {
    background-color: white;
    text-align: center;
  }

  .custom-input {
    width: 200px;
  }

</style>
