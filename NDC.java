package ay;

import java.util.ArrayList;
import java.util.HashSet;

public class NDC {
	static String part1;
	static String part2;
	static String part3;
	static String part4;
	static String SN;
	static NState[] StatesArray;
	static HashSet<String> h;
	static ArrayList<NState> a;
	static NState CS;

	public static void main(String[] args) {
		/*createStatesN("0,0;0,1;1,3#1,2;2,4;4;4#0,1;3,4#3,4");
		Runw5alasna("0010");//false
		Runw5alasna("0111");//true
		Runw5alasna("1010");//false
		Runw5alasna("1111");//true
		Runw5alasna("0110");//false*/
		
		
		createStatesN("0,1;1,3;4,5;5,5#1,2;2,4;4;4#0,1;3,4#5");
		Runw5alasna("1011");//false
		Runw5alasna("01100");//true
		Runw5alasna("000111");//false
		Runw5alasna("010");//true
		Runw5alasna("1111");//false
		
		
		
		
		
	}

	public static void createStatesN(String input) {
		// System.out.println("createStatesN");

		// filtering string to find max
		String emad = input.replaceAll(",", "");
		emad = emad.replaceAll(";", "");
		emad = emad.replaceAll("#", "");

		int max = -1;
		for (int i = 0; i < emad.length(); i++) {
			if (Integer.parseInt(emad.charAt(i) + "") > max)
				max = Integer.parseInt(emad.charAt(i) + "");
		}
		// Creating NFA states
		StatesArray = new NState[max + 1];
		for (int i = 0; i <= max; i++) {
			StatesArray[i] = new NState(i + "", "-1", "-1", "-1");
		}

		String[] parts = input.split("#");
		part1 = parts[0]; // 0 transitions
		part2 = parts[1]; // 1 transitions
		part3 = parts[2]; // eps transitions
		part4 = parts[3]; // Accept States

		part1 = part1.replaceAll(",", "");
		part1 = part1.replaceAll(";", "");

		part2 = part2.replaceAll(",", "");
		part2 = part2.replaceAll(";", "");

		part3 = part3.replaceAll(",", "");
		part3 = part3.replaceAll(";", "");

		part4 = part4.replaceAll(",", "");

		int j = 0, k = 1;
		int x;
		String z;

		for (int i = 0; i < part1.length() / 2; i++) {
			x = Integer.parseInt("" + part1.charAt(j));
			z = "" + part1.charAt(k);
			StatesArray[x].setZero(z);
			j += 2;
			k += 2;
		}

		j = 0;
		k = 1;
		for (int i = 0; i < part2.length() / 2; i++) {
			x = Integer.parseInt("" + part2.charAt(j));
			z = "" + part2.charAt(k);
			StatesArray[x].setOne(z);
			j += 2;
			k += 2;
		}

		// Eps generation code

		j = 0;
		k = 1;
		for (int i = 0; i < part3.length() / 2; i++) {
			if (part3.charAt(j) != part3.charAt(k)) {
				x = Integer.parseInt("" + part3.charAt(j));
				z = "" + part3.charAt(k);
				StatesArray[x].setEps(z);
			}
			j += 2;
			k += 2;
		}

		createStatesD();

	}

	public static void createStatesD() {
		// System.out.println("createStatesD");

		h = new HashSet<String>();
		a = new ArrayList<NState>();

		NState Start_State = StatesArray[0];
		String NSN = Start_State.name;

		if (Start_State.eps.length() == 1)
			NSN += Start_State.getEps();
		else if (Start_State.getEps() != "-1") {
			NSN += Start_State.getEps();
			for (int i = 0; i < Start_State.eps.length(); i++) {
				if (StatesArray[Start_State.eps.charAt(i)].eps != "-1")
					NSN += StatesArray[Start_State.eps.charAt(i)].getEps();
			}
		}
		
		//NSN Hashset
		String y="";
		HashSet<Character> valid = new HashSet<Character>();
		for(int i=0;i<NSN.length();i++) {
			if(!valid.contains(NSN.charAt(i))) {
				valid.add(NSN.charAt(i));
				y+=NSN.charAt(i);
			}	
		}
		NSN=y;
		
		
		SN = NSN;
		// System.out.println("NSN is " + NSN);

		if (!h.contains(NSN)) // useless
			h.add(NSN); // useful

		Et3amelYabny(NSN);

	}

