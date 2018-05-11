from core.bo.pro import TestBO_pb2
from core.bo.model.TestBO import TestBO


class TestBO_OTW(object):

    def __init__(self, byteMsg = None):
        self._bo_pro = TestBO_pb2.TestBO()
        self._bo = TestBO()      
        
        if byteMsg is not None:
            self._bo_pro.ParseFromString(byteMsg)
            self.__setDataFromPB()
        else:
            self.__setDataFromBO()
                  
    def __setDataFromBO(self):  
        self._bo_pro.boid = self._bo.boid
        self._bo_pro.uuid = self._bo.uuid
        self._bo_pro.destination = self._bo.destination 
        
    def __setDataFromPB(self):  
        self._bo.boid = self._bo_pro.boid
        self._bo.uuid = self._bo_pro.uuid
        self._bo.destination = self._bo_pro.destination 
        self._bo.servername = self._bo_pro.servername
        self._bo.msg = self._bo_pro.msg
  
    
    def getBO(self):
        return self._bo
    
    def getProBO(self):
        return self._bo_pro
    
    def setDestination(self, destination):
        self._bo.destination = destination
        self._bo_pro.destination = destination
    
    def setMsg(self, msg):
        self._bo.msg = msg
        self._bo_pro.msg = msg

          
test = TestBO_OTW() 
test.setMsg('123456789') 
# print test.getBO().uuid 
# print test.getBO().destination
# print test.getBO().msg
# print
# 
# print test.getProBO().uuid 
# print test.getProBO().destination
# print test.getProBO().msg
# print
# 
rec = TestBO_OTW(test.getProBO().SerializeToString())
 
print rec.getBO().uuid 
print rec.getBO().destination
print rec.getBO().msg
print
# 
# print rec.getProBO().uuid 
# print rec.getProBO().destination
# print rec.getProBO().msg
# print        
        