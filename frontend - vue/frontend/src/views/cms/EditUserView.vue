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
.edit-user-form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.edit-user-form h2 {
    margin-bottom: 20px;
    font-size: 1.5rem;
    color: #333;
    text-align: center;
}

.edit-user-form form {
    display: flex;
    flex-direction: column;
}

.edit-user-form form div {
    margin-bottom: 15px;
}

.edit-user-form label {
    margin-bottom: 5px;
    font-size: 1rem;
    color: #666;
}

.edit-user-form input[type="text"],
.edit-user-form input[type="date"],
.edit-user-form input[type="boolean"],
.edit-user-form input[type="number"],
.edit-user-form textarea {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color 0.3s ease-in-out;
}

.edit-user-form input[type="text"]:focus,
.edit-user-form input[type="date"]:focus,
.edit-user-form input[type="boolean"]:focus,
.edit-user-form input[type="number"]:focus,
.edit-user-form textarea:focus {
    border-color: #007bff;
    outline: none;
}

.edit-user-form button {
    padding: 10px 15px;
    font-size: 1rem;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.edit-user-form button:hover {
    background-color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
    .edit-user-form {
        padding: 15px;
    }

    .edit-user-form h2 {
        font-size: 1.25rem;
    }

    .edit-user-form label {
        font-size: 0.875rem;
    }

    .edit-user-form input[type="text"],
    .edit-user-form input[type="date"],
    .edit-user-form input[type="boolean"],
    .edit-user-form input[type="number"],
    .edit-user-form textarea {
        font-size: 0.875rem;
    }

    .edit-user-form button {
        font-size: 0.875rem;
    }
}
</style>
  