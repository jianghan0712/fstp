from com.purefun.fstp.core.bo.otw.TestBO_OTW import TestBO_OTW

class PySubscriber(object):
    '''
    classdocs
    '''
    def __init__(self, session, subListener, log):
        '''
        Constructor
        '''
        self.session = session
        self.listener = subListener
        self.log = log
    
    def subscribe(self, topic):
        receiver = self.session.receiver(topic)
        self.listener.init(receiver)
        self.listener.onEvent()              
        self.session.acknowledge()

class SubListener(object):
    '''
    classdocs
    '''
    def __init__(self, log):
        self.receiver = None
        self.log = log
        
    def onEvent(self):
        while self.receiver is not None:
            message = self.receiver.fetch()
            bo_otw = TestBO_OTW(message.content)
#             target.ParseFromString(message.content)           
            self.doTask(bo_otw)
                
    def init(self, Receiver):
        self.receiver = Receiver
        
    def doTask(self, bo_otw):
        self.log.info("Receive BO:",bo_otw.toString())
        
        
#         self.listener.onEvent(message)
#         listener = 
    
#     bo = TestBO_pb2.TestBO()
#     bo.uuid = "1234";
#     bo.boid = 3;
#     bo.destination = "fstp.core.rpc.testone"
#     bo.servername = "PythonService"
#     bo.msg = "msg content"
#     data = bo.SerializeToString()

#     sender.send(Message(data));

#     message = receiver.fetch()
#     target = TestBO_pb2.TestBO()
#     
#     target.ParseFromString(message.content)
#     print target.msg
#     session.acknowledge()