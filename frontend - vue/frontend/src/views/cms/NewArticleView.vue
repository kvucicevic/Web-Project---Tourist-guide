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
  }

};
</script>

<style scoped>
/* General styles for add article form */
.add-article-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.add-article-form h2 {
  margin-bottom: 20px;
  font-size: 1.5rem;
  color: #333;
  text-align: center;
}

.add-article-form form {
  display: flex;
  flex-direction: column;
}

.add-article-form form div {
  margin-bottom: 15px;
}

.add-article-form label {
  margin-bottom: 5px;
  font-size: 1rem;
  color: #666;
}

.add-article-form input[type="text"],
.add-article-form input[type="date"],
.add-article-form input[type="number"],
.add-article-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
}

.add-article-form input[type="text"]:focus,
.add-article-form input[type="date"]:focus,
.add-article-form input[type="number"]:focus,
.add-article-form textarea:focus {
  border-color: #007bff;
  outline: none;
}

.add-article-form button {
  padding: 10px 15px;
  font-size: 1rem;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.add-article-form button:hover {
  background-color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
  .add-article-form {
      padding: 15px;
  }

  .add-article-form h2 {
      font-size: 1.25rem;
  }

  .add-article-form label {
      font-size: 0.875rem;
  }

  .add-article-form input[type="text"],
  .add-article-form input[type="date"],
  .add-article-form input[type="number"],
  .add-article-form textarea {
      font-size: 0.875rem;
  }

  .add-article-form button {
      font-size: 0.875rem;
  }
}
</style>
