<template>
  <div id="app">
    <Login/>
    <Header/>

    <div id="nav">
      <p class="b">
        <nav class="navbar navbar-expand-lg navbar-light">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <router-link tag="li" class="col" class-active="active" to="/" exact> Home</router-link>
            <router-link tag="li" class="col" class-active="active" to="/mathematics" exact> Mathematics</router-link>
            <router-link tag="li" class="col" class-active="active" to="/physics" exact> Physics</router-link>
            <router-link tag="li" class="col" class-active="active" to="/chemistry" exact> Chemistry</router-link>
            <router-link tag="li" class="col" class-active="active" to="/biology" exact> Biology</router-link>
            <router-link tag="li" class="col" class-active="active" to="/cs" exact> Computer Science</router-link>
            <router-link tag="li" class="col" class-active="active" to="/varia" exact> Varia</router-link>
            <router-link tag="li" class="col" class-active="active" to="/addpost" exact> Add a Post</router-link>
          </ul>

          <Search/>

        </nav>
      </p>
    </div>
    <router-view/>
  </div>
</template>

<script>
    import Header from './components/layout/Header.vue';
    import Login from './components/layout/Login.vue';
    import Search from './components/layout/Search.vue';

    export default {
        name: "app",
        components: {
            Header,
            Login,
            Search
        }
    }
</script>

<style>
  p.b {
    border: 3px solid #333;
    border-radius: 0;
    background-color: #D6BDF3;
  }

  nav {
    background-color: #D6BDF3;
    margin: 0 auto;
  }

  /*input[type="search"]:hover {
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
  }*/

  div {
    text-align: center;
  }

  nav li {
    background-color: #D6BDF3;
    text-align: center;
    border: 1px solid #333;
    border-radius: 4px;
  }

  nav li:hover,
  nav li.router-link-active,
  nav li.router-link-exact-active {
    text-align: center;
    background-color: #fff;
    cursor: pointer;
    border: 1px solid #333;
    border-radius: 4px;
  }

  li {
    list-style-type: none;
  }

  body, a {
    font-family: Arial, Helvetica, sans-serif;
    line-height: 1.4;
    text-align: center;
  }

  .error {
    color: red;
    font-weight: bolder;
  }

</style>
