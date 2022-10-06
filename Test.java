package ay;

public class Test {
	public static void main(String[] args) {

		// String x="ammrrodiabb";

		String x = "$$bbbbccdd";
		Elkbeera: while (true) {
			boolean Flag = true;
			for (int i = 0; i < x.length() - 1; i++) {
				if (x.charAt(i) == x.charAt(i + 1)) {
					Flag = false;
					x = x.substring(0, i + 1) + x.substring(i + 2, x.length());
				}
			}
			if (Flag)
				break Elkbeera;
		}

		System.out.println(x);

		String z = "$bcd";
		if (z.charAt(0) == '$') {
			z = z.substring(1);
			z += "$";
		}

		/*
		 * String y="AbdelSSalam"; y=y.substring(0,y.length());
		 * System.out.println("hi "+y);
		 */
		
		
		
		
		
		
		
		Special:while(true) {
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
