<template>
  <div>
    <div class="articles-container">
      <div class="article-item-container" v-for="article in articles" :key="article.id">
        <ArticleItem :article="article"/>
        <div class="buttons">
          <button @click="editArticle(article.id)">Edit</button>
          <button @click="deleteArticle(article.id)">Delete</button>
        </div>
      </div>
    </div>
    <!-- <button class="new-article-button" @click="createNewArticle">New Article</button> -->
    <router-link to="/cmsArticles/add" class="button">Add article</router-link>
  </div>
</template>

<script>
import ArticleItem from '@/components/ArticleItem.vue';
import axios from 'axios';

export default {
  name: 'ArticlesView',
  components: {
    ArticleItem
  },
  data() {
    return {
      articles: []
    };
  },
  created() {
    this.fetchArticles();
  },
  methods: {
    async fetchArticles() {
      try {
        const response = await axios.get('http://localhost:8080/myApp/articles/all');
        this.articles = response.data;
      } catch (error) {
        console.error('Error fetching articles:', error);
      }
    },
    editArticle(id) {
      if (id) {
        this.$router.push(`/articles/${id}/update`);
      } else {
        console.error('Edit article: id is undefined');
      }
    },
    async deleteArticle(id) {
      if (id) {
        try {
          await axios.delete(`http://localhost:8080/myApp/articles/${id}/delete`);
          this.fetchArticles();
        } catch (error) {
          console.error('Error deleting article:', error);
        }
      } else {
        console.error('Delete article: id is undefined');
      }
    },
    createNewArticle() {
      this.$router.push('/cmsArticles/add');
    }
  }
};
</script>

<style>
/* General styles for articles container */
.articles-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.article-item-container {
    flex: 1 1 calc(33.333% - 40px);
    box-sizing: border-box;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.article-item-container:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}

.buttons button {
    padding: 10px 15px;
    font-size: 1rem;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.buttons button:first-child {
    background-color: #007bff;
}

.buttons button:first-child:hover {
    background-color: #0056b3;
}

.buttons button:last-child {
    background-color: #dc3545;
}

.buttons button:last-child:hover {
    background-color: #c82333;
}

/* Styles for add article button */
.button {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    font-size: 1rem;
    color: #fff;
    background-color: #28a745;
    border: none;
    border-radius: 4px;
    text-align: center;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.button:hover {
    background-color: #218838;
}

@media (max-width: 768px) {
    .article-item-container {
        flex: 1 1 calc(50% - 20px);
    }
}

@media (max-width: 480px) {
    .article-item-container {
        flex: 1 1 100%;
    }
}
</style>
