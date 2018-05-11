#coding=utf-8
'''
Created on 2018年5月2日

@author: Jianghan
'''

class PyPublisher(object):
    '''
    classdocs
    '''
    def __init__(self, session, ):
        '''
        Constructor
        '''
        self.session = session
        
        
        sender = self.session.sender("amq.topic/python")
        bo = TestBO_pb2.TestBO()
        bo.uuid = "1234";
        bo.boid = 3;
        bo.destination = "fstp.core.rpc.testone"
        bo.servername = "PythonService"
        bo.msg = "msg content"
        data = bo.SerializeToString()
         
        sender.send(Message(data));
    
    def publish(self, bo):
        sender.send(Message(data));
        