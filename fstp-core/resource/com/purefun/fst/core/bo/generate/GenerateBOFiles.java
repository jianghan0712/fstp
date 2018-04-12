package com.purefun.fst.core.bo.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.purefun.fstp.core.bo.model.BOinstance;

public class GenerateBOFiles {
	PrintWriter myFileWriter;
	private String myIndent = "";
	String protofileDirectory = null;
	String otwfileDirectory = null;
	String builderfileDirectory = null;
	String bopackageName = null;
	final String TAB = "    ";
	final String BO = "bo";
	final String BUILDER = "builder";
	
	public GenerateBOFiles(String path) {
		protofileDirectory = path + "protofile/";
		otwfileDirectory = path + "otw/";
		builderfileDirectory = path + "builder/";
		
		String temp = path.substring(path.indexOf("com"),path.length()-1);
		bopackageName = temp.replaceAll("/", ".");
	}
	
	private void println(String str) {
		this.myFileWriter.println(str);
		System.out.println(str);   
    }
	
	private Object currIndent() {
		// TODO Auto-generated method stub
		return myIndent;
	}
	
	private StringBuilder analysis(String line,int i) {
		// TODO Auto-generated method stub
//		String[] str = line.substring(0, line.indexOf("=")).trim().split(" "); 
		line = line.replaceAll(";", "");
		String[] str = line.trim().split(" "); 
		StringBuilder fin = new StringBuilder();
		//   str[0] str[1] str[2]
		// required string uuid = 1;
		if(str[2].equalsIgnoreCase("uuid") || str[2].equalsIgnoreCase("boid") || str[2].equalsIgnoreCase("destination")) {
			fin.append("    required ");
		}else {
			fin.append("    optional ");
		}
		
		if(str[1].equalsIgnoreCase("double")) {
			fin.append("double ");
		}else if(str[1].equalsIgnoreCase("float")) {
			fin.append("float ");
		}else if(str[1].equalsIgnoreCase("long")) {
			fin.append("sint64 ");
		}else if(str[1].equalsIgnoreCase("String")) {
			fin.append("string ");
		}else if(str[1].equalsIgnoreCase("boolean")) {
			fin.append("bool ");
		}else if(str[1].equalsIgnoreCase("int")) {
			fin.append("int32 ");
		}else {
			System.out.println("con't find type:" + str[1]);
		}		
		fin.append(" ").append(str[2]).append(" = ").append(String.valueOf(i)).append(";");
		
		return fin;		
	}
	
	public void genProtoFile(File directory) throws IOException {
		File flist[] = directory.listFiles();
//		String targetDirectory = directory.getPath() + "\\protofile\\";
				
		if (flist == null || flist.length == 0) {
		    System.out.println("NO BO Found");
		}else {
			File tardir = new File(protofileDirectory);
			if(!tardir.exists()) {
				tardir.mkdirs();
	    	}
			for (File f : flist) {
			    if (f.isDirectory()) {
//			    	generateProtoFile(f);
			    } else {			    	 
			    	 String className = f.getName().substring(0,f.getName().indexOf("."));
			    	 if(!className.contains("BO") || className.equalsIgnoreCase("BaseBO"))
			    		 continue;
			    	 myFileWriter = new PrintWriter(new FileWriter(protofileDirectory + className + ".proto"),true);
			    	 genProFiles(f);
			    }		    		    	
			}
		}		
	}
	
	private void genProFiles(File f) throws IOException {
		// TODO Auto-generated method stub
		String className = f.getName().substring(0,f.getName().indexOf("."));
	
		InputStream input = new FileInputStream(f);
		InputStreamReader inp = new InputStreamReader(input,"UTF-8");
		BufferedReader br = new BufferedReader (inp);
			
		println(new StringBuilder().append("option java_outer_classname = \"").append(className).append("_PRO\";").toString());
		println(new StringBuilder().append("option java_package = \"").append(bopackageName).append(".pro").append("\";").toString());
		println("");
		println(new StringBuilder().append("message ").append(className).append(" {").toString());
				
		String line = null;
		int count = 1;
		while((line = br.readLine())!=null) {
			if(-1 != line.indexOf("{"))
				break;
		}
			 
		while((line = br.readLine())!=null) {
			if(-1 != line.indexOf(";")) {
				println(analysis(line,count++).toString());
			}
			if(-1 != line.indexOf("}")) {
				break;
			}
		}
		
		println("}");
	}

