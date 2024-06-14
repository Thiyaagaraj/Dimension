package testCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testNg;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> suites = new ArrayList<String>(); testNg = new TestNG();

		suites.add("./Dimension_TestNG.xml"); 
		testNg.setTestSuites(suites); 
		testNg.run();
	}
}
