package ay;

import java.util.ArrayList;
import java.util.Stack;

public class FDFA {

	ArrayList<String> Ast;
	ArrayList<FState> a;
	int NS;
	String AS;

	public static void main(String[] args) {
		FDFA f = new FDFA("0,4,1,000;1,1,2,001;2,3,2,010;3,1,4,011;4,4,0,100#2,4");
		f.run("000");
		f.run("101");
		f.run("1110");
		f.run("11000");
		f.run("0110");
		
		System.out.println("  ");
		System.out.println("  ");
		System.out.println("  ");
		
		FDFA m = new FDFA("0,1,1,000;1,4,2,001;2,2,3,010;3,4,0,011;4,2,4,100#4");
		m.run("0101");
		m.run("1001");
		m.run("0011");
		m.run("11100");
		m.run("00");
		
		System.out.println("Thanks Nardeen");
		
	}

	public FDFA(String x) {
		Ast = new ArrayList<String>();
		a = new ArrayList<FState>();

		String[] parts = x.split("#");
		String FBS = parts[0]; // FDFA building string

		AS = parts[1]; // Accept String
		AS = AS.replaceAll(",", "");

		NS = 0;
		for (int i = 0; i < FBS.length(); i++) {
			if (FBS.charAt(i) == ';')
				NS++;
		}
		NS++;
		// System.out.println("Number of states is " + NS);
		String[] ps = FBS.split(";");
		for (int i = 0; i < NS; i++) {
			// System.out.println("ps[i] is "+ps[i]);
			Ast.add(ps[i]);
		}

		// System.out.println(Ast.toString());
		// System.out.println(Ast.get(0));

		for (int i = 0; i < NS; i++) {
			String y = Ast.get(i);
			String[] pm = y.split(",");
			String Name = pm[0];
			String ZT = pm[1];
			String OT = pm[2];
			String AcS = pm[3];
			a.add(new FState(Name, ZT, OT, AcS));
			//System.out.println(a.get(i));
		}

		// System.out.println(a.toString());

	}

	private void run(String x) {
		Stack s = new Stack();
		String RS = "";// Rs
		int limit = x.length();
		int LP = 0, RP = 0;
		FState CS = a.get(0);
		s.push(CS);
		int cc;
		String y;

		int counter=0;
		while (RP < limit && LP < limit ) {
			//System.out.println("hi");
			while (RP < limit) {
				//System.out.println("bye");
				cc = Integer.parseInt(x.charAt(RP) + "");
				if (cc == 0)
					y = CS.getZero();
				else
					y = CS.getOne();
				for (int j = 0; j < NS; j++) {
					if (a.get(j).getName().equals(y)) {
						CS = a.get(j);
						//System.out.println("Line 100 CS is "+CS);
						s.push(CS);
						break;
					}
				}
				RP++;
			}
			// System.out.println("CS is " + CS);
			/*
			 * while(!s.isEmpty()) { System.out.println(s.pop()); }
			 */
			Boolean Flag=false;
			String sp=((FState) s.peek()).getAS();
			w: while (true && !s.isEmpty()) {
				String z = ((FState) s.peek()).getName();
				//sp=  ((FState) s.peek()).getAS();
				//System.out.println("sp is "+sp+"fo2");
				for (int i = 0; i < AS.length(); i++) {
					// System.out.println("AS char is "+AS.charAt(i));
					// System.out.println("z is "+z);
					if ((AS.charAt(i) + "").equals(z)) {
						Flag=true; 
						//-if statement 
						CS = a.get(0);
						RS += ((FState) s.peek()).getAS();
						//System.out.println("RS changed in the accept");
						break w;
					}
				}
				s.pop();
				RP--;
			}
			if(!Flag) {
				RS+=sp;
				//System.out.println("sp is "+sp);
				//System.out.println("flag didn't change");
				System.out.println("RS is "+RS);
				return;
			}
			
			
			/*
			 * while (!s.isEmpty()) { System.out.println(s.pop()); }
			 */

			LP = RP;
			s = new Stack();
			counter++;
			Flag=false;
			//System.out.println("LP is "+LP);
			//System.out.println("RP is "+RP);
		}
		System.out.println("RS is "+RS);
	}
}
