import uuid

class QNSRequestBO(object):

    def __init__(self):
        self.uuid = str(uuid.uuid1())
        self.boid = 4L
        self.destination = "fstp.core.manager.qnsrequest"
        self.servername = ''
        self.request = ''
