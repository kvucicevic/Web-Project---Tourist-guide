<template>
  <div class="edit-article-form">
    <h2>Edit Article</h2>
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
      <button type="submit">Update</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EditArticle',
  data() {
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
  created() {
    if (this.$route.params.id) {
      this.fetchArticle(this.$route.params.id);
    }
  },
  methods: {
    async fetchArticle(id) {
      try {
        const response = await axios.get(`http://localhost:8080/myApp/articles/${id}`);
        this.article = response.data;
      } catch (error) {
        console.error('Error fetching article:', error);
      }
    },
    async handleFormSubmit() {
      try {
        const { id, ...newArticle } = this.article;
        console.log(newArticle)
        await axios.put(`http://localhost:8080/myApp/articles/${id}/update`, newArticle);
        this.$router.push('/cmsArticles/all');
      } catch (error) {
        console.error('Error updating article:', error);
      }
    }
  }
};
</script>

<style scoped>

</style>
