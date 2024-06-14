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
      console.log('1 ovde sam usao')
      this.$router.push('/articles/create-new');
    }
  }
};
</script>

<style>
.articles-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