	private void genBuildFile(File directory) {
		File flist[] = directory.listFiles();
//		String targetDirectory = directory.getPath() + "\\protofile\\";
				
		if (flist == null || flist.length == 0) {
		    System.out.println("NO BO Found");
		}else {
			for(File f:flist) {
				String strCmd = "D:/software/protobuf-3.5.1/src/protoc.exe -I=./" + protofileDirectory + " --java_out=./resource/ ./" + protofileDirectory + f.getName();  
				try {
				    Runtime.getRuntime().exec(strCmd);
				} catch (IOException e) {
			        e.printStackTrace();
				}
			}
		}			  
	}
	
	private void genOtwFile(File directory) throws IOException, ClassNotFoundException {
		File flist[] = directory.listFiles();
		
		if (flist == null || flist.length == 0) {
		    System.out.println("NO BO Found");
		}else {
			File tardir = new File(otwfileDirectory);
			if(!tardir.exists()) {
				tardir.mkdirs();
	    	}
			for (File f : flist) {
			    if (!f.isDirectory()) {			    	 
			    	 String className = f.getName().substring(0,f.getName().indexOf("."));
			    	 if(!className.contains("BO") || className.equalsIgnoreCase("BaseBO"))
			    		 continue;
			    	 myFileWriter = new PrintWriter(new FileWriter(otwfileDirectory + className + "_OTW.java"),true);
			    	 genOTWContent(className);
			    }		    		    	
			}
		}		
	}

	private void genOTWContent(String boName) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String _proName = boName + "_PRO";
		String _otwName = boName + "_OTW";
		StringBuilder builder = new StringBuilder(_proName).append(".").append(boName).append(".Builder");
		String boClassName = bopackageName + "." + boName;
		
		Class bo = Class.forName(boClassName);
		Field[] fields=bo.getDeclaredFields();
		
		println(new StringBuilder("package ").append(bopackageName).append(".otw;").toString());
		println("");
		
		println(new StringBuilder("import ").append(bopackageName).append(".").append(boName).append(";").toString());
		println(new StringBuilder("import ").append(bopackageName).append(".pro.").append(boName).append("_PRO;").toString());
		println(new StringBuilder("import com.google.protobuf.InvalidProtocolBufferException;").toString());
		println("");
		println(new StringBuilder("public class ").append(_otwName).append(" implements ICommom_OTW {").toString());
		
		//定义变量
		println(new StringBuilder(TAB).append(builder).append(" ").append(BUILDER).append(" = null;").toString());
		println(new StringBuilder(TAB).append(boName).append(" ").append(BO).append(" = null;").toString());
		println("");
		
		//定义构造器1
		println(new StringBuilder(TAB).append("public ").append(_otwName).append("() {").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(" = ")
									  .append(_proName).append(".").append(boName).append(".").append("newBuilder();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BO).append("= new ").append(boName).append("();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setUuid(bo.uuid);").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setBoid(bo.boid);").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setDestination(bo.destination);").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");
		
