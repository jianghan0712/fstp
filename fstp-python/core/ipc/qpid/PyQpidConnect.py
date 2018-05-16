import sys
from qpid.messaging import *
import ConfigParser 
import uuid

from core.log import PyPLogger

class PyQpidConnect(object):
    '''
    classdocs
    '''
    def __init__(self,log):
        self._name = None
        self._pass = None
        self._ip = None
        self._port = None
        
        self.session = None
        self.connect = None
        self.log = log
        self._loadConf()
    
    def _loadConf(self):
        conf = ConfigParser.SafeConfigParser()
        conf.read('../../config/qpid.conf')
        self._name = conf.get('Qpid', 'user')
        self._pass = conf.get('Qpid', 'pass')
        self._ip = conf.get('Qpid', 'ip')
        self._port = conf.get('Qpid', 'port')
        
        url = self._ip + ':' + self._port
        self.connect = Connection(url)
        
    def createSession(self):
        try:
            self.connect.open()
            self.session = self.connect.session()
            self.log.info('create qpid session succesful')
            return self.session
#             sender = self.session.sender("amq.topic/python")
#             bo = TestBO_pb2.TestBO()
#             bo.uuid = str(uuid.uuid1())
#             bo.boid = 3;
#             bo.destination = "fstp.core.rpc.testone"
#             bo.servername = "PythonService"
#             bo.msg = "msg content"
#             data = bo.SerializeToString()
#             sender.send(Message(data));
            
#             receiver = session.receiver(address)
        except MessagingError,m:
            print m
