<template>
    <div>
      <div class="users-container">
        <div class="user-item-container" v-for="user in users" :key="user.id">
          <UserItem :user="user"/>
          <div class="buttons">
            <button @click="editUser(user.id)">Edit</button>
            <button @click="deleteUser(user.id)">Delete</button>
          </div>
        </div>
      </div>
      <!-- <button class="new-user-button" @click="createNewuser">New user</button> -->
      <router-link to="/users/add" class="button">Add user</router-link>
    </div>
  </template>
  
  <script>
  import UserItem from '@/components/UserItem.vue';
  import axios from 'axios';
  
  export default {
    name: 'UsersView',
    components: {
      UserItem
    },
    data() {
      return {
        users: []
      };
    },
    created() {
      this.fetchUsers();
    },
    methods: {
      async fetchUsers() {
        try {
          const response = await axios.get('http://localhost:8080/myApp/users/all');
          this.users = response.data;
        } catch (error) {
          console.error('Error fetching users:', error);
        }
      },
      editUser(id) {
        if (id) {
          this.$router.push(`/users/${id}/update`);
        } else {
          console.error('Edit user: id is undefined');
        }
      },
      async deleteUser(id) {
        if (id) {
          try {
            await axios.delete(`http://localhost:8080/myApp/users/${id}/delete`);
            this.fetchUsers();
          } catch (error) {
            console.error('Error deleting user:', error);
          }
        } else {
          console.error('Delete user: id is undefined');
        }
      },
      createNewuser() {
        this.$router.push('/users/add');
      }
    }
  };
  </script>
  
  <style>
  .users-container {
    padding: 20px;
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  </style>
  