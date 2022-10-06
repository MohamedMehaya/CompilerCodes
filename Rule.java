package ay;

public class Rule {
	String Letter;
	int Number;
	boolean HE; //Has Epsilon
	boolean FiG; //First is Generated
	boolean FoG; //Follow is Generated
	String First;
	String Follow;
	
	public Rule(String letter, int number, boolean hE, boolean fiG, boolean foG, String first, String follow) {
		super();
		Letter = letter;
		Number = number;
		HE = hE;
		FiG = fiG;
		FoG = foG;
		First = first;
		Follow = follow;
	}
	

	public String getLetter() {
		return Letter;
	}

	public void setLetter(String letter) {
		Letter = letter;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public boolean isHE() {
		return HE;
	}

	public void setHE(boolean hE) {
		HE = hE;
	}

	public boolean isFiG() {
		return FiG;
	}

	public void setFiG(boolean fiG) {
		FiG = fiG;
	}

	public boolean isFoG() {
		return FoG;
	}

	public void setFoG(boolean foG) {
		FoG = foG;
	}

	public String getFirst() {
		return First;
	}

	public void setFirst(String first) {
		First = first;
	}

	public String getFollow() {
		return Follow;
	}

	public void setFollow(String follow) {
		Follow = follow;
	}


	@Override
	public String toString() {
		return "Rule [Letter=" + Letter + ", Number=" + Number + ", HE=" + HE + ", FiG=" + FiG + ", FoG=" + FoG
				+ ", First=" + First + ", Follow=" + Follow + "]";
	}
	
	
	
	
	
}
