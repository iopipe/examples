import json
import datetime

from iopipe.iopipe import IOpipe
iopipe = IOpipe("PROJECT_TOKEN")

@iopipe.decorator
def handler(event, context):
    current_time = datetime.datetime.now().time()
    body = {
        "message": "Hello, the current time is " + str(current_time)
    }

    response = {
        "statusCode": 200,
        "body": json.dumps(body)
    }

    return response
