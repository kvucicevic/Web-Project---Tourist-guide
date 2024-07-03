<template>
  <div>
    <div class="articles-container">
      <ArticleItem v-for="article in paginatedArticles" :key="article.id" :article="article" />
    </div>
    <div class="pagination-controls">
      <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>
  </div>
</template>
  
<script>
import ArticleItem from '@/components/ArticleItem.vue'
import axios from 'axios'

export default {
  name: 'AllArticles',
  components: {
    ArticleItem
  },
  data() {
    return {
      articles: [],
      currentPage: 1,
      articlesPerPage: 5,
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.articles.length / this.articlesPerPage);
    },
    paginatedArticles() {
      const start = (this.currentPage - 1) * this.articlesPerPage;
      return this.articles.slice(start, start + this.articlesPerPage);
    }
  },
  created() {
    this.fetchArticles()
  },
  methods: {
    async fetchArticles() {
      try {
        const response = await axios.get('http://localhost:8080/myApp/articles/all')
        this.articles = response.data
      } catch (error) {
        console.error('Error fetching articles:', error)
      }
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
.articles-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
  