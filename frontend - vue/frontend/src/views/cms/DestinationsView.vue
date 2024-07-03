<template>
  <div>
    <div class="destinations-container">
      <div class="destination-item-container" v-for="destination in paginatedDestinations" :key="destination.id">
        <DestinationItem :destination="destination"/>
        <div class="buttons">
          <button @click="editDestination(destination.id)">Edit</button>
          <button @click="deleteDestination(destination.id)">Delete</button>
        </div>
      </div>
    </div>
    <div class="pagination-controls">
      <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>
    <button class="new-destination-button" @click="createNewDestination">New Destination</button>
  </div>
</template>

<script>
import DestinationItem from '@/components/DestinationItem.vue'
import axios from 'axios'

export default {
  name: 'AllDestinations',
  components: {
    DestinationItem
  },
  data() {
    return {
      destinations: [],
      currentPage: 1,
      destinationsPerPage: 5,
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.destinations.length / this.destinationsPerPage);
    },
    paginatedDestinations() {
      const start = (this.currentPage - 1) * this.destinationsPerPage;
      return this.destinations.slice(start, start + this.destinationsPerPage);
    }
  },
  created() {
    this.fetchDestinations()
  },
  methods: {
    async fetchDestinations() {
      try {
        const response = await axios.get('http://localhost:8080/myApp/destinations/all')
        this.destinations = response.data
      } catch (error) {
        console.error('Error fetching destinations:', error)
      }
    },
    editDestination(id) {
      if (id) {
        this.$router.push(`/destinations/${id}/update`)
      } else {
        console.error("Edit destination: id is undefined")
      }
    },
    async deleteDestination(id) {
      if (id) {
        try {
          await axios.delete(`http://localhost:8080/myApp/destinations/${id}`)
          this.fetchDestinations()
        } catch (error) {
          console.error('Error deleting destination:', error)
        }
      } else {
        console.error("Delete destination: id is undefined")
      }
    },
    createNewDestination() {
      this.$router.push('/destinations/add')
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
}
</script>

<style>
.destinations-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.destination-item-container {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.buttons {
  display: flex;
  gap: 5px;
}
.new-destination-button {
  margin-top: 20px;
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.new-destination-button:hover {
  background-color: #0056b3;
}
</style>
