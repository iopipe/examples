# IOpipe Java Examples

## Running the project

```
# Install dependencies
mvn install
# Package the code into target/ for serverless framework
mvn package
# Deploy with serverless framework
sls deploy
# Invoke the function with the name "Friend"
sls invoke -f hello -d Friend
```

## Classes

These classes contain executable code:

 * `com.iopipe.examples.Hello`
   * Extends `com.iopipe.SimpleRequestHandlerWrapper`
 * `com.iopipe.examples.ManualHello`
   * Creates an instance of `IOpipeService` then invokes the lambda.
 * `com.iopipe.examples.Lowercase`
   * Extends `com.iopipe.SimpleRequestStreamHandlerWrapper`

For reference `PlainHello` does not integrate with IOpipe:

 * `com.iopipe.examples.PlainHello`
   * Implements `com.amazonaws.services.lambda.runtime.RequestHandler`.
