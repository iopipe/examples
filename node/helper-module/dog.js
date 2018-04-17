const got = require('got');
const { start, end, auto } = require('./iopipe-helper');

const getDog = () =>
  got('https://dog.ceo/api/breeds/image/random', { json: true });

// this function uses the "typical" tracing simple start/end marks
exports.typeOne = async () => {
  // start the trace
  start('fetch-boy');
  // do the work you typically would
  const res = await getDog();
  // end the trace
  end('fetch-boy');
  // return result
  return res.body.message;
};

// this function uses a bespoke "autoTrace" function
exports.typeTwo = async () => {
  const res = await auto(getDog);
  return res.body.message;
};
