const NODE_ENV = process.env.NODE_ENV || 'development';
const webpack = require("webpack");
module.exports ={
  context: __dirname + "/src/app/js/navigator",
  entry: "./nav",
  output: {
    path: __dirname + "/src/app/js/build/navigator/",
    filename: "navigator.js",
    library : "home"
  },

  module: {
    loaders: [
      {
        test: /\.html$/,
        loader: 'babel-loader?presets[]=es2015!es6-template-string-loader?context=data'
      },
      {
        test: /\.js$/,
        loader: 'babel-loader'
      },
      {
        test: /\.js/,
        loader: 'imports-loader?define=>false'
      }
    ]
  },
  plugins :[
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery",
      "window.jQuery": "jquery"
    }),
    new webpack.DefinePlugin({
      NODE_ENV : JSON.stringify(NODE_ENV)
    })
  ],
  watch : NODE_ENV == 'development',
  watchOptions: {
    aggregateTimeout: 300,
    poll: 1000
  },
  devtool: "source-map"
};
