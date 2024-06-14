<template>
  <div class="add-article-form">
    <h2>New Article</h2>
    <form @submit.prevent="handleFormSubmit">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="article.title" required />
      </div>
      <div>
        <label for="date">Date:</label>
        <input type="date" v-model="article.date" required />
      </div>
      <div>
        <label for="text">Text:</label>
        <textarea v-model="article.text" required></textarea>
      </div>
      <div>
        <label for="destinationId">Destination ID:</label>
        <input type="number" v-model="article.destinationId" required />
      </div>
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddArticle',
  data() {
    console.log('2 usao u data')
    return {
      article: {
        title: '',
        date: '',
        text: '',
        visitNo: 0,
        destinationId: null
      }
    };
  },
  methods: {
  
    handleFormSubmit() {
      try {
        axios.post('http://localhost:8080/myApp/articles/add', this.article);
        this.$router.push('/cmsArticles/all');
      } catch (error) {
        console.error('Error creating article:', error);
      }
    }
  },
  created() {
    console.log("created")
  },
  mounted() {
    console.log("mounted")
  }

};
</script>

<style scoped>

</style>
