<template>
  <nav class="navbar navbar-expand-lg navbar-light mb-3">
    <a class="navbar-brand" href="/">Topics</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

      <ul class="navbar-nav mr-auto my-auto">
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/" exact>All
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/mathematics" exact>
          Mathematics
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/physics" exact>Physics
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/chemistry" exact>
          Chemistry
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/biology" exact>Biology
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/cs" exact>Computer
          Science
        </router-link>
        <router-link tag="li" class="col my-auto font-weight-bolder" class-active="active" to="/varia" exact>Varia
        </router-link>
      </ul>

      <Search/>
    </div>
  </nav>

</template>

<script>
    import Search from './Search.vue';

    export default {
        name: "TopicNavbar",
        components: {
            Search
        }
    }
</script>

<style scoped>
  nav li:hover,
  nav li.router-link-active,
  nav li.router-link-exact-active {
    text-decoration: underline;
    text-align: center;
    cursor: pointer;

  }
</style>
