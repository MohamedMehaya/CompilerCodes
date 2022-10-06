package ay;

public class FState {
	
	String name;
	String zero;
	String one;
	String AS;
	
	public FState(String name,String zero,String one,String AS) {
		this.name=name;
		this.zero=zero;
		this.one=one;
		this.AS=AS;
	}

	
	
	
	@Override
	public String toString() {
		return "FState [name=" + name + ", zero=" + zero + ", one=" + one + ", AS=" + AS + "]";
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZero() {
		return zero;
	}

	public void setZero(String zero) {
		this.zero = zero;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getAS() {
		return AS;
	}

	public void setAS(String aS) {
		AS = aS;
	}
	
	
	
}
