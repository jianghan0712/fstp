#coding=utf-8
from core.log import PyPLogger
from core.ipc.qpid import PyQpidConnect
import ConfigParser

class PyPService(object):
    ''' 主服务类   '''
    
    def __init__(self, serviceName, env, instance):
        self.serviceName = serviceName
        self.property = PyProperty(serviceName, env, instance)
        
        self.log = PyPLogger.PyPLogger(PyPService)  
        self.qpid = PyQpidConnect.PyQpidConnect(self.log) #config/qpid.conf
        self.confDic = dict()                             #用户自定义的配置字典
      
    def __initConfig(self):
        path = "../../config/" + self.serviceName + "/" + self.property.env + "/" + self.property.instance + "/"
        mainCof = ConfigParser.SafeConfigParser()
        mainCof.read(path + "config.conf")
        self.confDic['main'] = mainCof
        if mainCof.has_section('conf'):
            for eachConf in mainCof.options('conf'):                
                self.__loadConfigs(path, eachConf, mainCof.get('conf', eachConf))
            
    def __loadConfigs(self, confPath, moduName, fileName):
        subConf = ConfigParser.SafeConfigParser()
        if  fileName.find('/') == -1:
            subConf.read(confPath + fileName)           
        else :
            subConf.read(fileName)
        
        if subConf is None:
            self.log.error("load config ",fileName," failed")
        else:
            self.confDic[moduName] = subConf    
     
    def getConfigBean(self, module = 'main', section = None, option = None):  
        ret = None
        if self.confDic.get(module) is None:
            self.log.error("config module ", module, " is not exist!") 
            return None
        
        if self.confDic.get(module).has_section(section) :
            if self.confDic.get(module).has_option(section, option):
                ret = self.confDic.get(module).get(section, option)
            else:
                self.log.error("get config module=", module, " ,section=", section, " ,option", option, " failed") 
        else:
            self.log.error("get config module=", module, " ,section=", section, " ,option=", option, " failed")
        
        return ret
           
    def initService(self):
        self.__initConfig()             #初始化配置文件
        self.qpid.createSession()       #初始化Qpid
      
    def startService(self):
        pass


class PyProperty(object):
    serviceName = None
    env = None
    instance = None
    
    def __init__(self, serviceName, env, instance):
        self.serviceName = serviceName
        self.env = env
        self.instance = instance  

    
service = PyPService("pyTestService","DEV","1")
service.initService()

str = service.getConfigBean('db', 'db', 'url')
print str
# print service.confDic['db'].get('db','url')
