
from jpype import *
import os

class PyPService(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
        self.j_service = JClass("com.purefun.fstp.core.server.PService")
        server = self.j_service()
    
    def start1(self):
        self.server.init()
        
    def start2(self):
        self.j_service.start()

