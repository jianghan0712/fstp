from jpype import *
import os

jarpath = os.path.join(os.path.abspath('.'),'C:\Users\jianghan\Desktop\\fstp-core.jar')
dependency = os.path.join(os.path.abspath('.'), 'D:/fstp-core_lib/')

startJVM(getDefaultJVMPath(), "-ea", 
         "-Djava.class.path=%s" % jarpath,
         "-Djava.ext.dirs=%s" %dependency,
         "-DServiceName=%s" %"MonitorService",
         "-DEnv=DEV",
         "-DInstance=1")

# PService = JClass("com.purefun.fstp.core.server.PService")
Test = JClass("com.purefun.fstp.core.python.TestPython")

Test.getLog().info("1234")
shutdownJVM()
# class TestService(PService):
#      def __init__(self):
         
# Test = JClass("com.purefun.fstp.core.bo.otw.QNSRequestBO_OTW")

# t = Test()

# print t.getUuid()
