package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBaseManager.DBConnectManager;
import DataBaseManager.DBService;
import ProjectCore.CollectData;

@SuppressWarnings("serial")
public class DBInitServlet extends  HttpServlet {
	
	 public void init() throws ServletException
	    {
			System.out.println("----------");
			System.out.println("---------- Server Initialization started ----------");
			System.out.println("----------");
			
	  		DBConnectManager.InitConnexion();
			

			System.out.println("----------");
			System.out.println("---------- Server Connection established ----------");
			System.out.println("----------");
			
			
			DBService.INIT_DB();
			
			System.out.println("----------");
			System.out.println("---------- Database cleaned ----------");
			System.out.println("----------");
			
			CollectData.collectData();
			
			System.out.println("----------");
			System.out.println("---------- Database generated ----------");
			System.out.println("----------");
			
			System.out.println("----------");
			System.out.println("---------- Server Initialization successfully ----------");
			System.out.println("----------");
	    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
