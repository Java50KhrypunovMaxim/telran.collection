package telran.strings;


public class Strings {
static public String javaVariable() {
	
	return "[a-zA-z$][\\w$]*|_[\\w$]+";
}

static public String zero_300 () {
	
	return "[1-9]\\d?|[1-2]\\d\\d|300|0";
}

static public String ipV4Octet()
{
	return "\\d\\d?|[0-1]\\d\\d|2([0-4]\\d|5[0-5])";
}

static public String ipV4Address()
{
	String octet = ipV4Octet();
	return String.format("(%s)(\\.(%s)){3}", octet, octet);
}
static public String arithmeticExpression()
{
	String operator = operator();
	String operand = operand();
	return String.format("\\s*(%s)*(%s%s)*\\s*",operand, operator, operand);
}
public static String operator()
{
	return "\\s*[-+*/]\\s*";
}
public static String operand()
{
	String number = anyNumber ();
	String variable = variableName ();
	return String.format("(%s|%s)", number, variable);
}

public static String anyNumber ()
{
	return "(\\d+\\.?\\d*|\\.\\d+)";
}
public static String  variableName() {
	
	return "([a-zA-Z$][a-zA-Z$]*|_[_]*[\\w$]+)";
}

}