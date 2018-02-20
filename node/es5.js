var iopipe = require('@iopipe/iopipe')();

exports.handler = iopipe(function(event, context, callback){
  var response = {
    statusCode: 200,
    body: JSON.stringify({
      message: 'Your function executed successfully!',
      input: event
    })
  };
  callback(null, response);
});
