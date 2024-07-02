<template>
  <div class="destination-form">
    <h2>{{ isEdit ? 'Edit Destination' : 'New Destination' }}</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="localDestination.name" required />
      </div>
      <div>
        <label for="description">Description:</label>
        <textarea v-model="localDestination.description" required></textarea>
      </div>
      <button type="submit">{{ isEdit ? 'Update' : 'Create' }}</button>
    </form>
  </div>
</template>
  
<script>
  export default {
    name: 'DestinationForm',
    props: {
      destination: Object,
      isEdit: Boolean
    },
    data() {
      return {
        localDestination: { ...this.destination }
      }
    },
    watch: {
      destination: {
        handler(newVal) {
          this.localDestination = { ...newVal }
        },
        deep: true
      }
    },
    methods: {
      handleSubmit() {
        this.$emit('submit', this.localDestination)
      }
    }
  }
</script>
  
<style>
.destination-form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.destination-form h2 {
    margin-bottom: 20px;
    font-size: 1.5rem;
    color: #333;
    text-align: center;
}

.destination-form form {
    display: flex;
    flex-direction: column;
}

.destination-form form div {
    margin-bottom: 15px;
}

.destination-form label {
    margin-bottom: 5px;
    font-size: 1rem;
    color: #666;
}

.destination-form input[type="text"],
.destination-form textarea {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color 0.3s ease-in-out;
}

.destination-form input[type="text"]:focus,
.destination-form textarea:focus {
    border-color: #007bff;
    outline: none;
}

.destination-form button {
    padding: 10px 15px;
    font-size: 1rem;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.destination-form button:hover {
    background-color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
  .destination-form {
      padding: 15px;
  }

  .destination-form h2 {
      font-size: 1.25rem;
  }

  .destination-form label {
      font-size: 0.875rem;
  }

  .destination-form input[type="text"],
  .destination-form textarea {
      font-size: 0.875rem;
  }

  .destination-form button {
      font-size: 0.875rem;
  }
}
</style>
  