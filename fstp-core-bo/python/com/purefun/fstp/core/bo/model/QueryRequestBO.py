import uuid

class QueryRequestBO(object):

    def __init__(self):
        self.uuid = str(uuid.uuid1())
        self.boid = 5L
        self.destination = "fstp.core.manager.queryrequest"
        self.requestServiceName = ''
        self.respondServiceName = ''
        self.querytopic = ''
        self.tempTopic = ''
