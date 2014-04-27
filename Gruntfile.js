module.exports = function (grunt) {
    var webappDir = 'src/main/webapp/WEB-INF/';
    var cssDir = webappDir + 'css/';
    var jsDir = webappDir + 'js/';
    var imgDir = webappDir + 'images/';

    var dstDir = 'src/main/webapp/public/';
    var bowerDir = dstDir + 'lib/';
    var dstCssDir = dstDir + 'css/';
    var dstJsDir = dstDir + 'js/';
    var dstImgDir = dstDir + 'images/';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        clean: [ dstDir ],
        bower: {
            install: {
                options: {
                    targetDir: bowerDir,
                    install: true,
                    cleanTargetDir: false,
                    cleanBowerDir: false,
                    bowerOptions: {}
                }
            }
        },
        copy: {
            images_css: {
                expand: true,
                cwd: cssDir,
                src: [
                    'images/*'
                ],
                dest: dstCssDir
            },
            images: {
                expand: true,
                cwd: imgDir,
                src: [ '**/*' ],
                dest: dstImgDir
            }
        },
        uglify: {
            dist: {
                options: {
                    compress: true,
                    report: 'min'
                },
                src: [
                        jsDir + '*.js'
                ],
                dest: dstJsDir + 'all.js'
            }
        },
        less: {
            dist: {
                options: {
                    compress: true,
                    yuicompress: true,
                    report: 'min'
                },
                src: [
                        cssDir + '*.less',
                        cssDir + '*.css'
                ],
                dest: dstCssDir + 'all.css'
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-less');

    grunt.registerTask('default', ['bower', 'uglify', 'less', 'copy']);
};
