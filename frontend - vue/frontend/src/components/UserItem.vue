<template>
    <div class="user-item" @click="goToUser">
      <h2>{{ user.name }} {{ user.surname }}</h2>
      <p>Email: {{ user.email }}</p>
      <p>Role: {{ user.role }}</p>
      <p>Status: <span :class="{'active': user.active, 'inactive': !user.active}">{{ user.active ? 'Active' : 'Inactive' }}</span></p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'UserItem',
    props: {
      user: Object
    },
    methods: {
      goToUser() {
        this.$router.push(`/users/${this.user.id}`)
      }
    }
  }
  </script>
  
  <style>
  .user-item {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
  }
  .user-item h2 {
    margin: 0;
  }
  .user-item p {
    margin: 10px 0;
  }
  .user-item .active {
    color: green;
  }
  .user-item .inactive {
    color: red;
  }
  </style>
  