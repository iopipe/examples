import { APIGatewayEvent, Context, ProxyCallback } from 'aws-lambda';
import iopipe, { label, mark } from '@iopipe/iopipe'

const run = (event: APIGatewayEvent, context: Context, callback: ProxyCallback): void => {
  console.log('Hello Typescript!');
  console.log('with label and optional params');
  
  mark.start('database call')
  // some expensive database query
  mark.end('database call')


  label("my label")

  context.succeed('This is my serverless function!')
}


exports.handler = iopipe()(run)