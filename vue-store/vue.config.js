module.exports = {
  publicPath: "./",
  devServer: {
    open: true,
    proxy: {
      "/": {
        // target: "http://localhost:9097", // 本地后端地址
        target: 'http://124.222.90.31:9097', // 线上后端地址
        changeOrigin: true, //允许跨域
      },
    },
  },
};
