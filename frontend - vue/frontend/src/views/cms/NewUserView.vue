<template>
    <div class="add-user-form">
      <h2>New user</h2>
      <form @submit.prevent="handleFormSubmit">
        <div>
          <label for="firstName">First name:</label>
          <input type="text" v-model="user.firstName" required />
        </div>
        <div>
          <label for="lastName">Last name:</label>
          <input type="text" v-model="user.lastName" required />
        </div>
        <div>
          <label for="email">Email:</label>
          <textarea v-model="user.email" required></textarea>
        </div>
        <div>
          <label for="active">Active:</label>
          <input type="boolean" v-model="user.active" required />
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="text" v-model="user.password" required />
        </div>
        <div>
          <label for="roleId">Role:</label>
          <input type="number" v-model="user.roleId" required />
        </div>
        <button type="submit">Create</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'AddUser',
    data() {
      return {
        user: {
          firstName: '',
          lastName: '',
          email: '',
          active: false,
          password: '',
          roleId: ''
        }
      };
    },
    methods: {
    
      handleFormSubmit() {
        try {
          axios.post('http://localhost:8080/myApp/users/add', this.user);
          this.$router.push('/users/all');
        } catch (error) {
          console.error('Error creating user:', error);
        }
      }
    }
  
  };
  </script>
  
  <style scoped>
  
  </style>
  