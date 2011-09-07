package org.nirmalya.testproject;

import java.lang.reflect.Constructor;

public class PrintBall {
	
	public static void main(String [] args) {
		try {
			Class<?> myClass = Class.forName(args[0]);
			Constructor<?> ctr = myClass.getConstructor();			
			
			Ball finalObj = (Ball) ctr.newInstance();
			finalObj.printColor(args[1]);		
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
