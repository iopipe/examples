const express = require('express');
const lambdaExpress = require('aws-serverless-express');
const iopipe = require('iopipe')();

const app = express();
const server = lambdaExpress.createServer(app);

let invocationContext = {};

// original lambda invocation context middleware
// useful for iopipe plugins (context.iopipe.log)
// or original aws methods (context.getRemainingTimeInMillis)
app.use((req, res, next) => {
  req.context = invocationContext;
  return next();
});

app.get('/', (req, res) => {
  req.context.iopipe.log('Woot!');
  res.json({ success: true });
});

exports.handler = iopipe((event, context) => {
  invocationContext = context;
  return lambdaExpress.proxy(server, event, context);
});
