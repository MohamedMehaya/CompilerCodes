package ay;

import java.util.Arrays;

public class DFA {
	String data[];
	String part1;
	String part2;

	public DFA(String x) {

		String[] parts = x.split("#");
		part1 = parts[0]; // DFA building string
		part2 = parts[1]; // Accept String

		part2 = part2.replaceAll(",", "");
		//System.out.println("part2 is "+part2);

		data = new String[10];
		int c = 0;
		for (int i = 0; i < part1.length(); i += 6) {
			data[c] = x.substring(i, i + 5);
			c++;
		}

		//System.out.println(Arrays.toString(data));
		// System.out.println("data 0 is "+data[0]);

	}

	public boolean accept(String y) {
		int t = 0; // data[] state
		int s; // charAt
		int x;
		for (int i = 0; i < y.length(); i++) {

			s = Integer.parseInt(y.charAt(i) + "");
			//System.out.println("value of s is "+s);
			if (s == 0)
				s = 2;
			else
				s = 4;

			t = Integer.parseInt(data[t].charAt(s) + "");
		}
		
		for (int i = 0; i < part2.length(); i++) {

			if (t == Integer.parseInt(part2.charAt(i) + ""))
				return true;
		}
		return false;

	}

	public static void main(String[] args) {
		DFA d = new DFA("0,0,1;1,2,1;2,0,3;3,4,4;4,1,1#1,3,4");
		//DFA d = new DFA("0,0,1,1,2,1#");

		System.out.println(d.accept("10101"));
	}
}
