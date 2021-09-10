import Vue from 'vue'
import axios from '../plugins/axios'
import VueRouter from 'vue-router'


import RepositoryView from '../views/RepositoryView.vue'
import ProblemAddView from '../views/ProblemAddView.vue'
import HomeView from '../views/HomeView.vue'
import LocalView from '../views/LocalView.vue'
import LocalResultView from '../views/LocalResultView.vue'
import LoginView from '../views/LoginView.vue'
import UserView from '../views/UserView.vue'
import ProblemView from '../views/ProblemView.vue'
import AnswerAddView from '../views/AnswerAddView.vue'
import JudgeView from '../views/JudgeView.vue'
import JudgeAnswer from '../components/judge/JudgeAnswer.vue'
import JudgeCase from '../components/judge/JudgeCase.vue'
import JudgeTest from '../components/judge/JudgeTest.vue'
import JudgeResultView from '../views/JudgeResultView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: HomeView
  },
  {
    path: '/repository',
    name: 'repository',
    component: RepositoryView
  },
  {
    path: '/repository/add',
    component: ProblemAddView
  },
  {
    path: '/login',
    component: LoginView
  },
  {
    path: '/user',
    name: 'user',
    component: UserView
  },
  {
    path: '/local',
    component: LocalView
  },
  {
    path: '/local/result/:amount/:time',
    name: 'localJudge',
    component: LocalResultView
  },
  {
    path: '/problem/:problemId',
    name: 'problem',
    component: ProblemView
  },
  {
    path: '/problem/:problemId/addAnswer',
    name: 'answerAdd',
    component: AnswerAddView
  },
  {
    path: '/judge/:problemId/',
    name: 'judge',
    component: JudgeView,
    children : [
      {
        path: 'answer',
        name: 'judgeAnswer',
        component: JudgeAnswer
      },
      {
        path: 'case',
        component: JudgeCase
      },
      {
        path: 'test',
        component: JudgeTest
      },
    ]
  },
  {
    path: '/judge/:problemId/result/:amount/:time',
    component: JudgeResultView
  },
  
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (from.fullPath != "" && (to.name == 'repository' || to.name == 'user')) {
    axios.get('./api/getUser/true').then(() => {next();}).catch(() => {next();})
  } else {
    next();
  }
  
  
})

export default router
