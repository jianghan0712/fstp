package com.purefun.fstp.core.bo.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class GeneratePythonBOFiles {
	PrintWriter myFileWriter;
	private String myIndent = "";
	String protofileDirectory = null;
	String modelDirectory = null;
	String otwfileDirectory = null;
	String builderfileDirectory = null;
	String bopackageName = null;
	final String TAB = "    ";
	final String BO = "bo";
	final String BUILDER = "builder";	
	String TargetPath = "python\\";
	
	
	public GeneratePythonBOFiles(String path) {
//		protofileDirectory = path + "python/protofile/";
//		otwfileDirectory = path + "python/otw/";
//		builderfileDirectory = path + "builder/";
//		modelDirectory = path + "python/model/";
//		
//		String temp = path.substring(path.indexOf("com"),path.length()-1);
//		bopackageName = temp.replaceAll("/", ".");
	}
	
	private void println(String str) {
		this.myFileWriter.println(str);
//		System.out.println(str);   
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
	
	public void genModelFile(File directory) throws IOException, ClassNotFoundException {
		File flist[] = directory.listFiles();
//		String targetDirectory = directory.getPath() + "\\protofile\\";
				
		if (flist == null || flist.length == 0) {
		    System.out.println("NO BO Found");
		}else {
			File tardir = new File(modelDirectory);
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
			    	 myFileWriter = new PrintWriter(new FileWriter(modelDirectory + className + ".py"),true);
			    	 genPyModel(f);
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
			
		println(new StringBuilder().append("syntax = \"proto2\";").toString());
		println(new StringBuilder().append("package ").append(bopackageName).append(";").toString());
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

	private void genOTWContent(String boName) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String _pb2Name = boName + "_pb2";
		String _otwName = boName + "_OTW";
		StringBuilder builder = new StringBuilder(_pb2Name).append(".").append(boName).append(".Builder");
		String boClassName = bopackageName + "." + boName;
		
		Class bo = Class.forName(boClassName);
		Field[] fields=bo.getDeclaredFields();
		
		println(new StringBuilder("from ").append(bopackageName).append(".pro import ").append(_pb2Name).toString());
		println(new StringBuilder("from ").append(bopackageName).append(".model.").append(boName).append(" import ").append(boName).toString());
		println("");
		
		println(new StringBuilder("class ").append(_otwName).append("(object):").toString());
		println("");
		
		//_init(self,object)
		println(new StringBuilder(TAB).append("def __init__(self, byteMsg = None):").toString());
		println(new StringBuilder(TAB).append(TAB).append("self._bo_pro = ").append(_pb2Name).append(".").append(boName).append("()").toString());
		println(new StringBuilder(TAB).append(TAB).append("self._bo = ").append(boName).append("()").toString());
		println("");
		println(new StringBuilder(TAB).append(TAB).append("if byteMsg is not None:").toString());
		println(new StringBuilder(TAB).append(TAB).append(TAB).
									append("self._bo_pro.ParseFromString(byteMsg)").toString());
		println(new StringBuilder(TAB).append(TAB).append(TAB).
									append("self.__setDataFromPB()").toString());
		println(new StringBuilder(TAB).append(TAB).append("else:").toString());
		println(new StringBuilder(TAB).append(TAB).append(TAB).
									append("self.__setDataFromBO()").toString());		
		println("");
		
		//__setDataFromBO
		println(new StringBuilder(TAB).append("def __setDataFromBO(self):").toString());
		genDataFromBO(fields);
		println("");
		
		//__setDataFromPB
		println(new StringBuilder(TAB).append("def __setDataFromPB(self):").toString());
		genDataFromPB(fields);
		println("");
		
		//getBO
		println(new StringBuilder(TAB).append("def getBO(self):").toString());
		println(new StringBuilder(TAB).append(TAB).append("return self._bo").toString());
		println("");
		
		//getProBO
		println(new StringBuilder(TAB).append("def getProBO(self):").toString());
		println(new StringBuilder(TAB).append(TAB).append("return self._bo_pro").toString());
		println("");
		
		//get/set method
		genMethod(fields);
		
	}

	private void genMethod(Field[] fields) {
		// TODO Auto-generated method stub
		for(Field field : fields) {
			genGetMethod(field);
			genSetMethod(field);
		}
	}

	private void genSetMethod(Field field) {
		// TODO Auto-generated method stub
		String name = field.getName();
		String part1 = name.substring(0, 1).toUpperCase();
		String part2 = name.substring(1);
		println(new StringBuilder(TAB).append("def set").append(part1).append(part2).append("(self, ").append(name).append("):").toString());
		println(new StringBuilder(TAB).append(TAB).append("self._bo.").append(name).append(" = ").append(name).toString());
		println(new StringBuilder(TAB).append(TAB).append("self._bo_pro.").append(name).append(" = ").append(name).toString());
		println("");
	}

	private void genGetMethod(Field field) {
		// TODO Auto-generated method stub
		String name = field.getName();
		String part1 = name.substring(0, 1).toUpperCase();
		String part2 = name.substring(1);
		println(new StringBuilder(TAB).append("def get").append(part1).append(part2).append("(self):").toString());
		println(new StringBuilder(TAB).append(TAB).append("return self._bo.").append(name).toString());
		println("");
	}

	private void genDataFromPB(Field[] fields) {
		// TODO Auto-generated method stub
		for(Field each : fields) {
			println(new StringBuilder(TAB).append(TAB).
					append("self._bo.").append(each.getName()).append(" = ").append("self._bo_pro.").append(each.getName()).toString());
		}	
	}

	private void genDataFromBO(Field[] fields) {
		// TODO Auto-generated method stub
		for(Field each : fields) {
			println(new StringBuilder(TAB).append(TAB).
					append("self._bo_pro.").append(each.getName()).append(" = ").append("self._bo.").append(each.getName()).toString());
		}		
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

	private void genPyModel(File f) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String className = f.getName().substring(0,f.getName().indexOf("."));

		InputStream input = new FileInputStream(f);
		InputStreamReader inp = new InputStreamReader(input,"UTF-8");
		BufferedReader br = new BufferedReader (inp);
			
		println(new StringBuilder().append("import uuid").toString());
		println("");
		println(new StringBuilder().append("class ").append(className).append("(object):").toString());
		println("");
		println(new StringBuilder().append(TAB).append("def __init__(self):").toString());		
				
		String line = null;
		int count = 1;
		while((line = br.readLine())!=null) {
			if(-1 != line.indexOf("{"))
				break;
		}
			 
		while((line = br.readLine())!=null) {
			if(-1 != line.indexOf(";")) {			
				println(analysis(line).toString());
			}
			if(-1 != line.indexOf("}")) {
				break;
			}
		}
	}

	private StringBuilder analysis(String line) {
		// TODO Auto-generated method stub
		line = line.replaceAll(";", "");
		String[] str = line.trim().split(" "); 
//		[0]		[1]	  [2]  [3]  [4]
//		public String uuid = UUID.createUuid();
		StringBuilder fin = new StringBuilder();
		int len = str.length;
		
		if(str[2].equalsIgnoreCase("uuid")) {
			fin.append(TAB).append(TAB).append("self.uuid = str(uuid.uuid1())");
		}else if (str[2].equalsIgnoreCase("boid")){
			fin.append(TAB).append(TAB).append("self.boid = ").append(str[4]);
		}else if (str[2].equalsIgnoreCase("destination"))
			fin.append(TAB).append(TAB).append("self.destination = ").append(str[4]);
		else {
			if (len<=3) {
				fin.append(TAB).append(TAB).append("self.").append(str[2]).append(" = ");
				if(str[1].equalsIgnoreCase("double")) {
					fin.append("0");
				}else if(str[1].equalsIgnoreCase("float")) {
					fin.append("0.0");
				}else if(str[1].equalsIgnoreCase("long")) {
					fin.append("0");
				}else if(str[1].equalsIgnoreCase("String")) {
					fin.append("\'\'");
				}else if(str[1].equalsIgnoreCase("boolean")) {
					fin.append("false");
				}else if(str[1].equalsIgnoreCase("int")) {
					fin.append("0");
				}
			}else {
				if (str[4].equalsIgnoreCase("null")) {
					fin.append(TAB).append(TAB).append("self.").append(str[2]).append(" = ").append("\'\'");
				}else {
					fin.append(TAB).append(TAB).append("self.").append(str[2]).append(" = ").append(str[4]);
				}
			}					
		}		
		return fin;		
	}	

	private void genBuildFile(String fileName) {
		String strCmd = "./resource/protoc.exe -I=./" + protofileDirectory + " --python_out=./" + builderfileDirectory + " ./" + protofileDirectory + fileName;  
		try {
		    Runtime.getRuntime().exec(strCmd);
		} catch (IOException e) {
	        e.printStackTrace();
		}			  
	}
	
	private void genInit(File directory) throws IOException {
		// TODO Auto-generated method stub
		File flist[] = directory.listFiles();
		if (flist == null || flist.length == 0) {
		    return;
		}else {
			for (File f : flist) {
			    if (f.isDirectory()) {	
			    	PrintWriter initWriter = new PrintWriter(new FileWriter(f.getPath() + "/__init__.py"),true);
			    	initWriter.println("");
			    	genInit(f);		    	
			    } 
			}
		}
	}
	
	public void genFile(File directory) throws IOException, ClassNotFoundException {
		File flist[] = directory.listFiles();
		if (flist == null || flist.length == 0) {
		    System.out.println("NO BO Found");
		}else {
			for (File f : flist) {
			    if (f.isDirectory()) {			    	
			    	genFile(f);		    	
			    } else {			    	 
			    	String className = f.getName().substring(0,f.getName().indexOf("."));
			    	if(!className.contains("BO") || className.equalsIgnoreCase("BaseBO") || className.contains("Generate"))
			    		continue;
			    	String filePath = f.getPath().substring(0,f.getPath().lastIndexOf("\\"));
			    	String targetPath = TargetPath + filePath.substring(filePath.indexOf("com"));
				 	protofileDirectory = targetPath + "\\protofile\\";
				 	otwfileDirectory = targetPath + "\\otw\\";
				 	builderfileDirectory = targetPath + "\\pro\\";
				 	modelDirectory = targetPath + "\\model\\";
				 	System.out.println(targetPath);
				 	String temp = targetPath.substring(targetPath.indexOf("com"),targetPath.length());
				 	bopackageName = temp.replaceAll("\\\\", ".");
			    	System.out.println(targetPath +"----" +  className +"----" +  protofileDirectory  +"----" + otwfileDirectory +"----" +builderfileDirectory +"-----" + bopackageName);
					File tardir = new File(protofileDirectory);
					if(!tardir.exists()) {
						tardir.mkdirs();
				   	}
					File otwdir = new File(otwfileDirectory);
					if(!otwdir.exists()) {
						otwdir.mkdirs();
				   	}
					File prodir = new File(builderfileDirectory);
					if(!prodir.exists()) {
						prodir.mkdirs();
				   	}
					File modeldir = new File(modelDirectory);
					if(!modeldir.exists()) {
						modeldir.mkdirs();
				   	}
			    	myFileWriter = new PrintWriter(new FileWriter(protofileDirectory + className + ".proto"),true);
			    	genProFiles(f);
			    	myFileWriter = new PrintWriter(new FileWriter(modelDirectory + className + ".py"),true);
			    	genPyModel(f);
			    	myFileWriter = new PrintWriter(new FileWriter(otwfileDirectory + className + "_OTW.py"),true);
			    	genOTWContent(className);
//			    	myFileWriter = new PrintWriter(new FileWriter(otwfileDirectory + className + "_OTW.java"),true);
			    	genBuildFile(className + ".proto");
			    }		    		    	
			}
		}		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {	
		GeneratePythonBOFiles writer = new GeneratePythonBOFiles("src/main/java/");	
		writer.genFile(new File("src/main/java/"));
		writer.genInit(new File("python/"));		
	}


}
