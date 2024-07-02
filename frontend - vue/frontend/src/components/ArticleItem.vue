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
        this.$router.push(`/articles/${this.article.id}`)
      }
    }
  }
  </script>
  
<style>
.article-item {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin: 10px 0;
  transition: box-shadow 0.3s ease-in-out, transform 0.3s ease-in-out;
  cursor: pointer;
  background-color: #fff;
}

.article-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
}

.article-item h2 {
  margin: 0 0 10px;
  font-size: 1.5rem;
  color: #333;
}

.article-item p {
  margin: 0 0 15px;
  font-size: 1rem;
  color: #666;
  line-height: 1.5;
}

.article-item span {
  font-size: 0.875rem;
  color: #999;
}

/* Responsive styles */
@media (max-width: 768px) {
  .article-item {
      padding: 15px;
  }

  .article-item h2 {
      font-size: 1.25rem;
  }

  .article-item p {
      font-size: 0.875rem;
  }

  .article-item span {
      font-size: 0.75rem;
  }
}
</style>
  