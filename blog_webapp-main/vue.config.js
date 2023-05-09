module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        target: "http://117.50.189.47:8080",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  },
};
