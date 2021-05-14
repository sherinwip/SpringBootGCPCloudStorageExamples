package com.gcp.cloud.poc.uploaddownload.controller;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Storage;

@RestController
public class FileController {
	@Autowired
	private Storage storage;
	
	@GetMapping
	public String readFile() throws Exception {
		
		
		StringBuilder sb = new StringBuilder();
		try (ReadChannel channel = storage.reader("fileuploaddownload","3 in account-1.pdf")){
			
			ByteBuffer bytes = ByteBuffer.allocate(1024*64);
			while(channel.read(bytes)>0) {
				bytes.flip();
				String data = new String(bytes.array(),0,bytes.limit());
				sb.append(data);
				bytes.clear();
				
			}
		
		}
		return sb.toString();
		}
}