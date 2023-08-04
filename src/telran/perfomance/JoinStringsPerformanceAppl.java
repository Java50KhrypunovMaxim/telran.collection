package telran.perfomance;

import java.util.Arrays;

import telran.test.JoinStringsOnBuilder;
import telran.test.JoinStringsOnStandard;
import telran.test.JoinStringsOnString;



public class JoinStringsPerformanceAppl {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;

	public static void main(String[] args) {
		String[] strings = getStrings();
		PerformanceTest onBuilderTest = getTest("JoinStringsOnBuilder", strings, new JoinStringsOnBuilder());
		PerformanceTest onStandardTest = getTest("JoinStringsOnStandard", strings, new JoinStringsOnStandard());
		PerformanceTest onStringTest = getTest("JoinStringsOnString", strings, new JoinStringsOnString());
		
		onBuilderTest.run();
		onStandardTest.run();
		onStringTest.run();
	}

	private static PerformanceTest getTest(String className, String[] strings, JoinStrings joinStrings) {
		String testName = getTestName(className);
		return new JoinStringsPerformanceTest(testName, N_RUNS, strings, joinStrings);
	}

	private static String getTestName(String className) {
		
		return String.format("%s; Number of the strings is %d", className, N_STRINGS);
	}

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		Arrays.fill(res, "string");
		return res;
	}

}