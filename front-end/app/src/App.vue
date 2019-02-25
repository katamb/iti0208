<template>
  <div id="app">

    <nav class="navbar navbar-expand-lg navbar-light">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0 ml-auto">
        <form class="form-inline my-2 my-lg-0">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
        </form>
      </ul>

    </nav>


    <Header />

    <div id="nav" >
      <p class="b">
      <nav class="navbar navbar-expand-lg navbar-light">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <router-link tag="li" class="col" class-active="active" to="/" exact> Home </router-link>
        <router-link tag="li" class="col" class-active="active" to="/mathematics" exact> Mathematics </router-link>
        <router-link tag="li" class="col" class-active="active" to="/physics" exact> Physics </router-link>
        <router-link tag="li" class="col" class-active="active" to="/chemistry" exact> Chemistry </router-link>
        <router-link tag="li" class="col" class-active="active" to="/biology" exact> Biology </router-link>
        <router-link tag="li" class="col" class-active="active" to="/cs" exact> Computer Science </router-link>
        <router-link tag="li" class="col" class-active="active" to="/varia" exact> Varia </router-link>
        <router-link tag="li" class="col" class-active="active" to="/addpost" exact> Add a Post </router-link>

        </ul>
      <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
      </nav>
      </p>
    </div>
    <router-view/>
  </div>
</template>

<script>
  import Header from './components/layout/Header.vue';

  export default {
    name: "app",
    components: {
      Header
    }
  }
</script>
<style>
  #login {

  }

  .ml-auto {
    margin-right: auto !important;
  }
  p.b {
    border: 3px solid #333;
    border-radius: 0px;
    background-color: #D6BDF3;
  }
  nav {
    background-color: #D6BDF3;
    margin: 0px auto;
  }

  input[type="search"]:hover {
    background-color: #fff;
    display: inline-block;
    border: 1px solid #333;
    border-radius: 4px;
  }

  input[type="search"]:focus {
    background-color: #ccc;
  }

  button[type="submit"] {
    background-color: #333;
    color: white;
    cursor: pointer;

  }

  div {
    text-align: center;
  }
  nav li {
    background-color: #D6BDF3;
    text-align: center;
    border:1px solid #333;
    border-radius: 4px;
  }

  nav li:hover,
  nav li.router-link-active,
  nav li.router-link-exact-active {
    text-align: center;
    background-color: #fff;
    cursor: pointer;
    border:1px solid #333;
    border-radius: 4px;
}


body, a {
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
  text-align: center;
}
</style>
