<template>
  <div id="app">
    <Login/>
    <Header/>

    <nav class="navbar navbar-expand-lg navbar-light mb-3">
      <router-link tag="li" class="col my-auto font-weight-bolder order-lg-last" class-active="active" to="/addpost" exact>Post a problem</router-link>
      <a class="navbar-brand" href="#">Topics</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

        <ul class="navbar-nav mr-auto my-auto">
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/" exact>All</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/mathematics" exact> Mathematics</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/physics" exact> Physics</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/chemistry" exact> Chemistry</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/biology" exact> Biology</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/cs" exact> Computer Science</router-link>
          <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/varia" exact> Varia</router-link>
          <Search />
        </ul>

      </div>
    </nav>

    <!-- old version
    <div id="nav">
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
    </div-->
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
  div {
    text-align: center;
  }

  body {
    font-family: Arial, Helvetica, sans-serif;
    text-align: center;
  }

  nav {
    background-color: #D6BDF3 !important;
  }

  li {
    list-style-type: none;
  }

  nav li:hover,
  nav li.router-link-active,
  nav li.router-link-exact-active {
    text-decoration: underline;
    /*text-align: center;
    background-color: #fff;
    cursor: pointer;
    border: 1px solid #333;
    border-radius: 4px;*/
  }

  .error {
    color: red;
    font-weight: bolder;
  }

</style>
