const webpack = require("webpack");
const path = require("path");
const ClosureCompilerPlugin = require('webpack-closure-compiler');

const TEST_PACK = false;
const PRODUCTION = false;
const CLOSURE_COMPILLER = false;
const plugins = [];

if (PRODUCTION) {
    if (CLOSURE_COMPILLER) {
        plugins.push(new ClosureCompilerPlugin({
            compiler: {
                language_in: 'ECMASCRIPT5',
                language_out: 'ECMASCRIPT5',
                compilation_level: 'ADVANCED'
            },
            concurrency: 3,
        }))
    } else {
        plugins.push(
            new webpack.DefinePlugin({
                'process.env': {
                    NODE_ENV: JSON.stringify('production')
                }
            }),
            new webpack.optimize.UglifyJsPlugin({sourceMap: true})
        )

    }
}


const moduleName = 'ui';

// build list of aliases
const aliases = {};
const compiledDir = path.resolve(__dirname, 'build/classes/kotlin/main/min') + '/';
// todo: maybe read from files list ay compiledDir
let deps = [
    'kotlin'
];
for (let dep of deps) {
    aliases[dep] = compiledDir + dep + '.js'
}

module.exports = {
    devtool: 'source-maps',
    entry: compiledDir + moduleName + ".js",
    output: {
        path: path.resolve(__dirname, "build"),
        filename: "bundle.js"
    },
    resolve: {
        alias: aliases
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: ["source-map-loader"],
                enforce: "pre"
            }
        ]
    },
    plugins: plugins
};