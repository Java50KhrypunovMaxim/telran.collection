package telran.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class Strings {

	static HashMap<String, BinaryOperator<Double>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("+", (a, b) -> a + b);
		mapOperations.put("-", (a, b) -> a - b);
		mapOperations.put("*", (a, b) -> a * b);
		mapOperations.put("/", (a, b) -> a / b);
	}

	static public String javaVariable() {

		return "[a-zA-Z$][\\w$]*|_[\\w$]+";

	}

	static public String zero_300() {

		return "[1-9]\\d?|[1-2]\\d\\d|300|0";
	}

	static public String ipV4Octet() {
		return "\\d\\d?|[0-1]\\d\\d|2([0-4]\\d|5[0-5])";
	}

	static public String ipV4Address() {
		String octet = ipV4Octet();
		return String.format("(%s)(\\.(%s)){3}", octet, octet);
	}

	public static String arithmeticExpression() {
		String operandRE = operand();
		String operatorRE = operator();
		return String.format("%1$s(%2$s%1$s)*", operandRE, operatorRE);
	}

	public static String operator() {
		return "([-+*/])";
	}

	private static String operand() {
		String numberExp = numberExp();
		String variableExp = javaVariable();
		return String.format("(\\s*\\(*\\s*)*((%s|%s))(\\s*\\)*\\s*)*", numberExp, variableExp);
	}

	private static String numberExp() {
		return "(\\d+\\.?\\d*|\\.\\d+)";
	}

	public static boolean isArithmericExpression(String expression) {
		boolean res = false;
		if (brecketsPairsValidation(expression)) {
			res = expression.matches(arithmeticExpression());
		}
		return res;
	}

	private static boolean brecketsPairsValidation(String expression) {
		boolean res = true;
		int count = 0;
		char[] chars = expression.toCharArray();
		int index = 0;
		while (index < chars.length && res) {
			if (chars[index] == '(') {
				count++;
			} else if (chars[index] == ')') {
				count--;
				if (count < 0) {
					res = false;
				}
			}
			index++;
		}
		if (res) {
			res = count == 0;
		}
		return res;
	}

	public static double calculation(String expression, Map<String, Double> variableValues) {
		if (!isArithmericExpression(expression)) {
			throw new IllegalArgumentException("Wrong arithmetic syntax");
		}
		expression = expression.replaceAll("[()\\s]+", "");// removing brackets and spaces
		String[] operators = expression.split(operand());
		String[] operands = expression.split(operator());
		double res = getValue(operands[0], variableValues);
		for (int i = 1; i < operands.length; i++) {
			double operand = getValue(operands[i], variableValues);
			res = mapOperations.get(operators[i]).apply(res, operand);
		}
		return res;
	}

	private static double getValue(String operand, Map<String, Double> variableValues) {
		double res = 0;
		if (operand.matches(numberExp())) {
			res = Double.parseDouble(operand);
		}
		else if (operand.matches(javaVariable())) {
			if (!variableValues.containsKey(operand)) {
				throw new NoSuchElementException();
			}
			res = variableValues.get(operand);
		}
		return res;
	}

}