package controller.users;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class UsersControllerView extends HttpServlet{
	
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("id")).longValue());
		User user = pm.getObjectById(User.class, k);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/view.jsp");
		dispatcher.forward(request, response);
				
		
	}


}
