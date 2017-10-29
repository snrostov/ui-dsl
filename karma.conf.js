const path = require("path");

process.env.CHROME_BIN = require('puppeteer').executablePath();

const moduleName = 'cx.js.ui';

// build list of aliases
const aliases = {};
const compiledDir = path.resolve(__dirname, 'build/classes/kotlin/test/min') + '/';
// todo: maybe read from files list ay compiledDir
let deps = [
    'kotlin',
    'cx.system'
];

const testDeps = [].concat(deps, moduleName);
const testModuleName = moduleName + "_test";

for (let dep of testDeps) {
    aliases[dep] = compiledDir + dep + '.js'
}

const main = compiledDir + testModuleName + ".js";
const preprocessors = {};
preprocessors[main] = ['webpack', 'sourcemap'];

module.exports = function (config) {
    config.set({
        autoWatch: false,
        browsers: [
            'Chrome',
            /*'Chrome_without_security'*/
            'ChromeHeadless'
        ],
        files: [main],
        preprocessors: preprocessors,
        webpack: {
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
            devtool: 'source-map'
        },
    });
};