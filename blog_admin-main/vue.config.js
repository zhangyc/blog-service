module.exports = {
  productionSourceMap: false,
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
    historyApiFallback: true,
    allowedHosts: "all"
    //disableHostCheck: true
  },
  chainWebpack: config => {
    config.resolve.alias.set("@", resolve("src"));
  },

};

const path = require("path");
function resolve(dir) {
  return path.join(__dirname, dir);
}
