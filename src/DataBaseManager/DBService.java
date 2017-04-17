package DataBaseManager;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import DataBean.Attribut;
import DataBean.Pair;
import DataBean.RandomPair;
import DataBean.SimilarPair;

public class DBService {

	public static void INIT_DB(){
		String sqlPair = "DELETE FROM `pair` WHERE 1";
		PreparedStatement statementPair;
		try {
			statementPair = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlPair);
			statementPair.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlPair2 = "ALTER TABLE pair AUTO_INCREMENT = 1";
		PreparedStatement statementPair2;
		try {
			statementPair2 = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlPair2);
			statementPair2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sqlAttribut = "DELETE FROM `attribut` WHERE 1";
		PreparedStatement statementAttribut;
		try {
			statementAttribut = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlAttribut);
			statementAttribut.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlAttribut2 = "ALTER TABLE attribut AUTO_INCREMENT = 1";
		PreparedStatement statementAttribut2;
		try {
			statementAttribut2 = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlAttribut2);
			statementAttribut2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlTableSimilarR = "DELETE FROM `tablesimilarr` WHERE 1";
		PreparedStatement statementTableSimilarR;
		try {
			statementTableSimilarR = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTableSimilarR);
			statementTableSimilarR.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlTableSimilarR2 = "ALTER TABLE tablesimilarr AUTO_INCREMENT = 1";
		PreparedStatement statementTableSimilarR2;
		try {
			statementTableSimilarR2 = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTableSimilarR2);
			statementTableSimilarR2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlTableSimilarRPrime = "DELETE FROM `tablesimilarrprime` WHERE 1";
		PreparedStatement statementTableSimilarRPrime;
		try {
			statementTableSimilarRPrime = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTableSimilarRPrime);
			statementTableSimilarRPrime.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlTableSimilarRPrime2 = "ALTER TABLE tablesimilarrprime AUTO_INCREMENT = 1";
		PreparedStatement statementTableSimilarRPrime2;
		try {
			statementTableSimilarRPrime2 = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTableSimilarRPrime2);
			statementTableSimilarRPrime2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sqlTablePreTraitement = "DELETE FROM `tablepretraitement` WHERE 1";
		PreparedStatement statementTablePreTraitement;
		try {
			statementTablePreTraitement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTablePreTraitement);
			statementTablePreTraitement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlTablePreTraitement2 = "ALTER TABLE tablepretraitement AUTO_INCREMENT = 1";
		PreparedStatement statementTablePreTraitement2;
		try {
			statementTablePreTraitement2 = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlTablePreTraitement2);
			statementTablePreTraitement2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void INSERT_PAIR(Pair p){
		String sql = "INSERT INTO pair (Entry1, Entry2) VALUES (?, ?)";
		 
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, p.getObj1());
			statement.setString(2, p.getObj2());
			 
	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                p.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	        
			for (Attribut a : p.getListAttribut()) {
				INSERT_ATTRIBUT(a, p.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void INSERT_ATTRIBUT(Attribut a, int idPair){
		String sql = "INSERT INTO attribut(PairId, Attr1, Attr2, Val) VALUES (?,?,?,?)";
				
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idPair);
			statement.setString(2, a.getElem1());
			statement.setString(3, a.getElem2());
			statement.setDouble(4, a.getVal());
			
			int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                a.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void INSERT_PAIR_TABLE_PRE_TRAITEMENT(Pair p){
		String sql = "INSERT INTO tablepretraitement (idPair, idAttribut1, idAttribut2, idAttribut3, idAttribut4, idAttribut5, moySimilar) VALUES (?,?,?,?,?,?,?)";
				
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, p.getId());
			int i = 2;
			double moySim = 0;
			for (Attribut a : p.getListAttribut()) {
				statement.setInt(i, a.getId());
				i++;
				moySim = moySim + a.getVal();
			}
			moySim = moySim / p.getListAttribut().size();
			BigDecimal bd = new BigDecimal(moySim);
			bd = bd.setScale(3, BigDecimal.ROUND_FLOOR);
			statement.setDouble(i, bd.doubleValue());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void INSERT_PAIR_TABLE_SIMILARITE(Pair p){
		String sqlSelect = "SELECT * FROM tablesimilarr WHERE idPair = ?";
		PreparedStatement statementSelect;
		int nbrVote = 1;
		boolean exist = false;
		try {
			statementSelect = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlSelect);
			statementSelect.setInt(1, p.getId());
			ResultSet resSelect = statementSelect.executeQuery();
			while(resSelect.next()) {
				exist = true;
				double val = resSelect.getDouble(8);
				nbrVote = resSelect.getInt(9);
				nbrVote++;
				String sqlUpdate = "UPDATE tablesimilarr SET moySimilar = ?, nbrVote = ? WHERE idPair = ?";
				PreparedStatement statementUpdate;
				try {
					statementUpdate = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sqlUpdate);
					BigDecimal bd = new BigDecimal((val + p.getVal())/2);
					bd = bd.setScale(3, BigDecimal.ROUND_FLOOR);
					statementUpdate.setDouble(1, bd.doubleValue());
					statementUpdate.setDouble(2, nbrVote);
					statementUpdate.setInt(3, p.getId());
					statementUpdate.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!exist){
			String sql = "INSERT INTO tablesimilarr (idPair, idAttribut1, idAttribut2, idAttribut3, idAttribut4, idAttribut5, moySimilar, nbrVote) VALUES (?,?,?,?,?,?,?,?)";
					
			PreparedStatement statement;
			try {
				statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
				statement.setInt(1, p.getId());
				
				int i = 2;
				double moySim = 0;
				for (Attribut a : p.getListAttribut()) {
					statement.setInt(i, a.getId());
					i++;
					moySim = moySim + a.getVal();
				}
				moySim = moySim / p.getListAttribut().size();
				BigDecimal bd = new BigDecimal(moySim);
				bd = bd.setScale(3, BigDecimal.ROUND_FLOOR);
				statement.setDouble(i, bd.doubleValue());
				statement.setInt(i+1, nbrVote);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Attribut GET_ATTRIBUT(int idAttribut){
		String sql = "SELECT * FROM attribut where id = ?";

		Attribut attr = new Attribut();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, idAttribut);
			
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				String Attr1 = res.getString(3);
				String Attr2 = res.getString(4);
				Double Val = res.getDouble(5);
				attr = new Attribut(null, Attr1, Attr2, Val);
				attr.setId(res.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attr;
	}

	
	public static ArrayList<Attribut> GET_ALL_ATTRIBUT_FOR_PAIR(int idPair){
		String sql = "SELECT * FROM attribut where PairId = ?";
		ArrayList<Attribut> listAttribut = new ArrayList<>();
		Attribut attr = new Attribut();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, idPair);
			
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				String Attr1 = res.getString(3);
				String Attr2 = res.getString(4);
				Double Val = res.getDouble(5);
				attr = new Attribut(null, Attr1, Attr2, Val);
				attr.setId(res.getInt(1));
				listAttribut.add(attr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAttribut;
	}
	
	public static ArrayList<SimilarPair> GET_TABLE_SIMILAR_R(){
		String sql = "SELECT * FROM tablesimilarr";
		ArrayList<SimilarPair> listPairSimilaire = new ArrayList<>();		
		SimilarPair simP = new SimilarPair();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			ResultSet res = statement.executeQuery();
			
			while (res.next()) {
	            int id = res.getInt(1);
	            int idPair = res.getInt(2);
	            int idAttribut1 = res.getInt(3);
	            int idAttribut2 = res.getInt(4);
	            int idAttribut3 = res.getInt(5);
	            int idAttribut4 = res.getInt(6);
	            int idAttribut5 = res.getInt(7);
	            int moySimilar = res.getInt(8);

	            simP = new SimilarPair(id, idPair, idAttribut1, idAttribut2, idAttribut3, idAttribut4, idAttribut5, moySimilar);
	            listPairSimilaire.add(simP);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPairSimilaire;
	}
	
	public static void INSERT_PAIR__TABLE_SIMILARITE_PRIME(SimilarPair p){
		String sql = "INSERT INTO tablesimilarrprime (idPair, idAttribut1, idAttribut2, idAttribut3, idAttribut4, idAttribut5, moySimilar) VALUES (?,?,?,?,?,?,?)";
				
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, p.getId());
			statement.setInt(2, p.getIdAttribut1());
			statement.setInt(3, p.getIdAttribut2());
			statement.setInt(4, p.getIdAttribut3());
			statement.setInt(5, p.getIdAttribut4());
			statement.setInt(6, p.getIdAttribut5());
			statement.setDouble(7, p.getMoySimilar());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static RandomPair GET_RANDOM_PAIR(){
		String sql = "SELECT * FROM pair ORDER BY RAND() LIMIT 1";
		RandomPair simP = null;
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			ResultSet res  = statement.executeQuery();
            double val = 0;
			
			while (res.next()) {
	            int id = res.getInt(1);
	            ArrayList<Attribut> listAttr = GET_ALL_ATTRIBUT_FOR_PAIR(id);
	            Attribut Attribut1 = GET_ATTRIBUT(listAttr.get(0).getId());
	            Attribut Attribut2 = GET_ATTRIBUT(listAttr.get(1).getId());
	            Attribut Attribut3 = GET_ATTRIBUT(listAttr.get(2).getId());
	            Attribut Attribut4 = GET_ATTRIBUT(listAttr.get(3).getId());
	            Attribut Attribut5 = GET_ATTRIBUT(listAttr.get(4).getId());	            
	            simP = new RandomPair(id, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5);
	            for (Attribut attribut : listAttr) {
					val = val + attribut.getVal();
				}
	            val = (val / listAttr.size());
				simP.setVal(val);
	        }
			if(val < 0.4){
				simP = GET_RANDOM_PAIR();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return simP;
	}
	
	public static Pair GET_PAIR_BY_ID(int idPair){
		String sql = "SELECT * FROM pair WHERE ID = ?";
		Pair pair = null;
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, idPair);
			ResultSet res  = statement.executeQuery();
			while (res.next()) {
				pair = new Pair();
				pair.setId(res.getInt(1));
				pair.setObj1(res.getString(2));
				pair.setObj2(res.getString(3));	            
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pair;
	}
	
	public static RandomPair GET_PAIR_WITH_ATTRIBUT_BY_ID(int idPair){
		String sql = "SELECT * FROM pair WHERE ID = ?";
		RandomPair simP = null;
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) DBConnectManager.getConnectionDB().prepareStatement(sql);
			statement.setInt(1, idPair);
			ResultSet res  = statement.executeQuery();
			
			while (res.next()) {
	            int id = res.getInt(1);
	            ArrayList<Attribut> listAttr = GET_ALL_ATTRIBUT_FOR_PAIR(id);
	            Attribut Attribut1 = GET_ATTRIBUT(listAttr.get(0).getId());
	            Attribut Attribut2 = GET_ATTRIBUT(listAttr.get(1).getId());
	            Attribut Attribut3 = GET_ATTRIBUT(listAttr.get(2).getId());
	            Attribut Attribut4 = GET_ATTRIBUT(listAttr.get(3).getId());
	            Attribut Attribut5 = GET_ATTRIBUT(listAttr.get(4).getId());

	            simP = new RandomPair(idPair, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5);
	            
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return simP;
	}
}
