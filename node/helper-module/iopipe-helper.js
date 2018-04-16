const top = {
  context: {
    iopipe: {
      label: () => {},
      log: () => {},
      mark: { start: () => {}, end: () => {} }
    }
  }
};

// simple setter
const setContext = ctx => {
  top.context = ctx || top.context;
};

// tracing methods
const start = top.context.iopipe.mark.start;
const end = top.context.iopipe.mark.end;

// log from this module
const log = top.context.iopipe.log;

// use IOpipe labels from this module
const label = top.context.iopipe.label;

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
  log,
  setContext,
  start
};
