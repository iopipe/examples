const iopipe = require('@iopipe/iopipe')();

// modularize the tracing functionality for use in other modules outside of "main"
const { setContext, label, metric } = require('./iopipe-helper');

// normal lambda work
const { typeOne, typeTwo } = require('./dog');

exports.handler = iopipe(async (event, context) => {
  // important! reset context for each invocation
  setContext(context);

  // do your work as normal
  const pongo = await typeOne();
  const perdita = await typeTwo();

  // add IOpipe labels to the invocation, 1 label per dog breed
  const breeds = [pongo, perdita].map(url => url.match(/\/breeds\/(.*)\//)[1]);
  breeds.forEach(label);

  // add IOpipe custom metrics for each url
  metric('pongo-url', pongo);
  metric('perdita-url', perdita);

  context.succeed(JSON.stringify({ pongo, perdita }));
});
