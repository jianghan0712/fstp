import ConfigParser
import log4py



if __name__ == '__main__':
#     cp = ConfigParser.SafeConfigParser()
#     cp.read('conf1.conf')
#      
#     server = ConfigParser.SafeConfigParser()
#     server.read(cp.get('conf','1'))
#     print 'host of ssh:', server.get('ssh', 'pass')   # host of ssh: 192.168.1.101
    comCof = ConfigParser.SafeConfigParser()
    comCof.read('../config/comConf.conf')
    print 'host of ssh:', comCof.get('Qpid', 'port')
