package ProjectCore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.SimilarPair;
import Utils.Utils;

public class CollectData {

	static ArrayList<Pair> tablePreTraitement = new ArrayList<>();
	static ArrayList<Pair> tableSimilariteRPrime = new ArrayList<>();
	
	public static void collectData(){
		try {
			
			File file1 = new File("E:/Projet Java/ProjetPPD/WebContent/Files/Dataset1.txt");
			File file2 = new File("E:/Projet Java/ProjetPPD/WebContent/Files/Dataset2.txt");
			BufferedReader br1 = new BufferedReader(new FileReader(file1));
			BufferedReader br2;
			String line1 = "";
			String line2 = "";
						
			double val;
			int index = 0;
			br2 = new BufferedReader(new FileReader(file2));

			while ((line1 = br1.readLine()) != null) {
				while ((line2 = br2.readLine()) != null) {
					Pair p = new Pair(line1, line2);
					
					line1 =line1.replaceAll("\"", "");
					String[] tab1 = line1.split(",");

					line2 =line2.replaceAll("\"", "");
					String[] tab2 = line2.split(",");

					int length = tab1.length;
					if (tab2.length < length)
						length = tab2.length;
					String[] listNom = {"RestaurantName", "RestaurantAdress", "RestaurantVille", "RestaurantPhone", "RestaurantType", };
					for (int i = 0 ; i < length-1 ; i++){
						String elem1 = tab1[i].trim();
						String elem2 = tab2[i].trim();
						val = Utils.minDistance(elem1, elem2);
						double mult = 0.1;
						val = (1 - (val * mult));
						val = Double.parseDouble(new DecimalFormat("#.#").format(val).replace(',', '.'));
						if(val < 0)
							val = 0;
						Attribut a = new Attribut(p, listNom[i], elem1, elem2, val, 1);
						p.addAttribut(a);
					}
					DBService.INSERT_PAIR(p);
					DBService.INSERT_PAIR_TABLE_PRE_TRAITEMENT(p);
					DBService.INSERT_PAIR_TABLE_SIMILARITE(p);
				}
			}
			br2.close();	
			System.out.println("Ligne : " + index);
			index++;
			br1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
	public static void GenerateSimilarTablePrime(){
		ArrayList<SimilarPair> tableSimilariteR = DBService.GET_TABLE_SIMILAR_R();
		SimilarPair PairSimilairePrime = new SimilarPair();
		for (SimilarPair p : tableSimilariteR) {
			PairSimilairePrime.setId(p.getId());
			Attribut attr1 = DBService.GET_ATTRIBUT(p.getIdAttribut1());
			if(attr1.getVal() >= 0.5)
				PairSimilairePrime.setIdAttribut1(attr1.getId());
			else
				PairSimilairePrime.setIdAttribut1(-1);
			Attribut attr2 = DBService.GET_ATTRIBUT(p.getIdAttribut2());
			if(attr2.getVal() >= 0.5)
				PairSimilairePrime.setIdAttribut2(attr2.getId());
			else
				PairSimilairePrime.setIdAttribut2(-1);
			Attribut attr3 = DBService.GET_ATTRIBUT(p.getIdAttribut3());
			if(attr3.getVal() >= 0.5)
				PairSimilairePrime.setIdAttribut3(attr3.getId());
			else
				PairSimilairePrime.setIdAttribut3(-1);
			Attribut attr4 = DBService.GET_ATTRIBUT(p.getIdAttribut4());
			if(attr4.getVal() >= 0.5)
				PairSimilairePrime.setIdAttribut4(attr4.getId());
			else
				PairSimilairePrime.setIdAttribut4(-1);
			Attribut attr5 = DBService.GET_ATTRIBUT(p.getIdAttribut5());
			if(attr5.getVal() >= 0.5)
				PairSimilairePrime.setIdAttribut5(attr5.getId());
			else
				PairSimilairePrime.setIdAttribut5(-1);
			Double moySimilar = p.getMoySimilar();
			PairSimilairePrime.setMoySimilar(moySimilar);
			
			DBService.INSERT_PAIR_TABLE_SIMILARITE_PRIME(PairSimilairePrime);
			/*if(!p_Prime.getListAttribut().isEmpty())
				System.out.println(p_Prime.toString());*/
		}
	}
}
