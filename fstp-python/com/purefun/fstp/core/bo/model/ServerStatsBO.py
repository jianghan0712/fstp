import uuid

class ServerStatsBO(object):

    def __init__(self):
        self.uuid = str(uuid.uuid1())
        self.boid = 1L
        self.destination = "fstp.core.manager.serverstatus"
        self.servername = ''
        self.status = -1
