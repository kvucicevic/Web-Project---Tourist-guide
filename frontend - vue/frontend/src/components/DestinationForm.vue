<!-- src/components/DestinationForm.vue -->
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
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .destination-form h2 {
    margin-bottom: 20px;
  }
  .destination-form form {
    display: flex;
    flex-direction: column;
  }
  .destination-form label {
    margin-bottom: 5px;
  }
  .destination-form input, .destination-form textarea {
    margin-bottom: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .destination-form button {
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  .destination-form button:hover {
    background-color: #0056b3;
  }
  </style>
  