package discrete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.management.RuntimeErrorException;

public class truthTableCons {

	/**
	 * @author user
	 *
	 */
	public static final int MAX = 10000000;
	/**
	 * @author user
	 *
	 */
	public static int l1 = 0;
	/**
	 * @author user
	 *
	 */
	public static int l2 = 0;
	/**
	 * @author user
	 *
	 */
	public static int counter = 0;
	/**
	 * @author user
	 *
	 */
	public static char[] usedVariables;
	/**
	 * @author user
	 *
	 */
	public static char[] temp = new char[MAX];
	/**
	 * @author user
	 *
	 */
	static ArrayList<ArrayList<Integer>> variables = new ArrayList<ArrayList<Integer>>();

	public static boolean isoperator(final char a) {
		if (a == '^' || a == 'v' || a == '!' || a == '>' || a == '<') {
			return true;
		}
		return false;
	}

	public static void initializeVariables(int number) {
		int size = (int) Math.pow((double) 2, (double) number);
		int power = 1;
		for (int i = 0; i < number; i++) {
			ArrayList<Integer> variableValues = new ArrayList<Integer>();
			int check = (int) (size / Math.pow(2, power));
			int s = 0;
			int value2 = 0;
			for (int j = 0; j < size; j++) {
				if (s == check) {
					s = 0;
					if (value2 == 0) {
						value2 = 1;
					} else {
						value2 = 0;
					}
				}
				variableValues.add(value2);
				s++;
			}
			variables.add(variableValues);
			power++;
		}

	}

	public static String infixToPostfix(final String expression) {
		// TODO Auto-generated method stub
		String myExpression = expression;
		Stack<Character> postfix = new Stack<Character>();

		for (int i = 0; i < myExpression.length(); i++) {
			Character a = myExpression.charAt(i);
			if (a == '!' && a != ' ') {
				if (i == myExpression.length() - 1) {
					throw new RuntimeErrorException(null);
				}
				if (postfix.isEmpty()) {
					postfix.push(myExpression.charAt(i));
				} else {
					Character m = (char) postfix.peek();
					while (m == '!') {
						Character n = (char) postfix.pop();
						temp[counter++] = n;
						if (i != myExpression.length() - 1) {
							temp[counter++] = ' ';
						}
						if (postfix.isEmpty()) {
							break;
						}
					}
					postfix.push(myExpression.charAt(i));
				}
			} else if (a == '^' && a != ' ') {
				if (i == 0 || i == myExpression.length() - 1) {
					throw new RuntimeErrorException(null);
				}
				if (postfix.isEmpty()) {
					postfix.push(myExpression.charAt(i));
				} else {
					Character q = (char) postfix.peek();
					while (q == '^' || q == '!') {
						temp[counter++] = (char) postfix.pop();
						if (i != myExpression.length() - 1) {
							temp[counter++] = ' ';
						}
						if (postfix.isEmpty()) {
							break;
						}
						q = (char) postfix.peek();
					}
					postfix.push(myExpression.charAt(i));
				}
			} else if (a == 'v' && a != ' ') {
				if (i == 0 || i == myExpression.length() - 1) {
					throw new RuntimeErrorException(null);
				}
				if (postfix.isEmpty()) {
					postfix.push(myExpression.charAt(i));
				} else {
					while ((char) postfix.peek() == '!' || (char) postfix.peek() == '^'
							|| (char) postfix.peek() == 'v') {
						temp[counter++] = (char) postfix.pop();
						if (i != myExpression.length() - 1) {
							temp[counter++] = ' ';
						}
						if (postfix.isEmpty()) {
							break;
						}
					}
					postfix.push(myExpression.charAt(i));
				}
			} else if (a == '>' && a != ' ') {
				if (i == 0 || i == myExpression.length() - 1) {
					throw new RuntimeErrorException(null);
				}
				if (postfix.isEmpty()) {
					postfix.push(myExpression.charAt(i));
				} else {
					while ((char) postfix.peek() == '!' || (char) postfix.peek() == '^' || (char) postfix.peek() == 'v'
							|| (char) postfix.peek() == '>') {
						temp[counter++] = (char) postfix.pop();
						if (i != myExpression.length() - 1) {
							temp[counter++] = ' ';
						}
						if (postfix.isEmpty()) {
							break;
						}
					}
					postfix.push(myExpression.charAt(i));
				}
			} else if (a == '<' && a != ' ') {
				if (i == 0 || i == myExpression.length() - 1) {
					throw new RuntimeErrorException(null);
				}
				if (postfix.isEmpty()) {
					postfix.push(myExpression.charAt(i));
				} else {
					while ((char) postfix.peek() == '!' || (char) postfix.peek() == '^' || (char) postfix.peek() == 'v'
							|| (char) postfix.peek() == '>' || (char) postfix.peek() == '<') {
						temp[counter++] = (char) postfix.pop();
						if (i != myExpression.length() - 1) {
							temp[counter++] = ' ';
						}
						if (postfix.isEmpty()) {
							break;
						}
					}
					postfix.push(myExpression.charAt(i));
				}

			} else if (a == '(' && a != ' ') {
				postfix.push(myExpression.charAt(i));
			} else if (a == ')' && a != ' ') {

				while ((char) postfix.peek() != '(') {
					temp[counter++] = (char) postfix.pop();
					if (i != myExpression.length() - 1) {
						temp[counter++] = ' ';
					}
				}
				postfix.pop();
			} else {
				if (myExpression.charAt(i) != ' ') {
					temp[counter++] = myExpression.charAt(i);
					temp[counter++] = ' ';
				}
			}
		}
		while (!postfix.isEmpty()) {
			temp[counter++] = (char) postfix.pop();
			if (!postfix.isEmpty()) {
				temp[counter++] = ' ';
			}
		}
		char[] temp3 = new char[counter];
		for (int k = 0; k < counter; k++) {
			temp3[k] = temp[k];
		}
		String post = new String(temp3);
		if (post.length() == 0) {
			throw new RuntimeErrorException(null);
		}
		temp = new char[MAX];
		counter = 0;
		return post;
	}

