<template>
    <div>
      <DestinationForm 
        :destination="destination" 
        :isEdit="isEdit" 
        @submit="handleFormSubmit" 
      />
    </div>
  </template>
  
  <script>
  import DestinationForm from '@/components/DestinationForm.vue'
  import axios from 'axios'
  
  export default {
    name: 'EditDestination',
    components: {
      DestinationForm
    },
    data() {
      return {
        destination: {
          name: '',
          description: ''
        },
        isEdit: false
      }
    },
    created() {
      if (this.$route.params.id) {
        console.log('usao u created')
        this.isEdit = true
        this.fetchDestination(this.$route.params.id)
      }
    },
    methods: {
      async fetchDestination(id) {
        try {
          const response = await axios.get(`http://localhost:8080/myApp/destinations/${id}`)
          this.destination = response.data
        } catch (error) {
          console.error('Error fetching destination:', error)
        }
      },
      async handleFormSubmit(destination) {
      try {
        if (this.isEdit) {
          const { id, ...updatedDestination } = destination;
          await axios.put(`http://localhost:8080/myApp/destinations/${id}/update`, updatedDestination);
        } else {
          await axios.post('http://localhost:8080/myApp/destinations/add', destination);
        }
        this.$router.push('/destinations/all');
      } catch (error) {
        console.error('Error saving destination:', error);
      }
    }
    }
  }
  </script>
  