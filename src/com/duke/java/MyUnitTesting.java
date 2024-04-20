package com.duke.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

public class MyUnitTesting {

	// test git checking, testing

	public static void main(String[] args) throws Exception {

		// testing reading from file
		// the file has to be existing!!!
		String fileName = "C:\\Users\\Dad\\Documents\\testFile.txt";		
		MyFileReader r = new MyFileReader(fileName);
		for (String l: r.getAllLines()) {
			System.out.println(l);
		}

		System.out.println("this is a testing! testing again!!!!");

	}

}
