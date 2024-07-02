<template>
  <div>
    <div class="users-container">
      <div class="user-item-container" v-for="user in users" :key="user.id">
        <UserItem :user="user" />
        <div class="buttons">
          <button @click="editUser(user.id)">Edit</button>
          <button @click="deleteUser(user.id)">Delete</button>
        </div>
      </div>
    </div>
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
    }
  },
  beforeRouteEnter(to, from, next) {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      next();
    } else {
      next({ name: 'LoginPage' });
    }
  }
};
</script>

<style>
/* General styles for users container */
.users-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.user-item-container {
  flex: 1 1 calc(33.333% - 40px);
  box-sizing: border-box;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.user-item-container:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.buttons button {
  padding: 10px 15px;
  font-size: 1rem;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.buttons button:first-child {
  background-color: #007bff;
}

.buttons button:first-child:hover {
  background-color: #0056b3;
}

.buttons button:last-child {
  background-color: #dc3545;
}

.buttons button:last-child:hover {
  background-color: #c82333;
}

/* Styles for add user button */
.button {
  display: inline-block;
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 1rem;
  color: #fff;
  background-color: #28a745;
  border: none;
  border-radius: 4px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.button:hover {
  background-color: #218838;
}

/* Responsive styles */
@media (max-width: 768px) {
    .user-item-container {
        flex: 1 1 calc(50% - 20px);
    }
}

@media (max-width: 480px) {
    .user-item-container {
        flex: 1 1 100%;
    }
}

</style>
