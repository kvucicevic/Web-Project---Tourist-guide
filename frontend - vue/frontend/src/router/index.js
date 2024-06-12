import Vue from 'vue'
import VueRouter from 'vue-router'
import AllArticles from '../views/AllArticles.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'AllArticles',
    component: AllArticles
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