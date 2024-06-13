<!-- src/components/DestinationItem.vue -->
<template>
    <div class="destination-item" @click="goToDestination">
      <h2>{{ destination.name }}</h2>
      <p>{{ destination.description }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'DestinationItem',
    props: {
      destination: Object
    },
    methods: {
      goToDestination() {
        this.$router.push(`/destinations/${this.destination.id}`)
      }
    }
  }
  </script>
  
  <style>
  .destination-item {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
  }
  .destination-item h2 {
    margin: 0;
  }
  .destination-item p {
    margin: 10px 0;
  }
  </style>
  