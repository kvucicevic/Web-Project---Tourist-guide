import Vue from 'vue'
import VueRouter from 'vue-router'
import AllArticles from '../views/articles/AllArticles.vue'
import ArticleItemView from '../views/articles/ArticleItemView.vue'
import MostReadArticles from '../views/articles/MostReadArticles.vue'
import ArticlesByActivity from '../views/articles/ArticlesByActivity.vue'
import ActivityView from '../views/articles/ActivityView.vue'
import DestinationsView from '../views/cms/DestinationsView.vue'
import EditDestination from '../views/cms/EditDestinationView.vue'
import ArticlesView from '../views/cms/ArticlesView.vue'
import EditArticle from '../views/cms/EditArticlesView.vue'
import AddArticle from '../views/cms/NewArticleView.vue'
import UsersView from '../views/cms/UsersView.vue'
import EditUser from '../views/cms/EditUserView.vue'
import AddUser from '../views/cms/NewUserView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'AllArticles',
    component: AllArticles
  },
  {
    path: '/articles/:id',
    name: 'ArticleItemView',
    component: ArticleItemView,
    props: true
  },
  {
    path: '/articles/mostRead',
    name: 'MostReadArticles',
    component: MostReadArticles,
    props: true
  },
  {
    path: '/articles/byAtivity',
    name: 'ArticlesByActivity',
    component: ArticlesByActivity,
    props: true
  },
  {
    path: '/activity/:id',
    name: 'ActivityView',
    component: ActivityView,
    props: true
  },
  {
    path: '/destinations/all',
    name: 'DestinationsView',
    component: DestinationsView,
    props: true
  },
  { path: '/destinations/add', component: EditDestination },
  { path: '/destinations/:id/update', component: EditDestination },
  {
    path: '/cmsArticles/all',
    name: 'ArticlesView',
    component: ArticlesView,
    props: true
  },
  {
    path: '/cmsArticles/add',
    name: 'AddArticle',
    component: AddArticle,
  },
  {
    path: '/articles/:id/update',
    name: 'EditArticle',
    component: EditArticle,
    props: true,
  },
  {
    path: '/users/all',
    name: 'UsersView',
    component: UsersView,
    props: true,
  },
  {
    path: '/users/:id/update',
    name: 'EditUser',
    component: EditUser,
    props: true,
  },
  {
    path: '/users/add',
    name: 'AddUser',
    component: AddUser,
    props: true,
  },
  
//   {
//     path: '/subjects',
//     name: 'AllSubjects',
//     meta: {
//       authRequired: true,
//     },
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/AllSubjects.vue')
//   },
//   {
//     path: '/subjects/:id',
//     name: 'single-subject',
//     meta: {
//       authRequired: true,
//     },
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/SingleSubjectPage.vue')
//   },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'LoginPage'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'LoginPage'});
      return;
    }
  }

  next();
});

export default router