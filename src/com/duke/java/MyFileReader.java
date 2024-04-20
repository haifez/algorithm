package com.duke.java;

import java.io.File;
import java.util.*;

public class MyFileReader {
	private String fileName;

	public MyFileReader(String fileName) {
		this.fileName = fileName;

	}

	public List<String> getAllLines() throws Exception {
		ArrayList<String> l = new ArrayList<>();

		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			l.add(nextLine);
		}
		scanner.close();
		return l;
	}

}
