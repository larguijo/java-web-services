package com.arguijo.ws.soap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class FileWsImpl implements FileWs {

	@Override
	public void upload(DataHandler attachment) {
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			inputStream = attachment.getInputStream();
			os = new FileOutputStream(new File("C:\\code\\java-web-services\\files\\test.jpg"));
			byte[] b = new byte[100000];
			int bytesRead = 0;

			while ((bytesRead = inputStream.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DataHandler download() {
		return new DataHandler(new FileDataSource(new File("C:\\\\code\\\\java-web-services\\\\files\\\\test.jpg")));
	}

}
