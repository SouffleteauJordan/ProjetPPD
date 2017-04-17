package DataBean;
import java.util.ArrayList;

public class Pair {

	private int id;
	private String obj1;
	private String obj2;
	private ArrayList<Attribut> listAttribut = new ArrayList<>();
	private double val;

	public Pair() {
	}
	
	public Pair(String obj1, String obj2){
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	
	public void setVal(double val) {
		this.val = val;
	}
	
	public double getVal() {
		return val;
	}
	
	public String getObj1() {
		return obj1;
	}
	
	public void setObj2(String obj2) {
		this.obj2 = obj2;
	}
	
	public String getObj2() {
		return obj2;
	}	
	
	public void setObj1(String obj1) {
		this.obj1 = obj1;
	}
	
	public void addAttribut(Attribut a){
		listAttribut.add(a);
	}
	
	public ArrayList<Attribut> getListAttribut() {
		return listAttribut;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		String res = '\n' + "######################################################################################################################################" + '\n';
		res = res + "### " +obj1 + " | " + obj2 + " ###" + '\n';
		for (Attribut a : listAttribut) {
			res = res + a.toString() + '\n';
			res = res + "######################################################################################################################################" + '\n';
		}
		return res;
	}
}
