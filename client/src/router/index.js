import Vue from 'vue'
import Router from 'vue-router'
import Index from "../components/Index";
import Login from "../components/Login";
import Forum from "../components/user/Forum";
import Creation from "../components/user/Creation";
import UserInfo from "../components/user/UserInfo";
import RecentSport from "../components/user/RecentSport";
import SportRecord from "../components/user/SportRecord";
import MyCoach from "../components/user/MyCoach";
import MyUser from "../components/coach/MyUser";
import ConditionDisplay from "../components/user/ConditionDisplay";


Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        {
        path: 'forum',
        name: 'Forum',
        component: Forum,
      },
        {
          path: 'creation',
          name: 'Creation',
          component: Creation,
        },
        {
          path: 'userInfo',
          name: 'UserInfo',
          component: UserInfo,
        },
        {
          path: 'recentSport',
          name: 'RecentSport',
          component: RecentSport,
        },
        {
          path: 'sportRecord',
          name: 'SportRecord',
          component: SportRecord,
        },
        {
          path: 'myCoach',
          name: 'MyCoach',
          component: MyCoach,
        },
        {
          path: 'myUser',
          name: 'MyUser',
          component: MyUser,
        },
        {
          path:'conditionDisplay',
          name: 'ConditionDisplay',
          component: ConditionDisplay
        }
      ]
    },

    {
      path: '/login',
      name: 'Login',
      component: Login
    },
  ]
})
