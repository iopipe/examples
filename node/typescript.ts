import { APIGatewayEvent, Context, ProxyCallback } from 'aws-lambda';
import iopipe, { mark, metric, label } from '@iopipe/iopipe';

const run = (event: APIGatewayEvent, context: Context, callback: ProxyCallback): void => {
  console.log('Hello Typescript!');
  
  mark.start('database call');
  // some expensive database query
  mark.end('database call');

  metric("magic number", 42);

  label("my label");

  context.succeed('This is my serverless function!');
}

exports.handler = iopipe()(run);