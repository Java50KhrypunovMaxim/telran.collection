package telran.recursion;

public class LinearRecursion {
	public static void function(int a) {
		if(a > 3) {
			function(a - 1);
		}
		
	}
	public static long factorial(int n) {
		long res = 1;
		if (n < 0) {
			throw new IllegalArgumentException("factorial exists only for positive numbers and 0");
		}
		if (n > 0) {
			res = n * factorial(n - 1);
		}
		return res;
		
	}
	public static long pow(int a, int b) {
	    long res = 1;
		if (b < 0) {
	        throw new IllegalArgumentException("degree cannot be negative");
	    }
		if (b == 0)
		{
			return res;
		}
		if (b == 1)
		{
			return a;
		}
		
		if (b > 0) {
			res = degree(a, pow(a, b - 1));
		}
		return res;
	}

	private static int degree(int a, long b) {

		int res = 0;
		if (b != 0) {
			if (b > 0) {
		        res = a + degree(a, b - 1); 
		    } else {
		       res = degree(-a, -b);
		    }
		}
		return res;
	}


	
	private static long add(long x, long y) {
	    if (y == 0) {
	        return x;
	    } else {
	
	        return add(++x, --y);
	    }
	}
	
	public static void displayArray(int[] ar) {
		displayArray(0, ar, false);
	}
private static void displayArray(int index, int[] ar, boolean isReverse) {
		if (index < ar.length) {
			if (isReverse) {
				displayArray(index + 1, ar, isReverse);
				System.out.print(ar[index] + " ");
			} else {
				System.out.print(ar[index] + " ");
				displayArray(index + 1, ar, isReverse);
			}
		}
		
	}
public static void displayReversedArray(int[] ar) {
	displayArray(0, ar, true);
	}

public static long sumArray(int []array) {
	return sumArray(0, array);
}
private static long sumArray(int index, int[] array) {
	long res = 0;
	if (index < array.length) {
		res = array[index] + sumArray(index + 1, array);
	}
	return res;
}
public static void reverseArray(int[] array) {
	reverseArray(0, array, array.length -1);
}
private static void reverseArray(int left, int[] array, int right) {
	if(left < right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
		reverseArray(left + 1, array, right - 1);
	}
	
}
public static int square(int x) {
	int res = 0; 
	if (x != 0) {
	    if (x < 0) {
	        res = square(-x);
	    } else {
	        res = x + x - 1 + square(x - 1);
	    }
	}
	return res;
}

public static boolean isSubstring(String string, String substr) { 

	boolean res = false;
	if (string.length() >= substr.length()) {
	    if (isEqual(string, substr)) {
	        res = true;
	    } else {
	        res = isSubstring(string.substring(1), substr);
	    }
	}
	return res;

}

private static boolean isEqual(String str, String substr) {
	boolean res = false;
	if (substr.length() == 0) {
		res = true;
	} else if (str.charAt(0) == substr.charAt(0)) {
		res = isEqual(str.substring(1), substr.substring(1));
	}
	
	return res;
}
}
