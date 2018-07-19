#coding=utf-8
'''
Created on 2018年5月2日

@author: Jianghan
'''
from qpid.messaging import *

class PyPublisher(object):
    '''
    classdocs
    '''
    def __init__(self, session, log, cache):
        '''
        Constructor
        '''
        self.session = session
        self.log = log
        self.cache = cache
        
    
    def publish(self, bo, durFlag = False, topicFormat = "amq.topic/"):
        if self.session is not None :
            sender = self.session.sender(topicFormat + str(bo.getDestination())) 
            
        data = bo.getProBO().SerializeToString()
        sender.send(Message(data))
        self.log.info("Publish BO:", bo.toString())
        if durFlag is True:
            self.durable(bo)
    
    def durable(self, bo):
        if self.cache is None:
            self.log.info("cache is not init")
        self.cache.put(bo.getBO().__class__.__name__, bo.getUuid(), bo)

            
        
        