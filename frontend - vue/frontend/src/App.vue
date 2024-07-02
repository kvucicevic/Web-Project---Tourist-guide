<template>
  <div class="container">
    <DynamicNavBar/>
    <router-view/>
  </div>
</template>

<script>

import DynamicNavBar from "./components/DynamicNavBar.vue";

export default {
  name: 'App',
  components: {
    DynamicNavBar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
