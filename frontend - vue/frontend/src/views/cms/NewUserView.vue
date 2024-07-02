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
.add-user-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.add-user-form h2 {
  margin-bottom: 20px;
  font-size: 1.5rem;
  color: #333;
  text-align: center;
}

.add-user-form form {
  display: flex;
  flex-direction: column;
}

.add-user-form form div {
  margin-bottom: 15px;
}

.add-user-form label {
  margin-bottom: 5px;
  font-size: 1rem;
  color: #666;
}

.add-user-form input[type="text"],
.add-user-form input[type="boolean"],
.add-user-form input[type="number"],
.add-user-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
}

.add-user-form input[type="text"]:focus,
.add-user-form input[type="boolean"]:focus,
.add-user-form input[type="number"]:focus,
.add-user-form textarea:focus {
  border-color: #007bff;
  outline: none;
}

.add-user-form button {
  padding: 10px 15px;
  font-size: 1rem;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.add-user-form button:hover {
  background-color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
  .add-user-form {
      padding: 15px;
  }

  .add-user-form h2 {
      font-size: 1.25rem;
  }

  .add-user-form label {
      font-size: 0.875rem;
  }

  .add-user-form input[type="text"],
  .add-user-form input[type="boolean"],
  .add-user-form input[type="number"],
  .add-user-form textarea {
      font-size: 0.875rem;
  }

  .add-user-form button {
      font-size: 0.875rem;
  }
}

</style>
  