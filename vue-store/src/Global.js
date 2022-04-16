exports.install = function (Vue) {
  Vue.prototype.$target = "http://124.222.90.31:9097/"; // 线上后端地址
  // Vue.prototype.$target = "http://localhost:9097/"; // 本地后端地址
  // 封装提示成功的弹出框
  Vue.prototype.notifySucceed = function (msg) {
    this.$notify({
      title: "成功",
      message: msg,
      type: "success",
      offset: 100,
    });
  };
  // 封装提示失败的弹出框
  Vue.prototype.notifyError = function (msg) {
    this.$notify.error({
      title: "错误",
      message: msg,
      offset: 100,
    });
  };
};
