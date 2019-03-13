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
                                    <form class="form" role="form">
                                        <div class="form-group">
                                            <input id="emailInput" placeholder="Email"
                                                   class="form-control form-control-sm" type="text" required="">
                                        </div>
                                        <div class="form-group">
                                            <input id="passwordInput" placeholder="Password"
                                                   class="form-control form-control-sm" type="text" required="">
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
    export default {
        name: "Login"
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
