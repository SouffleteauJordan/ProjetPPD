package DataBean;

public class SimilarPair {

	private int id;
	private int idPair;
	private int idAttribut1 = -1;
	private int idAttribut2 = -1;
	private int idAttribut3 = -1;
	private int idAttribut4 = -1;
	private int idAttribut5 = -1;
	private double moySimilar;
	
	public SimilarPair() {
		
	}

	public SimilarPair(int id, int idPair, int idAttribut1, int idAttribut2, int idAttribut3, int idAttribut4, int idAttribut5, double moySimilar) {
		this.id = id;
		this.idPair = idPair;
		this.idAttribut1 = idAttribut1;
		this.idAttribut2 = idAttribut2;
		this.idAttribut3 = idAttribut3;
		this.idAttribut4 = idAttribut4;
		this.idAttribut5 = idAttribut5;
		this.moySimilar = moySimilar;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIdAttribut1() {
		return idAttribut1;
	}
	
	public int getIdAttribut2() {
		return idAttribut2;
	}
	
	public int getIdAttribut3() {
		return idAttribut3;
	}
	
	public int getIdAttribut4() {
		return idAttribut4;
	}
	
	public int getIdAttribut5() {
		return idAttribut5;
	}
	
	public int getIdPair() {
		return idPair;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIdAttribut1(int idAttribut1) {
		this.idAttribut1 = idAttribut1;
	}
	
	public void setIdAttribut2(int idAttribut2) {
		this.idAttribut2 = idAttribut2;
	}
	
	public void setIdAttribut3(int idAttribut3) {
		this.idAttribut3 = idAttribut3;
	}
	
	public void setIdAttribut4(int idAttribut4) {
		this.idAttribut4 = idAttribut4;
	}
	
	public void setIdAttribut5(int idAttribut5) {
		this.idAttribut5 = idAttribut5;
	}
	
	public void setIdPair(int idPair) {
		this.idPair = idPair;
	}
	
	public double getMoySimilar() {
		return moySimilar;
	}
	
	public void setMoySimilar(double moySimilar) {
		this.moySimilar = moySimilar;
	}
	
}
