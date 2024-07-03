<template>
  <div>
    <h1>Activity: {{ activityType }}</h1>
    <div class="articles-section" v-if="articles.length">
      <h2>Articles</h2>
      <div class="article" v-for="article in paginatedArticles" :key="article.id">
        <router-link :to="{ name: 'ArticleItemView', params: { id: article.id } }">
          <h3>{{ article.title }}</h3>
        </router-link>
        <p><strong>Date:</strong> {{ formatDate(article.date) }}</p>
        <p>{{ article.text }}</p>
      </div>
      <div class="pagination-controls">
        <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
      </div>
    </div>
    <div v-else>
      <p>Loading articles...</p>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'ActivityView',
  props: ['activityType'],
  data() {
    return {
      articles: [],
      currentPage: 1,
      articlesPerPage: 5,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.articles.length / this.articlesPerPage);
    },
    paginatedArticles() {
      const start = (this.currentPage - 1) * this.articlesPerPage;
      const end = start + this.articlesPerPage;
      return this.articles.slice(start, end);
    },
  },
  created() {
    this.fetchActivityArticles();
  },
  methods: {
    async fetchActivityArticles() {
      try {
        // Fetch articles associated with the activity type
        const response = await axios.get('http://localhost:8080/myApp/articles/articlesByActivity', {
          params: { activityType: this.activityType },
        });
        console.log('Response:', response.data);
        this.articles = response.data;
      } catch (error) {
        console.error('Error fetching activity articles:', error);
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
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
    },
  },
};
</script>

<style>
.articles-section {
  margin-top: 20px;
}
.article {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.article h3 {
  margin: 0;
}
.article p {
  margin: 5px 0;
}
</style>
  