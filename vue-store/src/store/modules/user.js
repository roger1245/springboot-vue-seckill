export default {
  state: {
    user: "", // 登录的用户
    showLogin: false, // 用于控制是否显示登录组件
    token: "", //所有与用户相关的接口都需要在header中携带Bearer
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    getShowLogin(state) {
      return state.showLogin;
    },
    getToken(state) {
      return state.token;
    },
  },
  mutations: {
    setUser(state, data) {
      state.user = data;
    },
    setShowLogin(state, data) {
      state.showLogin = data;
    },
    setToken(state, data) {
      state.token = data;
    },
  },
  actions: {
    setUser({ commit }, data) {
      commit("setUser", data);
    },
    setShowLogin({ commit }, data) {
      commit("setShowLogin", data);
    },
    setToken({ commit }, data) {
      commit("setToken", data);
    },
  },
};
