package com.purefun.fstp.core.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.purefun.fstp.core.bo.model.BOinstance;

public class TestGen {
	PrintWriter myFileWriter;
	private String myIndent = "";
	String protofileDirectory = null;
	String otwfileDirectory = null;
	String builderfileDirectory = null;
	
	public TestGen(String path) {
		protofileDirectory = path + "\\protofile\\";
		otwfileDirectory = path + "\\otw\\";
		builderfileDirectory = path + "\\builder\\";
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
		String[] str = line.substring(0, line.indexOf("=")).trim().split(" "); 
		StringBuilder fin = new StringBuilder();

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
		}else {
			System.out.println("con't find type:" + str[1]);
		}		
		fin.append(" ").append(str[2]).append(" = ").append(String.valueOf(i)).append(";");
		
		return fin;		
	}
	
	public void generateFile(File directory) throws IOException {
		File flist[] = directory.listFiles();
//		String protofileDirectory = directory.getPath() + "\\protofile\\";
				
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
			    	 System.out.println(protofileDirectory + className + ".proto");
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
		println(new StringBuilder().append("option java_package = \"").append("com.purefun.fstp.core.bo.proto").append("\";").toString());
		println("");
		println(new StringBuilder().append("message ").append(className).append(" {").toString());
				
		String line = null;
		int count = 1;
		while((line = br.readLine())!=null) {
			if(-1 == line.indexOf("=")) {
				continue;
			}
			if(-1 != line.indexOf("@")) {
				continue;
			}
			println(analysis(line,count++).toString());			
		}
		
		println("}");
	}

	private void genProtoFile(String inputfileName) {

	}

	public static void main(String[] args) throws IOException {			
		String bodir = "src/main/java/com/purefun/fstp/core/bo/";
		TestGen writer = new TestGen(bodir);
		writer.generateFile(new File(bodir));
		
//		String inputfileName = "TestBO";
//		String outfileName = inputfileName + ".proto";
//		
//		writer.myFileWriter = new PrintWriter(new FileWriter("D:/" +outfileName),true);
//		
//		InputStream input = new FileInputStream("D:\\project\\gitworkplace\\fstp\\fstp-core\\src\\main\\java\\com\\purefun\\fstp\\core\\bo\\" + inputfileName + ".java");
//		InputStreamReader inp = new InputStreamReader(input,"UTF-8");
//		BufferedReader br = new BufferedReader (inp);
//			
//		writer.println(new StringBuilder().append("option java_outer_classname = \"").append(inputfileName).append("_PRO\";").toString());
//		writer.println(new StringBuilder().append("option java_package = \"").append("com.purefun.fstp.core.bo.proto").append("\";").toString());
//		writer.println("");
//		writer.println(new StringBuilder().append("message ").append(inputfileName).append(" {").toString());
//				
//		String line = null;
//		int count = 1;
//		while((line = br.readLine())!=null) {
//			if(-1 == line.indexOf("=")) {
//				continue;
//			}
//			if(-1 != line.indexOf("@")) {
//				continue;
//			}
//			writer.println(writer.analysis(line,count++).toString());			
//		}
//		
//		writer.println("}");
	}


}
