import lambda = require('aws-lambda');
import iopipe = require('@iopipe/iopipe');

const run = (event: lambda.APIGatewayEvent, context: lambda.Context, callback: lambda.ProxyCallback): void => {
  console.log('Hello Typescript!');

  // Labelling
  iopipe.label("my label");

  // Custom Metrics
  iopipe.metric("magic number", 42);

  // Tracing
  iopipe.mark.start('database call');
  // some expensive database query
  iopipe.mark.end('database call');

  callback(null, {
    statusCode: 200,
    body: "This is my serverless function!"
  })
}

exports.handler = iopipe()(run);
