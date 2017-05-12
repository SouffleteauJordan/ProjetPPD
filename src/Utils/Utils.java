package Utils;

import java.util.ArrayList;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.RandomPair;
import DataBean.SimilarPair;

public class Utils {
	
	public static int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static RandomPair getRandomPair(){
		RandomPair simP = new RandomPair();
		try{
			 simP = DBService.GET_RANDOM_PAIR();
		}catch (Exception e) {
		}
		return simP;
		
	}
	
	public static void GenerateSimilarPrime(Pair p){
		SimilarPair PairSimilairePrime = new SimilarPair();
		PairSimilairePrime.setId(p.getId());

		ArrayList<Attribut> listAttribut = p.getListAttribut();

		Attribut attr1 = listAttribut.get(0);
		if(attr1.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut1(attr1.getId());
		else
			PairSimilairePrime.setIdAttribut1(-1);
		
		Attribut attr2 = listAttribut.get(1);
		if(attr2.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut2(attr2.getId());
		else
			PairSimilairePrime.setIdAttribut2(-1);
		
		Attribut attr3 = listAttribut.get(2);
		if(attr3.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut3(attr3.getId());
		else
			PairSimilairePrime.setIdAttribut3(-1);
		
		Attribut attr4 = listAttribut.get(3);
		if(attr4.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut4(attr4.getId());
		else
			PairSimilairePrime.setIdAttribut4(-1);
		
		Attribut attr5 = listAttribut.get(4);
		if(attr5.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut5(attr5.getId());
		else
			PairSimilairePrime.setIdAttribut5(-1);
		
		PairSimilairePrime.setMoySimilar(p.getVal());
		DBService.INSERT_PAIR_TABLE_SIMILARITE_PRIME(PairSimilairePrime);
	
	}
	
	public static void GenerateSimilarPrimeApriori(Pair p){
		SimilarPair PairSimilairePrime = new SimilarPair();
		PairSimilairePrime.setId(p.getId());

		ArrayList<Attribut> listAttribut = p.getListAttribut();

		Attribut attr1 = listAttribut.get(0);
		if(attr1.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut1(attr1.getId());
		else
			PairSimilairePrime.setIdAttribut1(-1);
		
		Attribut attr2 = listAttribut.get(1);
		if(attr2.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut2(attr2.getId());
		else
			PairSimilairePrime.setIdAttribut2(-1);
		
		Attribut attr3 = listAttribut.get(2);
		if(attr3.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut3(attr3.getId());
		else
			PairSimilairePrime.setIdAttribut3(-1);
		
		Attribut attr4 = listAttribut.get(3);
		if(attr4.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut4(attr4.getId());
		else
			PairSimilairePrime.setIdAttribut4(-1);
		
		Attribut attr5 = listAttribut.get(4);
		if(attr5.getVal() >= 0.5)
			PairSimilairePrime.setIdAttribut5(attr5.getId());
		else
			PairSimilairePrime.setIdAttribut5(-1);
		
		PairSimilairePrime.setMoySimilar(p.getVal());
		DBService.INSERT_PAIR_TABLE_SIMILARITE_PRIME_APRIORI(PairSimilairePrime);
	
	}
	
	public static void calculNoteAttribut(Pair p, boolean attrSim1, boolean attrSim2, boolean attrSim3, boolean attrSim4, double nouvelleNote){
		ArrayList<Attribut> listAttributs = p.getListAttribut();
		
		if(attrSim1){
			Attribut attr1 = listAttributs.get(0);
			double noteActuelle1 = attr1.getVal();
			double noteFinale1 = ((noteActuelle1 * (attr1.getNbrVote() -1)) + nouvelleNote) /attr1.getNbrVote();
			attr1.setVal(noteFinale1);
			DBService.INSERT_ATTRIBUT(attr1, p.getId());
		}

		if(attrSim2){
			Attribut attr2 = listAttributs.get(1);
			double noteActuelle2 = attr2.getVal();
			double noteFinale2 = ((noteActuelle2 * attr2.getNbrVote()) + nouvelleNote) /attr2.getNbrVote();
			attr2.setVal(noteFinale2);
			DBService.INSERT_ATTRIBUT(attr2, p.getId());
		}

		if(attrSim3){
			Attribut attr3 = listAttributs.get(2);
			double noteActuelle3 = attr3.getVal();
			double noteFinale3 = ((noteActuelle3 * attr3.getNbrVote()) + nouvelleNote) /attr3.getNbrVote();
			attr3.setVal(noteFinale3);
			DBService.INSERT_ATTRIBUT(attr3, p.getId());;
		}

		if(attrSim4){
			Attribut attr4 = listAttributs.get(0);
			double noteActuelle4 = attr4.getVal();
			double noteFinale4 = ((noteActuelle4 * attr4.getNbrVote()) + nouvelleNote) /attr4.getNbrVote();
			attr4.setVal(noteFinale4);
			DBService.INSERT_ATTRIBUT(attr4, p.getId());
		}

	}
}
