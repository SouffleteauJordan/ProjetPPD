package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import Utils.Apriori;
import Utils.Utils;

/**
 * Servlet implementation class getRandomPairServlet
 */
@WebServlet("/postPairServlet")
public class postPairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public postPairServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JsonParser parser = new JsonParser();
		JsonElement obj = parser.parse(request.getReader());
		JsonObject json = obj.getAsJsonObject();

		int idPair = json.get("idPair").getAsInt();
		JsonObject Attribut1 = json.get("RestaurantName").getAsJsonObject();
		boolean attrSim1 = Attribut1.get("2").getAsBoolean();
		JsonObject Attribut2 = json.get("RestaurantAdress").getAsJsonObject();
		boolean attrSim2 = Attribut2.get("2").getAsBoolean();
		JsonObject Attribut3 = json.get("RestaurantPhone").getAsJsonObject();
		boolean attrSim3 = Attribut3.get("2").getAsBoolean();
		JsonObject Attribut4 = json.get("RestaurantType").getAsJsonObject();
		boolean attrSim4 = Attribut4.get("2").getAsBoolean();
		
		Pair pair = DBService.GET_PAIR_BY_ID(idPair);
		ArrayList<Attribut> listAttributs = DBService.GET_ALL_ATTRIBUT_FOR_PAIR(idPair);
		for (Attribut attribut : listAttributs) {
			attribut.setNbrVote(attribut.getNbrVote() + 1);
			pair.addAttribut(attribut);
		}
		double val = json.get("Val").getAsDouble();		
		Utils.calculNoteAttribut(pair, attrSim1, attrSim2, attrSim3, attrSim4, val);
		
		pair.setVal(DBService.INSERT_PAIR_TABLE_SIMILARITE(pair));
		Utils.GenerateSimilarPrime(pair);

		Pair pairApriori = DBService.GET_PAIR_BY_ID(idPair);
		ArrayList<Attribut> listAttributsApriori = DBService.GET_ALL_ATTRIBUT_FOR_PAIR(idPair);
		for (Attribut attribut : listAttributsApriori) {
			pairApriori.addAttribut(attribut);
		}
		pairApriori.setVal(json.get("Val").getAsDouble());		
		
		DBService.INSERT_PAIR_TABLE_SIMILARITE_APRIORI(pairApriori);
		Utils.GenerateSimilarPrimeApriori(pairApriori);

		try {
			Apriori ap = new Apriori(null);
			// ICI ON APPEL APRIORI !!
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
