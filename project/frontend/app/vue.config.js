var path = require('path')

module.exports =
{
    // 基本路径
    publicPath: './',

    devServer:
    {
        // proxy: '此处应为后端服务器地址',
    },

    css:
    {
        loaderOptions:
        {
            stylus:
            {
                define:
                {
                    _colorset: path.resolve('src/assets/stylesheets/colorset/index.styl'),
                },
            },
        },
    },

    chainWebpack: config => {
        // pug loader //
        config.module
        .rule('pug')
        .test(/\.pug$/)
        .use('pug-html-loader')
        .loader('pug-html-loader')
        .end()
    },
}
