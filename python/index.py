import json
import os

from iopipe.iopipe import IOpipe

iopipe = IOpipe(os.environ['IOPIPE_TOKEN'])


@iopipe.decorator
def handler(event, context):
    body = {
      'message': 'Your function executed successfully!',
      'input': event
    }

    response = {
        'statusCode': 200,
        'body': json.dumps(body)
    }

    return response
