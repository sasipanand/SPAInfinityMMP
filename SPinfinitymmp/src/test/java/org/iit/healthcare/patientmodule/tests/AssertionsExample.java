package org.iit.healthcare.patientmodule.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsExample {
	
	@Test
	public void hardAssertion()
	{
		
		boolean result = false;
		
		Assert.assertTrue(result);
		System.out.println("Hello");
	
		Assert.assertFalse(result);
		System.out.println("HelloWorld Everyone");
		
	}
	@Test
	public void softAssertion()
	{
		
		boolean result = false;
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(result);
		System.out.println("Hello");
	
		sa.assertFalse(result);
		System.out.println("HelloWorld");
		
		sa.assertAll();
		
	}
	

}