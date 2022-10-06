package ay;

public class NState {

	String name;
	String zero;
	String one;
	String eps;
	

	public NState(String name,String zero,String one,String eps){
		this.name=name;
		this.zero=zero;
		this.one=one;
		this.eps=eps;
	}
	
	

	@Override
	public String toString() {
		return "State [name=" + name + ", zero=" + zero + ", one=" + one + ", eps=" + eps + "]";
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
		if(this.zero =="-1")
			this.zero =zero;
		else
		this.zero = this.zero +zero;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		if(this.one =="-1")
			this.one =one;
		else
		this.one = this.one+one;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		if(this.eps =="-1")
			this.eps =eps;
		else
		this.eps = this.eps+eps;
	}
	
}
