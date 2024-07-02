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
/* General styles for edit article form */
.edit-article-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.edit-article-form h2 {
  margin-bottom: 20px;
  font-size: 1.5rem;
  color: #333;
  text-align: center;
}

.edit-article-form form {
  display: flex;
  flex-direction: column;
}

.edit-article-form form div {
  margin-bottom: 15px;
}

.edit-article-form label {
  margin-bottom: 5px;
  font-size: 1rem;
  color: #666;
}

.edit-article-form input[type="text"],
.edit-article-form input[type="date"],
.edit-article-form input[type="number"],
.edit-article-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
}

.edit-article-form input[type="text"]:focus,
.edit-article-form input[type="date"]:focus,
.edit-article-form input[type="number"]:focus,
.edit-article-form textarea:focus {
  border-color: #007bff;
  outline: none;
}

.edit-article-form button {
  padding: 10px 15px;
  font-size: 1rem;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.edit-article-form button:hover {
  background-color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
  .edit-article-form {
      padding: 15px;
  }

  .edit-article-form h2 {
      font-size: 1.25rem;
  }

  .edit-article-form label {
      font-size: 0.875rem;
  }

  .edit-article-form input[type="text"],
  .edit-article-form input[type="date"],
  .edit-article-form input[type="number"],
  .edit-article-form textarea {
      font-size: 0.875rem;
  }

  .edit-article-form button {
      font-size: 0.875rem;
  }
}

</style>
