import uuid

class ExampleBO(object):

    def __init__(self):
        self.uuid = str(uuid.uuid1())
        self.boid = 6L
        self.destination = "fstp.core.example.publish"
        self.name = ''
        self.age = 0
