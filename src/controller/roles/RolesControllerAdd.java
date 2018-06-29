package controller.roles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;

import javax.jdo.PersistenceManager;
import model.entity.*;

@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Realizar la persistencia
	String name = req.getParameter("name");
	
	Role role = new Role(name);
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try{
		pm.makePersistent(role);
		}
	finally{
		pm.close();
	}
		resp.sendRedirect("/roles");
}
}
