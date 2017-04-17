package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.RandomPair;
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
		
		Pair pair = DBService.GET_PAIR_BY_ID(idPair);
		ArrayList<Attribut> listAttributs = DBService.GET_ALL_ATTRIBUT_FOR_PAIR(idPair);
		for (Attribut attribut : listAttributs) {
			pair.addAttribut(attribut);
		}
				
		pair.setVal(json.get("Val").getAsDouble());
		
		DBService.INSERT_PAIR_TABLE_SIMILARITE(pair);
		Utils.GenerateSimilarPrime(pair);
	}

}
