package com.purefun.fstp.core.protobuf;

import java.io.IOException;

public class GenerareClass {
	
	
    public static void main(String[] args) {
    String protoFile = "RDSStockBO.proto";//  
//    String strCmd = "D:/software/protobuf-3.5.1/src/protoc.exe -I=./proto --java_out=./src/main/java/ ./proto/"+ protoFile;  

    String strCmd = "D:/software/protobuf-3.5.1/src/protoc.exe -I=./src/main/java/com/purefun/fstp/core/bo/copy/protofile/ --java_out=./src/main/java/ ./src/main/java/com/purefun/fstp/core/bo/copy/protofile/"+ protoFile;  

    try {
        Runtime.getRuntime().exec(strCmd);
    } catch (IOException e) {
        e.printStackTrace();
    }//通过执行cmd命令调用protoc.exe程序  
    }
}