	public static ArrayList<Integer> evaluate(final String expression) {
		// TODO Auto-generated method stub
		Stack<ArrayList<Integer>> ev = new Stack<ArrayList<Integer>>();
		if (expression.length() == 0 || expression == null) {
			throw new RuntimeErrorException(null);
		}
		ArrayList<Integer> var1;
		ArrayList<Integer> var2;
		ArrayList<Integer> ans = null;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) != ' ') {
				if (!isoperator(expression.charAt(i))) {
					if (expression.charAt(i + 1) == ' ') {
						char l = expression.charAt(i);
						int index1 = 0;
						for (int i1 = 0; i1 < usedVariables.length; i1++) {
							if (l == usedVariables[i1]) {
								index1 = i1;
								break;
							}
						}
						ArrayList<Integer> temp1 = variables.get(index1);
						ev.push(temp1);
					} else {

					}
				} else {

					if (ev.isEmpty()) {
						throw new RuntimeErrorException(null);
					}
					if (expression.charAt(i) == '!') {
						var2 = (ArrayList<Integer>) ev.pop();
						ArrayList<Integer> finalans = new ArrayList<Integer>();
						for (int k = 0; k < var2.size(); k++) {
							if (var2.get(k) == 0) {
								finalans.add(1);
							} else {
								finalans.add(0);
							}
						}
						ans = finalans;

					}

					else {
						var2 = (ArrayList<Integer>) ev.pop();

						if (ev.isEmpty()) {
							throw new RuntimeErrorException(null);
						}

						var1 = (ArrayList<Integer>) ev.pop();

						if (expression.charAt(i) == '^') {

							ArrayList<Integer> finalans = new ArrayList<Integer>();
							for (int k = 0; k < var1.size(); k++) {
								finalans.add(var1.get(k) & var2.get(k));
							}

							ans = finalans;
						}
						if (expression.charAt(i) == 'v') {

							ArrayList<Integer> finalans = new ArrayList<Integer>();
							for (int k = 0; k < var1.size(); k++) {
								finalans.add(var1.get(k) | var2.get(k));
							}

							ans = finalans;
						}
						if (expression.charAt(i) == '>') {

							ArrayList<Integer> finalans = new ArrayList<Integer>();
							for (int k = 0; k < var1.size(); k++) {
								if (var1.get(k) == 0) {
									finalans.add(1);
								} else if (var1.get(k) == 1 && var2.get(k) == 1) {
									finalans.add(1);
								} else {
									finalans.add(0);
								}
							}

							ans = finalans;
						}
						if (expression.charAt(i) == '<') {

							ArrayList<Integer> finalans = new ArrayList<Integer>();
							for (int k = 0; k < var1.size(); k++) {
								if (var1.get(k) == 1 && var2.get(k) == 1) {
									finalans.add(1);
								} else if (var1.get(k) == 0 && var2.get(k) == 0) {
									finalans.add(1);
								} else {
									finalans.add(0);
								}
							}

							ans = finalans;
						}
					}
					ev.push(ans);
				}
			}
		}
		if (ev.size() == 0) {
			throw new RuntimeErrorException(null);
		}
		if (ev.size() > 1) {
			return null;
		} else {
			return (ArrayList<Integer>) ev.pop();
		}
	}

	public static String checkTatulogyAndContradiction(ArrayList<Integer> req) {
		boolean checkTatoulgy = true;
		boolean checkContradiction = true;
		for (int i = 0; i < req.size(); i++) {
			if (req.get(i) == 0) {
				checkTatoulgy = false;
			}
			if (req.get(i) == 1) {
				checkContradiction = false;
			}
		}
		if (checkTatoulgy) {
			return "Tatoulgy";
		} else if (checkContradiction) {
			return "Contradiction";
		}
		return "Niether Nor";
	}

	public static boolean checkEquivalence(ArrayList<Integer> req1, ArrayList<Integer> req2) {
		boolean checkEquivalence = true;
		for (int i = 0; i < req1.size(); i++) {
			if (req1.get(i) != req2.get(i)) {
				checkEquivalence = false;
			}
		}
		return checkEquivalence;
	}

	public static boolean checkInput(String expression) {
		if (expression.length() == 0 || expression == null) {
			return false;
		}
		for (int j = 0; j < expression.length(); j++) {
			if (expression.charAt(j) == '(') {
				l1++;
			}
			if (expression.charAt(j) == ')') {
				l2++;
			}
		}
		if (l1 != l2) {
			return false;
		}
		int count = 0;
		for (int i = 0; i < usedVariables.length; i++) {
			if (expression.charAt(i) == '>' || expression.charAt(i) == '<' || expression.charAt(i) == '^'
					|| expression.charAt(i) == 'v') {
				count++;
			}
		}
		if ((usedVariables.length - 1) != count) {
			return false;
		}
		return true;

	}

	public static void save(ArrayList<ArrayList<Integer>> truthTable, String expression) throws IOException {
		XML xml = new XML(truthTable);
		xml.serializeToXML(expression);
	}

}
