# IOpipe Example Projects

Get started with AWS Lambda & [IOpipe](https://iopipe.com) quickly with these starter projects.

## Node.js

From the repository directory, execute

```bash
cd node && npm run pkg
```

This will download the dependencies for `iopipe` into your `node_modules` folder and package the assets into `archive.zip`.

Point your Lambda handler to `index.handler` for Node > v6 (default).

Upload the `archive.zip` archive.

Set `IOPIPE_TOKEN` environment variable in the AWS web console to your project token.

When you run a test of your new Lambda function, you should see an output of:

```json
{
  "statusCode": 200,
  "body": "{\"message\":\"Your function executed successfully!\",\"input\":{\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}"
}
```

If you don't, create an issue here on GitHub, or reach out to us at support@iopipe.com

The available handler examples are:
- index.js : Standard hello world for Node v6+
- es5.js : Hello world for Node < v6
- express.js : A lambda built to run from an express app using `aws-serverless-express`
- typescript.ts : Standard hello world for TypeScript (Be sure to compile with `tsc` before deploying)

### TypeScript dependencies
We use [DefinitelyTyped](https://github.com/DefinitelyTyped/DefinitelyTyped), an open-sourced repository, to manage our declaration files. You can add our types adding `@types/iopipe__iopipe` as a dependency. You may also need `@type/aws-lambda` and `@types/aws-node`.

## Python

From the repository directory, execute

```bash
cd python && python archive.py
```

This will download the dependencies for `iopipe` into a `libs` folder and package the assets into `archive.zip`.

Point your Lambda handler to `index.handler`.

Upload the `archive.zip` archive.

Set `IOPIPE_TOKEN` environment variable in the AWS web console to your project token.

When you run a test of your new Lambda function, you should see an output of:

```json
{
  "statusCode": 200,
  "body": "{\"message\":\"Your function executed successfully!\",\"input\":{\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}"
}
```

If you don't, create an issue here on GitHub, or reach out to us at support@iopipe.com

See the [python library repo](https://github.com/iopipe/iopipe-python) for further instructions.

## Java

You can run the example by running the following:

For Maven, run:


```
# Go to the Java directory
cd java
# Install dependencies
mvn install
# Package the code into target/ for serverless framework
mvn package
# Deploy with serverless framework
sls deploy
# Invoke the function with the name "Friend"
sls invoke -f hello -d Friend
```

For Gradle, this requires that `serverless.yml` be slightly modified because
Gradle places the JAR it creates at an alternative location, after performing
that step you may run:

```
# Go to the Java directory
cd java
# Build a shadowJar with Gradle
gradle shadowJar
# Deploy the project
sls deploy
# Invoke the function with the name "Friend"
sls invoke -f hello -d Friend
```

These classes contain various examples which may be used:

 * `com.iopipe.examples.Hello`
   * Extends `com.iopipe.SimpleRequestHandlerWrapper`
 * `com.iopipe.examples.ManualHello`
   * Creates an instance of `IOpipeService` then invokes the lambda.
 * `com.iopipe.examples.Lowercase`
   * Extends `com.iopipe.SimpleRequestStreamHandlerWrapper`
 * `com.iopipe.examples.APIGatewayExample`
   * Provides an example which utilizes API Gateway.

For reference `PlainHello` does not integrate with IOpipe:

 * `com.iopipe.examples.PlainHello`
   * Implements `com.amazonaws.services.lambda.runtime.RequestHandler`.

See the [Java agent repository](https://github.com/iopipe/iopipe-java) for further instructions.

