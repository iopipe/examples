# IOpipe Example Projects

Get started with AWS Lambda & IOpipe quickly with these starter projects.

## Node.js

From the repository directory, execute

`cd node && npm run pkg`

This will download the dependencies for `iopipe` into your `node_modules` folder and package the assets into `archive.zip`.

Point your Lambda handler to `index.handler` (default) if you're using Node >= 6.10. If you need es5 formatted code (<= 6.10), point to `es5.handler`.

Upload the `archive.zip` folder.

Set `IOPIPE_TOKEN` environment variable in the AWS web console to your project token.

When you run a test of your new Lambda function, you should see an output of:

```json
{
  "statusCode": 200,
  "body": "{\"message\":\"Your function executed successfully!\",\"input\":{\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}"
}
```

If you don't, create an issue here on GitHub, or reach out to us at support@iopipe.com
