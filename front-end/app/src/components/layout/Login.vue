<template>
  <div class="main-div">
    <nav class="navbar navbar-expand-lg navbar-light" role="navigation" style="justify-content: space-between">

      <div class="container">
        <ul class="nav navbar-nav" style="margin-left: auto">
          <li style="background-color: #333">
            <router-link id="register" tag="button" to="/registration" v-if="!LoggedIn" exact>Register
            </router-link>
            <router-link id="myActivities" tag="button" to="/userActivities" v-if="LoggedIn" exact>My
              Activities
            </router-link>

            <button id="logout" tag="button" v-if="LoggedIn" @click="logout">Logout
            </button>

          <li class="dropdown order-1" v-if="!LoggedIn" style="background-color: #333">
            <button type="button" id="dropdownMenu1" data-toggle="dropdown"
                    class="btn btn-outline-success dropdown-toggle my-2 my-sm-0">Login
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu dropdown-menu-right mt-2">
              <li class="px-3 py-2">
                <form class="form" role="form" @submit="postInfo">
                  <div class="form-group">
                    <input id="usernameInput" placeholder="Username"
                           class="form-control form-control-sm" type="text" required=""
                           v-model="username">
                  </div>
                  <div class="form-group">
                    <input id="passwordInput" placeholder="Password"
                           class="form-control form-control-sm" type="password" required=""
                           v-model="password">
                  </div>
                  <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                  </div>

                  <div class="form-group text-center">
                    <small>
                      <a href="#" data-toggle="modal">Forgot password?</a>
                    </small>
                  </div>
                </form>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
    import axios from 'axios';
    import Swal from 'sweetalert2';

    export default {
        name: "Login",
        data() {
            return {
                username: '',
                password: '',
                return_msg: '',
                LoggedIn: false,
            };
        },
        methods: {
            postInfo() {
                axios
                    .post('http://localhost:8090/api/login',
                        {
                            username: this.username,
                            password: this.password
                        })
                    .then((response) => {
                        if (response.status === 200) {
                            localStorage.setItem("Authorization",
                                response.headers.authorization);
                            this.return_msg = "Logged in!";
                            this.username = response.username;
                            this.resetFields();
                            this.LoggedIn = true;
                            Swal.fire({
                                position: 'center',
                                type: 'success',
                                title: "You are logged in!",
                                showConfirmButton: false,
                                timer: 1000
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                type: 'error',
                                title: "Wrong username or password, try again!",
                                showConfirmButton: false,
                                timer: 900
                            });
                            this.resetFields();
                            this.return_msg = "Error!";
                        }
                    })
                    .catch(responce => {
                            Swal.fire({
                                position: 'center',
                                type: 'error',
                                title: "Wrong username or password, try again!",
                                showConfirmButton: false,
                                timer: 1200
                            });
                            this.resetFields();
                            this.return_msg = responce;

                        }
                    );
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
            },
            logout() {
                this.LoggedIn = false;
                localStorage.removeItem("Authorization");
                this.$router.push("/")
            }
        },
        mounted() {
            try {
                axios
                    .get('http://localhost:8090/api/check',
                        {
                            headers: {
                                "Authorization": localStorage.getItem("Authorization")
                            }
                        })
                    .then((response) => {
                        if (response.status === 200) {
                            this.LoggedIn = true;
                        }
                    })
            } catch (e) {
                this.LoggedIn = false;
            }
        }
    }


</script>

<style scoped>
  .ml-auto {
    margin-left: 0 !important;
  }

  button:focus {
    outline: 0;
  }

  nav {
    background-color: #D6BDF3;
    margin: 0px auto;
  }

  nav li {
    background-color: white;
    text-align: center;
    border: 1px solid #333;
    border-radius: 4px;
  }

  li.dropdown order-1 {
    background-color: white;
  }

  li {
    list-style-type: none;
  }


  .navbar .dropdown-menu .form-control {
    width: 200px;
  }

  button[type="button"], [tag="button"] {
    background-color: #333;
    border: #333;
    color: white;
    cursor: pointer;
    margin: 5px;
  }

  #logout, #myActivities {
    margin: 5px;
    background-color: #333;
    color: white;
    cursor: pointer;
    border: 1px solid #333;
  }


</style>
