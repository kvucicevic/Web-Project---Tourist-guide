<template>
  <div>
    <div class="article-detail" v-if="article">
        <h1>{{ article.title }}</h1>
        <p><strong>Date:</strong> {{ formattedDate }}</p>
        <p><strong>Text:</strong> {{ article.text }}</p>
        <p><strong>Visit No:</strong> {{ article.visitNo }}</p>
        <p><strong>Destination:</strong> {{ destinationName }}</p>
    </div>
    <div v-else>
        <p>Loading article...</p>
    </div>
    
    <div class="comments-section" v-if="comments.length">
      <h2>Comments</h2>
      <div class="comment" v-for="comment in comments" :key="comment.id">
        <p><strong>{{ comment.author }}:</strong> {{ comment.content }}</p>
      </div>
    </div>
    <div v-else>
      <p>Loading comments...</p>
    </div>

    <button @click="showCommentForm = !showCommentForm">
      {{ showCommentForm ? 'Cancel' : 'Add Comment' }}
    </button>

    <div v-if="showCommentForm" class="add-comment-form">
      <h2>Add Comment</h2>
      <form @submit.prevent="submitComment">
        <div>
          <label for="author">Author:</label>
          <input type="text" v-model="newComment.author" required>
        </div>
        <div>
          <label for="content">Comment:</label>
          <textarea v-model="newComment.content" required></textarea>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
    
    <div class="activities-section" v-if="activities.length">
      <h2>Activities</h2>
      <ul>
        <li v-for="activity in activities" :key="activity.id">
          <router-link :to="{ name: 'ActivityView', params: { activityType: activity.name } }">{{ activity.name }}</router-link>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>Loading activities...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ArticleItemView',
  data() {
    return {
      article: null,
      comments: [],
      destinationName: '',
      activities: [],
      showCommentForm: false,
      newComment: {
        articleId: '',
        author: '',
        content: ''
      }
    };
  },
  props: ['id'],
  created() {
    this.fetchArticle();
    this.fetchComments();
  },
  methods: {
    async fetchArticle() {
      try {
        const response = await axios.get(`http://localhost:8080/myApp/articles/${this.id}`);
        this.article = response.data;
        if (this.article && this.article.destinationId) {
          this.fetchDestinationName(this.article.destinationId);
          this.fetchActivities(this.article.destinationId);
        }
      } catch (error) {
        console.error('Error fetching article:', error);
      }
    },
    async fetchComments() {
      try {
        const response = await axios.get(`http://localhost:8080/myApp/articles/${this.id}/allComments`);
        this.comments = response.data;
      } catch (error) {
        console.error('Error fetching comments:', error);
      }
    },
    async fetchDestinationName(destinationId) {
      try {
        const response = await axios.get(`http://localhost:8080/myApp/destinations/${destinationId}`);
        this.destinationName = response.data.name;
      } catch (error) {
        console.error('Error fetching destination name:', error);
      }
    },
    async fetchActivities(destinationId) {
      try {
        const response = await axios.get(`http://localhost:8080/myApp/destinations/${destinationId}/allActivities`);
        this.activities = response.data;
      } catch (error) {
        console.error('Error fetching activities:', error);
      }
    },
    async submitComment() {
      try {
        this.newComment.articleId = this.article.id
        const response = await axios.post(`http://localhost:8080/myApp/articles/${this.id}/addComment`, this.newComment);
        this.comments.push(response.data);
        this.showCommentForm = false;
        this.newComment = { author: '', content: '' };
      } catch (error) {
        console.error('Error adding comment:', error);
      }
    }
  },
  computed: {
    formattedDate() {
      if (this.article && this.article.date) {
        const date = new Date(this.article.date);
        return date.toLocaleDateString();
      }
      return '';
    }
  }
};
</script>

  
<style>
.article-detail {
    max-width: 800px;
    margin: 0 auto 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.article-detail h1 {
    margin-bottom: 20px;
    font-size: 2rem;
    color: #333;
}

.article-detail p {
    margin-bottom: 10px;
    font-size: 1rem;
    color: #666;
    line-height: 1.5;
}

.comments-section {
    max-width: 800px;
    margin: 0 auto 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #f9f9f9;
}

.comments-section h2 {
    margin-bottom: 20px;
    font-size: 1.5rem;
    color: #333;
}

.comment {
    margin-bottom: 15px;
}

.comment p {
    font-size: 1rem;
    color: #555;
}

.comment strong {
    color: #333;
}

.add-comment-form {
    max-width: 800px;
    margin: 0 auto 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
}

.add-comment-form h2 {
    margin-bottom: 20px;
    font-size: 1.5rem;
    color: #333;
}

.add-comment-form form {
    display: flex;
    flex-direction: column;
}

.add-comment-form form div {
    margin-bottom: 15px;
}

.add-comment-form label {
    margin-bottom: 5px;
    font-size: 1rem;
    color: #666;
}

.add-comment-form input[type="text"],
.add-comment-form textarea {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color 0.3s ease-in-out;
}

.add-comment-form input[type="text"]:focus,
.add-comment-form textarea:focus {
    border-color: #007bff;
    outline: none;
}

.add-comment-form button {
    padding: 10px 15px;
    font-size: 1rem;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.add-comment-form button:hover {
    background-color: #0056b3;
}

.activities-section {
    max-width: 800px;
    margin: 0 auto 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #f9f9f9;
}

.activities-section h2 {
    margin-bottom: 20px;
    font-size: 1.5rem;
    color: #333;
}

.activities-section ul {
    list-style: none;
    padding: 0;
}

.activities-section li {
    margin-bottom: 10px;
}

.activities-section a {
    font-size: 1rem;
    color: #007bff;
    text-decoration: none;
    transition: color 0.3s ease-in-out;
}

.activities-section a:hover {
    color: #0056b3;
}

/* Responsive styles */
@media (max-width: 768px) {
    .article-detail, .comments-section, .add-comment-form, .activities-section {
        padding: 15px;
    }

    .article-detail h1, .comments-section h2, .add-comment-form h2, .activities-section h2 {
        font-size: 1.25rem;
    }

    .article-detail p, .comment p, .add-comment-form label, .add-comment-form input[type="text"], .add-comment-form textarea, .add-comment-form button, .activities-section a {
        font-size: 0.875rem;
    }
}

</style>
  