	public static void Et3amelYabny(String NSN) {
		// System.out.println("Et3amelYabny");
		String Zs = "", Os = "";

		/*
		 * System.out.println(" "); System.out.println("New NSN");
		 * System.out.println("NSN is "+NSN);
		 */

		for (int i = 0; i < NSN.length(); i++) {

			// Upper Part

			NState m = StatesArray[Integer.parseInt(NSN.charAt(i) + "")];
			// System.out.println("m is "+m);
			if (m.getZero() != "-1") {
				Zs += m.getZero();
				// System.out.println("Upper modification Zs: "+Zs);

				// Lower Part

				for (int j = 0; j < m.getZero().length(); j++) {
					// System.out.println("m.getZero() is "+m.getZero());
					char c = m.getZero().charAt(j);
					if (c != '-' && StatesArray[Integer.parseInt(c + "")].getEps() != "-1") {
						// System.out.println("m is "+m);
						// System.out.println("c is "+c);
						Zs += StatesArray[Integer.parseInt(c + "")].getEps();
						// System.out.println("Lower modification Zs: "+Zs);
					}
				}
			}
		}

		for (int i = 0; i < Zs.length() - 1; i++) {
			if (Zs.charAt(i) == Zs.charAt(i + 1))
				Zs = Zs.substring(0, i + 1) + Zs.substring(i + 2, Zs.length());
		}

		// System.out.println("Zs is " + Zs);

		String y="";
		HashSet<Character> valid = new HashSet<Character>();
		for(int i=0;i<Zs.length();i++) {
			if(!valid.contains(Zs.charAt(i))) {
				valid.add(Zs.charAt(i));
				y+=Zs.charAt(i);
			}	
		}
		Zs=y;
		
		
		
		
		
		if (!h.contains(Zs) && a.size() < 11) {
			h.add(Zs);
			// a.add(new State(Zs, "-1", "-1", "-1"));
			Et3amelYabny(Zs);
		}

		for (int i = 0; i < NSN.length(); i++) {

			NState m = StatesArray[Integer.parseInt(NSN.charAt(i) + "")];
			// System.out.println("m is " + m);
			if (m.getOne() != "-1") {
				Os += m.getOne();
				for (int j = 0; j < m.getOne().length(); j++) {
					if (m.getOne().charAt(j) != '-'
							&& StatesArray[Integer.parseInt(m.getOne().charAt(j) + "")].getEps() != "-1") {
						// System.out.println("da5al");
						Os += StatesArray[Integer.parseInt(m.getOne().charAt(j) + "")].getEps();
					}
				}
			}
		}

		for (int i = 0; i < Os.length() - 1; i++) {
			if (Os.charAt(i) == Os.charAt(i + 1))
				Os = Os.substring(0, i + 1) + Os.substring(i + 2, Os.length());
		}
		
		
		String yy="";
		HashSet<Character> valid2 = new HashSet<Character>();
		for(int i=0;i<Os.length();i++) {
			if(!valid2.contains(Os.charAt(i))) {
				valid2.add(Os.charAt(i));
				yy+=Os.charAt(i);
			}	
		}
		Os=yy;
		
		if (!h.contains(Os) && a.size() < 11) {
			h.add(Os);
			// a.add(new State(Os, "-1", "-1", "-1"));
			Et3amelYabny(Os);
		}

		// System.out.println("Os is " + Os);

		NState n = new NState(NSN, Zs, Os, "-1");

		a.add(n);
		// System.out.println("n is " + n);

	}

	public static void Runw5alasna(String e) {
		// System.out.println("Runw5alasna");

		// System.out.println(" ");

		/*
		 * for(int i=0;i<a.size();i++) { System.out.println(a.get(i)); }
		 */

		boolean Flag = false;
		a.add(new NState("dead", "-1", "-1", "-1"));
		// System.out.println("Tamam");

		for (NState state : a) {
			if (state.getName() == SN) {
				CS = state;
				break;
			}
		}
		// System.out.println("CS is " + CS);

		for (int i = 0; i < e.length(); i++) {
			// System.out.println(" ");
			// System.out.println(":D");
			Flag = false;
			// System.out.println(e.charAt(i) == '0'?true:false);
			// System.out.println(CS.getName() != "dead"?true:false);

			if (e.charAt(i) == '0' && CS.getName() != "dead") {
				// System.out.println("Amr");

				// System.out.println("e is "+e.charAt(i));
				for (NState state : a) {
					// System.out.println(' ');
					// System.out.println(state.getName());
					// System.out.println(CS.getZero());

					// System.out.println(state.getName()!=CS.getZero());
					if (state.getName().equals(CS.getZero())) {
						Flag = true;
						// System.out.println(Flag);
						// System.out.println("CS is " + CS);
						CS = state;
						break;
					}
				}
				if (Flag == false) {
					for (NState st : a) {
						if (st.getName().equals("dead")) {
							// System.out.println("resolve");
							// System.out.println("CS is " + CS);
							CS = st;
							break;
						}
					}
				}

			} else if (e.charAt(i) == '1' && CS.getName() != "dead") {
				// System.out.println("Wael");
				for (NState state : a) {
					if (state.getName().equals(CS.getOne())) {
						Flag = true;
						// System.out.println("CS is " + CS);
						CS = state;
						break;
					}
				}
				if (Flag == false) {
					for (NState st : a) {
						if (st.getName().equals("dead")) {
							// System.out.println("CS is " + CS);
							CS = st;
							break;
						}
					}
				}
			}
		}
		//System.out.println("End State is "+CS);
		String EndString = CS.getName();
		Boolean f2 = false;
		char s;
		if (CS.getName().equals("dead")) {
			System.out.println("false");
			return;
		} else {
			Elkbeera: for (int i = 0; i < EndString.length(); i++) {
				s = EndString.charAt(i);
				for (int j = 0; j < part4.length(); j++) {
					if (s == part4.charAt(j)) {
						System.out.println("true");
						f2 = true;
						break Elkbeera;
					}
				}
			}
			if (!f2) {
				System.out.println("false");
				return;
			}
		}

	}

}
