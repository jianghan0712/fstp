from core.common.ICommon_OTW import ICommon_OTW
from com.purefun.fstp.core.bo.pro import TestBO2_pb2
from com.purefun.fstp.core.bo.model.TestBO2 import TestBO2

class TestBO2_OTW(ICommon_OTW):

    def __init__(self, byteMsg = None):
        self._bo_pro = TestBO2_pb2.TestBO2()
        self._bo = TestBO2()

        if byteMsg is not None:
            self._bo_pro.ParseFromString(byteMsg)
            self.__setDataFromPB()
        else:
            self.__setDataFromBO()

    def __setDataFromBO(self):
        self._bo_pro.uuid = self._bo.uuid
        self._bo_pro.boid = self._bo.boid
        self._bo_pro.destination = self._bo.destination
        self._bo_pro.servername = self._bo.servername
        self._bo_pro.msg = self._bo.msg

    def __setDataFromPB(self):
        self._bo.uuid = self._bo_pro.uuid
        self._bo.boid = self._bo_pro.boid
        self._bo.destination = self._bo_pro.destination
        self._bo.servername = self._bo_pro.servername
        self._bo.msg = self._bo_pro.msg

    def getBO(self):
        return self._bo

    def getProBO(self):
        return self._bo_pro

    def getUuid(self):
        return self._bo.uuid

    def setUuid(self, uuid):
        self._bo.uuid = uuid
        self._bo_pro.uuid = uuid

    def getBoid(self):
        return self._bo.boid

    def setBoid(self, boid):
        self._bo.boid = boid
        self._bo_pro.boid = boid

    def getDestination(self):
        return self._bo.destination

    def setDestination(self, destination):
        self._bo.destination = destination
        self._bo_pro.destination = destination

    def getServername(self):
        return self._bo.servername

    def setServername(self, servername):
        self._bo.servername = servername
        self._bo_pro.servername = servername

    def getMsg(self):
        return self._bo.msg

    def setMsg(self, msg):
        self._bo.msg = msg
        self._bo_pro.msg = msg

    def toString(self):
        return "TestBO2_OTW ["+"uuid = " + str(self.getUuid()) +"," +"boid = " + str(self.getBoid()) +"," +"destination = " + str(self.getDestination()) +"," +"servername = " + str(self.getServername()) +"," +"msg = " + str(self.getMsg()) +"," +"]"
