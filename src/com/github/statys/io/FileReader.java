package com.github.statys.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileReader {
	
	private static FileReader instance;
	
	private FileReader() {
		
	}
	
	public synchronized static FileReader getInstance() {
		if(FileReader.instance == null) {
			FileReader.instance = new FileReader();
		}
		
		return FileReader.instance;
	}
	
	public String getFileContentAsString(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		
		List<Byte> buffer = new ArrayList<Byte>();
		
		int readByte;
		
		while((readByte = fis.read()) != -1) {
			buffer.add((byte)readByte);
		}
		
		fis.close();
		
		byte[] b = new byte[buffer.size()];

		for(int i = 0; i < b.length; i++) {
			b[i] = buffer.get(i);
		}
		
		String finalString = new String(b);
		
		return finalString;
	}
	
}
