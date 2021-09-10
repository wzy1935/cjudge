import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    local : {
      standard: '',
      test: '',
      generator: ''
    },
    problem : {
      standard: '', //this property only records its id, not its code.
      test: '',
      generator: ''
    },
  },
  mutations: {
    setLocalStandard(state, code) {
      state.local.standard = code;
    },
    setLocalTest(state, code) {
      state.local.test = code;
    },
    setLocalGenerator(state, code) {
      state.local.generator = code;
    },
    setProblemStandard(state, id) {
      state.problem.standard = id;
    },
    setProblemTest(state, code) {
      state.problem.test = code;
    },
    setProblemGenerator(state, code) {
      state.problem.generator = code;
    },

  },
  actions: {
  },
  modules: {
  }
})
