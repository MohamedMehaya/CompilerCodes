package ay;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFollow {

	public static void main(String[] args) {
		String input = "S,SAB,SBC,e;A,aAa,e;B,bB,e;C,cC,e";
		CFG cfg = new CFG(input);
		String firstEncoding = cfg.First();
		String followEncoding = cfg.Follow();
		System.out.println("First: " + firstEncoding);
		System.out.println("Follow: " + followEncoding);
	}

	static class CFG {
		static ArrayList<Rule> RA;
		static String[][] Elkbeer;

		static String Input;

		public CFG(String input) {
			super();
			Input = input;
		}

		private static String First() {
			Input = Input.replaceAll("\\s+", "");
			// System.out.println(input);

			String[] parts = Input.split(";");
			int c1 = parts.length; // counter 1
			int c2 = 0; // counter 2
			int max = 0;

			String x = "";
			for (int i = 0; i < c1; i++) {
				x = parts[i];
				// System.out.println("x is "+x);
				for (int j = 0; j < x.length(); j++) {
					if (x.charAt(j) == ',') {
						c2++;
					}
				}
				c2++;
				// System.out.println("c2 is "+c2);
				if (c2 > max)
					max = c2;
				c2 = 0;
			}

			// System.out.println("final c1 is " + c1);
			// System.out.println("final max is " + max);

			Elkbeer = new String[c1][max];

			for (int i = 0; i < c1; i++) {
				x = parts[i];
				String[] parts2 = x.split(",");
				// System.out.println(Arrays.toString(parts2));
				for (int j = 0; j < parts2.length; j++) {
					Elkbeer[i][j] = parts2[j];
				}
			}

			CreateRules(Elkbeer, c1, max);
			FillRules(Elkbeer, c1, max);
			/*
			 * for (int i = 0; i < RA.size(); i++) {
			 * System.out.println(RA.get(i).toString()); }
			 */

			// Ascending First-s

			for (int i = 0; i < RA.size(); i++) {
				RA.get(i).setFirst(Ascendize(RA.get(i).getFirst()));
			}

			for (int i = 0; i < RA.size(); i++) {
				String Temp = RA.get(i).getFirst();
				Elkbeera: while (true) {
					boolean Flag = true;
					for (int j = 0; j < Temp.length() - 1; j++) {
						if (Temp.charAt(j) == Temp.charAt(j + 1)) {
							Flag = false;
							Temp = Temp.substring(0, j + 1) + Temp.substring(j + 2, Temp.length());
						}
					}
					if (Flag)
						break Elkbeera;
				}

				RA.get(i).setFirst(Temp);
			}

			// Creating FiString
			String FiString = "";
			for (int i = 0; i < RA.size(); i++) {
				FiString += RA.get(i).getLetter();
				FiString += ",";
				FiString += RA.get(i).getFirst();
				FiString += ";";
			}

			FiString = FiString.substring(0, FiString.length() - 1);

			return FiString;
		}

		private static String Ascendize(String first) {
			String str = first;
			char charArray[] = str.toCharArray();
			Arrays.sort(charArray);
			return (new String(charArray));
		}

		private static void CreateRules(String[][] Elkbeer, int c1, int max) {
			RA = new ArrayList<Rule>();
			String letter;
			int Number;
			boolean HE = false;
			for (int i = 0; i < c1; i++) {
				HE = false;
				letter = Elkbeer[i][0];
				Number = i;

				for (int j = 0; j < max; j++) {
					if (Elkbeer[i][j] != null && Elkbeer[i][j].contains("e"))
						HE = true;
				}

				Rule r = new Rule(letter, Number, HE, false, false, "", "");
				RA.add(r);
			}

			/*
			 * for (int i = 0; i < RA.size(); i++) {
			 * System.out.println(RA.get(i).toString()); }
			 */

		}

		private static void FillRules(String[][] Elkbeer, int c1, int max) {
			for (int i = 0; i < RA.size(); i++) {
				if (RA.get(i).isFiG() == false) {
					// System.out.println("Filling rule for " + i);
					RA.get(i).setFirst(GenerateFirst(RA.get(i), Elkbeer, i, max));

					RA.get(i).setFiG(true);
				}
			}

		}

		private static String GenerateFirst(Rule R, String[][] Elkbeer, int i, int max) {
			String letter = R.getLetter();
			// System.out.println(" ");
			// System.out.println("Generating First for " + letter);
			String x = ""; // String to bring small letters
			String y = ""; // String to bring First of another letter
			String finale = ""; // String to combine both strings

			for (int j = 1; j < max; j++) {
				String word = Elkbeer[i][j]; // One of the Rules
				if (word != null) {

					int Counter = 0;
					special: while (true) {

						if (word.charAt(Counter) >= 65 && word.charAt(Counter) <= 90) { // if first letter is a Capital
																						// letter
							if (!((word.charAt(Counter) + "").equals(letter))) {

								for (int j2 = 0; j2 < RA.size(); j2++) { // looping on RA to bring the first of the
																			// Capital
																			// letter or Generate it

									if ((RA.get(j2).getLetter().equals(word.charAt(Counter) + ""))) { // last edit
										if (!RA.get(j2).isFiG()) { // checking if the first of that letter isn't already
																	// generated
											Rule ru = RA.get(j2);

											y += GenerateFirst(ru, Elkbeer, ru.getNumber(), max);// Filling String y

											break;

										} else {
											y += RA.get(j2).getFirst();
											break;
										}
									}
								}

							} else if ((word.charAt(Counter + 1)) >= 97 && word.charAt(Counter + 1) <= 122) {
								y += (word.charAt(Counter + 1));
							}

							if (y.contains("e")) { // if y contains e
								// System.out.println("da5al 178 and The letter is " + letter);
								// System.out.println("word length is " + word.length());
								// System.out.println("Counter is " + Counter);

								if (word.length() - 1 > Counter) {
									// System.out.println("da5al 181 and The letter is " + letter);
									// if there are still letters to follow

									Counter++;
									if (word.charAt(Counter) >= 65 && word.charAt(Counter) <= 90) {
										// System.out.println("da5al 186 and The letter is " + letter);
									} else {
										y += word.charAt(Counter);
										break special;
									}
								} else {
									break special;
								}

							} else {
								break special;
							}

						}

						else { // if first letter is a small letter
							x += word.charAt(0);
							break special;
						}

					}
					// System.out.println("Special loop ended");
				}
			}

			finale = x + y;
			// System.out.println("finale is Generated");
			R.setFirst(finale);
			R.setFiG(true);
			// System.out.println("reached 208");
			return finale;
		}

		private static String Follow() {
			Input = Input.replaceAll("\\s+", "");
			// System.out.println(input);

			String[] parts = Input.split(";");
			int c1 = parts.length; // counter 1
			int c2 = 0; // counter 2
			int max = 0;

			String x = "";
			for (int i = 0; i < c1; i++) {
				x = parts[i];
				// System.out.println("x is "+x);
				for (int j = 0; j < x.length(); j++) {
					if (x.charAt(j) == ',') {
						c2++;
					}
				}
				c2++;
				// System.out.println("c2 is "+c2);
				if (c2 > max)
					max = c2;
				c2 = 0;
			}

			// System.out.println("final c1 is " + c1);
			// System.out.println("final max is " + max);

			Elkbeer = new String[c1][max];

			for (int i = 0; i < c1; i++) {
				x = parts[i];
				String[] parts2 = x.split(",");
				// System.out.println(Arrays.toString(parts2));
				for (int j = 0; j < parts2.length; j++) {
					Elkbeer[i][j] = parts2[j];
				}
			}

			// Making Elkbeer ^^

			for (int i = 0; i < RA.size(); i++) { // loop for generating follow for each rule
				if (!RA.get(i).isFoG()) {
					Rule rule = RA.get(i);
					GenerateFollow(rule, c1, max);
				}
			}

			// Printing rules

			/*
			 * System.out.println("Printing rules 235");
			 * 
			 * for (int i = 0; i < RA.size(); i++) {
			 * System.out.println(RA.get(i).toString()); }
			 */

			// Ascending Follow-s and removing epsilons and removing repeated letters and
			// changing $ position

			for (int i = 0; i < RA.size(); i++) {
				String Temp = RA.get(i).getFollow();
				Temp = Temp.replaceAll("e", "");
				Temp = Ascendize(Temp);

				// System.out.println("Temp for " + RA.get(i).getLetter() + " is " + Temp);

				Elkbeera: while (true) {
					boolean Flag = true;
					for (int j = 0; j < Temp.length() - 1; j++) {
						if (Temp.charAt(j) == Temp.charAt(j + 1)) {
							Flag = false;
							Temp = Temp.substring(0, j + 1) + Temp.substring(j + 2, Temp.length());
						}
					}
					if (Flag)
						break Elkbeera;
				}

				if (Temp.length()>0) {
					//System.out.println("Temp is "+Temp);
					//System.out.println("Temp length is "+Temp.length());
					if (Temp.charAt(0) == '$') {
						Temp = Temp.substring(1);
						Temp += "$";
					}
				}

				// System.out.println("Temp for " + RA.get(i).getLetter() + " is " + Temp);
				RA.get(i).setFollow(Temp);

			}

			// Creating FoString
			String FoString = "";
			for (int i = 0; i < RA.size(); i++) {
				FoString += RA.get(i).getLetter();
				FoString += ",";
				FoString += RA.get(i).getFollow();
				FoString += ";";
			}

			FoString = FoString.substring(0, FoString.length() - 1);

			return FoString;

		}

		private static String GenerateFollow(Rule rule, int c1, int max) {
			String fol = "";
			System.out.println("Generate Follow called for " + rule.getLetter());
			System.out.println(RA.get(0).isFoG());
			String letter = rule.getLetter();
			if (letter.equals("S"))
				fol = "$";

			for (int j = 0; j < c1; j++) { // accessing rules L S T
				for (int k = 1; k < max; k++) { // accessing Strings in rules
					String word = Elkbeer[j][k];
					if (word != null) {
						for (int m = 0; m < word.length(); m++) {

							if ((word.charAt(m) + "").equals(letter)) { // finding the Rule-Main Letter
								if (m != word.length() - 1) { // if not last letter
									if (word.charAt(m + 1) >= 97 && word.charAt(m + 1) <= 122) // Case 1 - Small letter
										fol += word.charAt(m + 1);
									else {
										for (int n = 0; n < RA.size(); n++) {
											if ((word.charAt(m + 1) + "").equals(RA.get(n).getLetter()))
												fol += RA.get(n).getFirst();
											// Add here to get the letter after if the string obtained contains epsilon
										}
									}
								} else if (!(letter.equals(Elkbeer[j][0]))) { // if last letter //mawde3 shak
									String LTBSF = Elkbeer[j][0]; // Letter to be searched for
									for (int p = 0; p < RA.size(); p++) {
										if (LTBSF.equals(RA.get(p).getLetter())) {
											if (RA.get(p).isFoG()) {
												fol += RA.get(p).getFollow();
											} /*else {
												fol += GenerateFollow(RA.get(p), c1, max);
											}*/
										}
									}

								}
							}

						}
					}
				}

			}
			// System.out.println("fol is " + fol);
			rule.setFollow(fol);

			rule.setFoG(true);

			return fol;

		}

	}

}
