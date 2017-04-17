package DataBean;

public class RandomPair {

	private int idPair;
	private Attribut idAttribut1;
	private Attribut idAttribut2;
	private Attribut idAttribut3;
	private Attribut idAttribut4;
	private Attribut idAttribut5;
	private double val;
	
	public RandomPair() {
		
	}

	public RandomPair(int idPair, Attribut idAttribut1, Attribut idAttribut2, Attribut idAttribut3, Attribut idAttribut4, Attribut idAttribut5) {
		this.idPair = idPair;
		this.idAttribut1 = idAttribut1;
		this.idAttribut2 = idAttribut2;
		this.idAttribut3 = idAttribut3;
		this.idAttribut4 = idAttribut4;
		this.idAttribut5 = idAttribut5;
	}
	
	public Attribut getIdAttribut1() {
		return idAttribut1;
	}
	
	public double getVal() {
		return val;
	}
	public void setVal(double val) {
		this.val = val;
	}
	public Attribut getIdAttribut2() {
		return idAttribut2;
	}
	
	public Attribut getIdAttribut3() {
		return idAttribut3;
	}
	
	public Attribut getIdAttribut4() {
		return idAttribut4;
	}
	
	public Attribut getIdAttribut5() {
		return idAttribut5;
	}
	
	public int getIdPair() {
		return idPair;
	}
	
	public void setIdAttribut1(Attribut idAttribut1) {
		this.idAttribut1 = idAttribut1;
	}
	
	public void setIdAttribut2(Attribut idAttribut2) {
		this.idAttribut2 = idAttribut2;
	}
	
	public void setIdAttribut3(Attribut idAttribut3) {
		this.idAttribut3 = idAttribut3;
	}
	
	public void setIdAttribut4(Attribut idAttribut4) {
		this.idAttribut4 = idAttribut4;
	}
	
	public void setIdAttribut5(Attribut idAttribut5) {
		this.idAttribut5 = idAttribut5;
	}
	
	public void setIdPair(int idPair) {
		this.idPair = idPair;
	}
	
}
