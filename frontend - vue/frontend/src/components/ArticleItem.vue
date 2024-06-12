<!-- src/components/ArticleItem.vue -->
<template>
    <div class="article-item" @click="goToArticle">
      <h2>{{ article.title }}</h2>
      <p>{{ article.text }}</p>
      <span>{{ formattedDate }}</span>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ArticleItem',
    props: {
      article: Object
    },
    computed: {
      formattedDate() {
        const date = new Date(this.article.date)
        return date.toLocaleDateString()
      }
    },
    methods: {
      goToArticle() {
        this.$router.push(`/article/${this.article.id}`)
      }
    }
  }
  </script>
  
  <style>
  .article-item {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
  }
  .article-item h2 {
    margin: 0;
  }
  .article-item p {
    margin: 10px 0;
  }
  .article-item span {
    font-size: 0.8em;
    color: #888;
  }
  </style>
  