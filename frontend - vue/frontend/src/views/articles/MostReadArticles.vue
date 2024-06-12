<!-- src/views/AllArticles.vue -->
<template>
    <div>
      <div class="articles-container">
        <ArticleItem v-for="article in articles" :key="article.id" :article="article" />
      </div>
    </div>
  </template>
  
  <script>
  import ArticleItem from '@/components/ArticleItem.vue'
  import axios from 'axios'
  
  export default {
    name: 'MostReadArticles',
    components: {
      ArticleItem
    },
    data() {
      return {
        articles: []
      }
    },
    created() {
      this.fetchArticles()
    },
    methods: {
      async fetchArticles() {
        try {
          const response = await axios.get('http://localhost:8080/myApp/articles/mostRead')
          this.articles = response.data
        } catch (error) {
          console.error('Error fetching articles:', error)
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
  