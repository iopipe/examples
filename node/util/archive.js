var fs = require('fs');
var path = require('path');
var archiver = require('archiver');

var resolve = path.resolve;

var output = fs.createWriteStream(resolve(__dirname, '../archive.zip'));
var archive = archiver('zip');

output.on('close', function(){
  console.log('Archive complete.');
});

archive.on('error', function(err){
  console.error('Something went wrong zipping assets.');
  throw err;
})

archive.pipe(output);

archive.directory(resolve(__dirname, '../node_modules'), '/node_modules');
archive.file(resolve(__dirname, '../index.js'), {name: 'index.js'});
archive.file(resolve(__dirname, '../es5.js'), {name: 'es5.js'});

archive.finalize();
