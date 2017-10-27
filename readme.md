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
