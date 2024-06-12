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
    <div class="activities-section" v-if="activities.length">
      <h2>Activities</h2>
      <ul>
        <!-- <li v-for="activity in activities" :key="activity.id">
          <router-link :to="{ name: 'ActivityView', params: { id: activity.id } }">{{ activity.name }}</router-link>
        </li> -->
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
  import axios from 'axios'
  
  export default {
    name: 'ArticleItemView',
    // components: {
    //   Navbar
    // },
    data() {
      return {
      article: null,
      comments: [],
      destinationName: '', // Initialize destinationName
      activities: []
      }
    },
    props: ['id'],
    created() {
      this.fetchArticle()
      this.fetchComments()
    },
    methods: {
      async fetchArticle() {
        try {
            const response = await axios.get(`http://localhost:8080/myApp/articles/${this.id}`)
            this.article = response.data
            if (this.article && this.article.destinationId) {
              this.fetchDestinationName(this.article.destinationId)
              this.fetchActivities(this.article.destinationId)
            }
        } catch (error) {
            console.error('Error fetching article:', error)
        }
      },
      async fetchComments() {
        try {
          console.log('Fetching comments');
          const response = await axios.get(`http://localhost:8080/myApp/articles/${this.id}/allComments`)
          this.comments = response.data
        } catch (error) {
          console.error('Error fetching comments:', error)
        }
      },
      async fetchDestinationName(destinationId) {
        try {
          console.log('Fetching destination name for ID:', destinationId);
          const response = await axios.get(`http://localhost:8080/myApp/destinations/${destinationId}`)
          this.destinationName = response.data.name
        } catch (error) {
          console.error('Error fetching destination name:', error)
        }
      },
      async fetchActivities(destinationId) {
        try {
          console.log('Fetching activities for destination ID:', destinationId);
          const response = await axios.get(`http://localhost:8080/myApp/destinations/${destinationId}/allActivities`)
          this.activities = response.data
        } catch (error) {
          console.error('Error fetching activities:', error)
        }
      }
    },
    computed: {
      formattedDate() {
        if (this.article && this.article.date) {
          const date = new Date(this.article.date)
          return date.toLocaleDateString()
        }
        return ''
      }
    }
  }
  </script>
  
<style>
.article-detail {
  padding: 20px;
}
.article-detail h1 {
  margin-bottom: 20px;
}
.article-detail p {
  margin: 5px 0;
}
.comments-section {
  margin-top: 40px;
}
.comment {
  margin-bottom: 20px;
}
</style>
  