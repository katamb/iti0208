<template>
  <div class="main-div" style="margin-left: auto">
    <nav class="navbar navbar-expand-lg navbar-light" role="navigation" style="justify-content: space-between">

      <div class="container">

        <div class="collapse navbar-collapse" id="exCollapsingNavbar">
          <ul class="nav navbar-nav" style="justify-content:space-between; margin-left: auto">
            <router-link id="register" tag="button" to="/registration" exact>Register</router-link>
            <li class="dropdown order-1">
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
      </div>
    </nav>
  </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "Login",
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

  button[type="button"] {
    background-color: #333;
    color: white;
    cursor: pointer;
  }
</style>
