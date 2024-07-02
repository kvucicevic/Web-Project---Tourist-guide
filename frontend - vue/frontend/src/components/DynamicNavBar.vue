<template>
    <div>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Web programiranje</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <template v-if="isLoggedIn">
                <li class="nav-item">
                  <router-link :to="{ name: 'DestinationsView' }" class="nav-link" :class="{ active: isActive('DestinationsView') }">Destinations</router-link>
                </li>
                <li class="nav-item">
                  <router-link :to="{ name: 'ArticlesView' }" class="nav-link" :class="{ active: isActive('ArticlesView') }">Articles</router-link>
                </li>
                <li class="nav-item">
                  <router-link :to="{ name: 'UsersView' }" class="nav-link" :class="{ active: isActive('UsersView') }">Users</router-link>
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <router-link :to="{ name: 'AllArticles' }" class="nav-link" :class="{ active: isActive('AllArticles') }">HomePage</router-link>
                </li>
                <li class="nav-item">
                  <router-link :to="{ name: 'MostReadArticles' }" class="nav-link" :class="{ active: isActive('MostReadArticles') }">Most Read</router-link>
                </li>
                <li class="nav-item">
                  <router-link :to="{ name: 'LoginPage' }" class="nav-link" :class="{ active: isActive('LoginPage') }">Login</router-link>
                </li>
              </template>
            </ul>
            <form v-if="isLoggedIn" class="d-flex" @submit.prevent="logout">
              <button class="btn btn-outline-secondary" type="submit">Logout</button>
            </form>
          </div>
        </div>
      </nav>
    </div>
</template>
  
<script>
  export default {
    name: "DynamicNavBar",
    computed: {
      isLoggedIn() {
        return !!localStorage.getItem('jwt');
      }
    },
    methods: {
      isActive(routeName) {
        return this.$router.currentRoute.name === routeName;
      },
      logout() {
        localStorage.removeItem('jwt');
        this.$router.push({ name: 'LoginPage' });
      }
    }
  }
</script>
  
<style scoped>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.navbar {
    background-color: #f8f9fa;
    border-bottom: 1px solid #dee2e6;
}

.navbar-brand {
    font-size: 1.5rem;
    font-weight: bold;
    color: #007bff;
    text-transform: uppercase;
}

.navbar-nav .nav-item .nav-link {
    color: #555;
    padding: 10px 15px;
    transition: color 0.3s ease-in-out;
}

.navbar-nav .nav-item .nav-link.active {
    color: #007bff;
    font-weight: bold;
}

.navbar-nav .nav-item .nav-link:hover {
    color: #007bff;
}

.navbar-toggler {
    border: none;
    outline: none;
}

.navbar-toggler-icon {
    width: 24px;
    height: 24px;
    background-color: #007bff;
}

.btn-outline-secondary {
    color: #007bff;
    border-color: #007bff;
}

.btn-outline-secondary:hover {
    background-color: #007bff;
    color: #fff;
}

/* Responsive styles */
@media (max-width: 992px) {
    .navbar-nav {
        text-align: center;
    }
    .navbar-nav .nav-item {
        margin-bottom: 10px;
    }
    .navbar-nav .nav-item .nav-link {
        padding: 10px 0;
    }
}
</style>
  