		//定义构造器2
		println(new StringBuilder(TAB).append("public ").append(_otwName).append("(byte[] message) throws InvalidProtocolBufferException {").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(" = ")
				  .append(_proName).append(".").append(boName).append(".").append("newBuilder();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BO).append("= new ").append(boName).append("();").toString());
		println(new StringBuilder(TAB).append(TAB).append(_proName).append(".").append(boName).append(" receive = ")
									  .append(_proName).append(".").append(boName).append(".parseFrom(message);").toString());
		genStructSetMethod(fields);
		println(new StringBuilder(TAB).append("}").toString());
		println("");
		
		//定义@Override
		//builder
		println(new StringBuilder(TAB).append("@Override").toString());
		println(new StringBuilder(TAB).append("public ").append(_proName).append(".").append(boName).append(".Builder getBuilder() { ").toString());
		println(new StringBuilder(TAB).append(TAB).append("return builder;").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");
		
		//bo
		println(new StringBuilder(TAB).append("@Override").toString());
		println(new StringBuilder(TAB).append("public ").append(boName).append(" getBo() { ").toString());
		println(new StringBuilder(TAB).append(TAB).append("return bo;").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");	
		
		genMethod(fields);
		
		genToString(_otwName,fields);
		println("}");					
	}

	private void genToString(String name,Field[] fields) {
		// TODO Auto-generated method stub
		println(new StringBuilder(TAB).append("public String toString() {").toString()); 
		println(new StringBuilder(TAB).append(TAB).append("return \"").append(name).append(" [\"+").toString());
		for(Field field:fields) {
			StringBuilder fieldName = new StringBuilder(field.getName());
			StringBuilder methodName = new StringBuilder("get");
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append(first).append(last);
			println(new StringBuilder(TAB).append(TAB).append(TAB).append("\"").append(field.getName())
					                      .append(" = \" + ").append(methodName).append("() +").append("\",\" +").toString());			
		}
		println(new StringBuilder(TAB).append("\"]\";").toString()); 
		println(new StringBuilder(TAB).append("}").toString());
		
	}

	private void genStructSetMethod(Field[] fields) {
		// TODO Auto-generated method stub
		for(Field field : fields) {
			StringBuilder setmethodName = new StringBuilder();
			StringBuilder getmethodName = new StringBuilder();
			String fieldName = field.getName();
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			
			
			String[] str = fieldName.split("_");
			setmethodName.append(first).append(last);
			
			if(str.length == 1) {//没有  _
				getmethodName = setmethodName;
			}else {
				StringBuilder all = new StringBuilder();
				for(String each:str) {
					all.append(each.substring(0, 1).toUpperCase());
					all.append(each.substring(1).toString());
				}
				getmethodName.append(all);
			}
							
			println(new StringBuilder(TAB).append(TAB).append("set").append(setmethodName).append("(")
										  .append("receive.get").append(getmethodName).append("());").toString());
		}
			
		
	}

	private void genMethod(Field[] fields) {
		// TODO Auto-generated method stub
		for(Field field : fields) {
			Class type = field.getType();
			genGetMethod(field,type);
			genSetMethod(field,type);
		}
	
	}

	private void genSetMethod(Field field,Class type) {
		// TODO Auto-generated method stub
		String fieldName = field.getName();		
		StringBuilder methodName = new StringBuilder();
		StringBuilder all = new StringBuilder("set");
		
		String[] str = fieldName.split("_");
		if(str.length == 1) {//没有  _
			methodName = all;
		}else {
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append("set").append(first).append(last);
		}
		for(String each:str) {
			all.append(each.substring(0, 1).toUpperCase());
			all.append(each.substring(1).toString());
		}
		
		println(new StringBuilder(TAB).append("public void ").append(methodName)
				                      .append("(").append(type.getName()).append(" ").append(field.getName()).append(") {").toString());
		//        bo.msg = msg;
		println(new StringBuffer(TAB).append(TAB)
                .append("bo.").append(field.getName()).append(" = ").append(field.getName()).append(";").toString());
		//        build.setMsg(msg);
		println(new StringBuffer(TAB).append(TAB)
				                     .append("builder.").append(all)
				                     .append("(").append(field.getName()).append(");").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");		
	}

	private void genGetMethod(Field field,Class type) {
		// TODO Auto-generated method stub
		String fieldName = field.getName();
		
		StringBuilder methodName = new StringBuilder();
		StringBuilder all = new StringBuilder("get");
		
		String[] str = fieldName.split("_");
		if(str.length == 1) {//没有  _
			methodName = all;
		}else {
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append("get").append(first).append(last);
		}
			
		for(String each:str) {
			all.append(each.substring(0, 1).toUpperCase());
			all.append(each.substring(1).toString());
		}
		println(new StringBuilder(TAB).append("public ").append(type.getName()).append(" ").append(methodName).append("() {").toString());
		println(new StringBuilder(TAB).append(TAB).append("return ").
				                       append(BUILDER).append(".").append(all).append("()").append(";").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");
		
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {	
		String bodir = "resource/com/purefun/fstp/core/bo/copy/";
		GenerateBOFiles writer = new GenerateBOFiles(bodir);
		writer.genProtoFile(new File(bodir));
		writer.genBuildFile(new File(writer.protofileDirectory));
		writer.genOtwFile(new File(bodir));
	}


}
