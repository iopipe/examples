let context = {
  iopipe: {
    label: function defaultLabel() {},
    log: function defaultLog() {},
    mark: { start: () => {}, end: () => {} }
  }
};

// simple setter
const setContext = ctx => {
  context = ctx || context;
};

// tracing methods
const start = (...args) => context.iopipe.mark.start(...args);
const end = (...args) => context.iopipe.mark.end(...args);

// custom metrics from this module
const metric = (...args) => context.iopipe.metric(...args);

// use IOpipe labels from this module
const label = (...args) => context.iopipe.label(...args);

// easy to create your own flavor of tracing, this one accepts a promise-based fn and a name and wraps a function automatically
const auto = async (fn = () => {}, nameStr) => {
  const name = nameStr || fn.name || 'trace';
  start(name);
  const res = await fn();
  end(name);
  return res;
};

module.exports = {
  auto,
  end,
  label,
  metric,
  setContext,
  start
};
