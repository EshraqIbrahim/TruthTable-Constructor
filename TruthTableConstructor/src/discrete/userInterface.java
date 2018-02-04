package discrete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class userInterface {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> f = null;
		String s1 = null;
		ArrayList<Integer> f2 = null;
		System.out.println("Enter the number of your propositions");
		int n = scan.nextInt();
		truthTableCons.usedVariables = new char[n];
		System.out.println("Enter your propositions charachters");
		for (int i = 0; i < n; i++) {
			char input = scan.next().charAt(0);
			truthTableCons.usedVariables[i] = input;
		}
		truthTableCons.initializeVariables(n);
		System.out.println("Enter your first Expression");
		String s = scan.next();
		if (!truthTableCons.checkInput(s)) {
			System.out.println("Invalid Input");
		} else {
			String ans = truthTableCons.infixToPostfix(s);
			f = truthTableCons.evaluate(ans);
			String z = "";
			for (int i = 0; i < truthTableCons.usedVariables.length; i++) {
				z += truthTableCons.usedVariables[i] + " ";
			}
			System.out.println(z + s);
			for (int i = 0; i < f.size(); i++) {
				String ans2 = "";
				for (int j = 0; j < truthTableCons.variables.size(); j++) {
					if (j == truthTableCons.variables.size() - 1) {
						ans2 += truthTableCons.variables.get(j).get(i) + "\t";
					} else {
						ans2 += truthTableCons.variables.get(j).get(i) + " ";
					}

				}

				System.out.println(ans2 + f.get(i));

			}
			System.out.println("Check Tatoulogy or Contradiction for " + s + " : "
					+ truthTableCons.checkTatulogyAndContradiction(f));
			System.out.println("Enter your second Expression or -1 if nothing");
			// String s1 = "(pvq)v((!p)^(!q))";
			s1 = scan.next();
			if (!truthTableCons.checkInput(s1)) {
				System.out.println("Invalid Input");
			} else {

				if (!s1.equals("-1")) {
					String ans3 = truthTableCons.infixToPostfix(s1);
					f2 = truthTableCons.evaluate(ans3);
					String z2 = "";
					for (int i = 0; i < truthTableCons.usedVariables.length; i++) {
						z2 += truthTableCons.usedVariables[i] + " ";
					}
					System.out.println(z2 + s1);
					for (int i = 0; i < f2.size(); i++) {
						String ans22 = "";
						for (int j = 0; j < truthTableCons.variables.size(); j++) {
							if (j == truthTableCons.variables.size() - 1) {
								ans22 += truthTableCons.variables.get(j).get(i) + "\t";
							} else {
								ans22 += truthTableCons.variables.get(j).get(i) + " ";
							}

						}
						System.out.println(ans22 + f2.get(i));
					}
					System.out.println("Check Tatoulogy or Contradiction for " + s1 + " : "
							+ truthTableCons.checkTatulogyAndContradiction(f2));
					System.out.println("Check Equivelance: " + truthTableCons.checkEquivalence(f, f2));
				}
			}
		}
		System.out.println("saving the truth table of the both....");
		truthTableCons.variables.add(f);
		truthTableCons.save(truthTableCons.variables, s);
		truthTableCons.variables.remove(truthTableCons.variables.size() - 1);
		truthTableCons.variables.add(f2);
		truthTableCons.save(truthTableCons.variables, s1);
		scan.close();
	}

}
