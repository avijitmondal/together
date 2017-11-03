package com.avijit.together.server;

import java.io.File;
import java.rmi.server.UID;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TogetherApplicationTests {

	@Ignore
	@Test
	public void contextLoads() {
		String userId = new UID().toString();

		// System.out.println(UUID.randomUUID() + userId.replaceAll(":", ""));
		StringBuilder str = new StringBuilder(UUID.randomUUID() + userId.replaceAll(":", ""));
		str.setCharAt(36, '_');
		// "34f5b616-9c6c-4278-bf20-0262dd74a982-5c58cfd515f2ea00bab-8000".substring(37)
		System.out.println(str);
		String name = "626c7073-7769-6a71-787a-6b6d61740000_252f7c5378c84fbe877672cdfb078e4f_110308.132";
		System.out.println(name.substring(70));
	}

	@Ignore
	@Test
	public void testGetFileExtension() {
		File file2 = new File("C://together//db//files//626c7073-7769-6a71-787a-6b6d61740000_252f7c5378c84fbe877672cdfb078e4f");
		if (!file2.exists()) {
			System.out.println("file with path: 626c7073-7769-6a71-787a-6b6d61740000_252f7c5378c84fbe877672cdfb078e4f was not found.");
		}
		System.out.println(file2.getName());

		File file = new File("/Users/pankaj/java.tar.gz");
		System.out.println("File extension is: " + getFileExtension(file));
		// file name without extension
		file = new File("/Users/pankaj/temp");
		System.out.println("File extension is: " + getFileExtension(file));
		// file name with dot
		file = new File("/Users/pankaj/java.util.txt");
		System.out.println("File extension is: " + getFileExtension(file));
		// hidden files without extension
		file = new File("/Users/pankaj/.htaccess");
		System.out.println("File extension is: " + getFileExtension(file));
	}
	
	@Ignore
	@Test
	public void testLocalDateTime() {
		String time = LocalTime.now().toString().replaceAll(":", "");
		System.out.println(time);
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
}
