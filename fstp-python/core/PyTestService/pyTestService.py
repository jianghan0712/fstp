from core.MainService.PyPService import PyPService
from core.log import PyPLogger
from core.ipc.sub.PySubscriber import PySubscriber,SubListener
from com.purefun.fstp.core.bo.otw.TestBO_OTW import TestBO_OTW
from core.ipc.pub.PyPublisher import PyPublisher


class PyTestService(PyPService):
    '''
    classdocs
    '''
    def __init__(self, serviceName, env, instance):
        super(PyTestService, self).__init__(serviceName, env, instance)
        self.log = PyPLogger.PyPLogger(PyTestService) 
        
    def initService(self):
        super(PyTestService, self).initService()
        self.log.info("this is initService")
    
    def startService(self):
        super(PyTestService, self).startService()
        
        listner = SubListener(self.log)
        sub = PySubscriber(self.session, listner, self.log)
        sub.subscribe("amq.topic/fstp.core.rpc.testone")
        
#         bo = TestBO_OTW()
#         self.pub = PyPublisher(self.session,self.log)
#         self.pub.publish(bo)
        

service = PyTestService("pyTestService","DEV","1")
service.initService()
service.startService()

raw_input() 
   