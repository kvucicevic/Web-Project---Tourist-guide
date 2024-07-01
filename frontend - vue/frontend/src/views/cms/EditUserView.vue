<template>
    <div class="edit-user-form">
      <h2>Edit user</h2>
      <form @submit.prevent="handleFormSubmit">
        <div>
          <label for="firstName">First name:</label>
          <input type="text" v-model="user.firstName" required />
        </div>
        <div>
          <label for="lastName">Last name:</label>
          <input type="date" v-model="user.lastName" required />
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
    name: 'EditUser',
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
    created() {
      if (this.$route.params.id) {
        this.fetchuser(this.$route.params.id);
      }
    },
    methods: {
      async fetchuser(id) {
        try {
          const response = await axios.get(`http://localhost:8080/myApp/users/${id}`);
          this.user = response.data;
        } catch (error) {
          console.error('Error fetching user:', error);
        }
      },
      async handleFormSubmit() {
        try {
          const { id, ...newuser } = this.user;
          console.log(newuser)
          await axios.put(`http://localhost:8080/myApp/users/${id}/update`, newuser);
          this.$router.push('/users/all');
        } catch (error) {
          console.error('Error updating user:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  
  </style>
  