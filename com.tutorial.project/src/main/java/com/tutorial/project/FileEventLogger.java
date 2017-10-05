/**
 * FileEventLogger has the method "init()" to initialize bean's state that's called after 
 * bean is created and all properties are set. In this method we create a text file, checking 
 * some conditions of a file in advance and then write strings to it. 
 */
package com.tutorial.project;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileEventLogger {

	public FileEventLogger(String fileName) {
		super();
		this.fileName = fileName;
	}
	private String fileName;
	private File file;
	
	public void init(){
		file = new File(fileName);
		if(file.exists() && !file.canWrite()){
			throw new IllegalArgumentException("Cannot write to the file" + " " + file);
		}else if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IllegalArgumentException("The file cannot be created" + " " + e);
			}
		}
	}
	
	// Using FileUtils from commons-io to write strings to the file
		public void logEvent(Event event) {

			try{
			    FileUtils.writeStringToFile(file, event.toString(), true);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
}
