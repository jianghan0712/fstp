#!/usr/bin/env python
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
# 
#   http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

import sys
from qpid.messaging import *
from time import sleep

if len(sys.argv)<2:
    broker =  "localhost:5672" 
#     broker = "client/guest@localhost:5672"
else:
    broker = sys.argv[1]

if len(sys.argv)<3: 
    address = "amq.topic/fstp.core.rpc.testtwo" 
#     address = "test"
else:
    address = sys.argv[2]

connection = Connection(broker)

try:
    connection.open()
    session = connection.session()
   
#     sender = session.sender(address)
    receiver = session.receiver(address)

    while True:
        msg = receiver.fetch()
        print msg.content
        print msg.reply_to

        snd = None
        try:
            snd = session.sender("amq.topic/"+str(msg.reply_to))
            snd.send("12345")
        except SendError, e:
            print e
        if snd is not None:
            snd.close()
        session.acknowledge()

except MessagingError,m:
    print m

sleep(200)
connection.close()
