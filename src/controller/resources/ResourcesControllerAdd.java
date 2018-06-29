package controller.resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;

import javax.jdo.PersistenceManager;
import model.entity.*;

@SuppressWarnings("serial")
public class ResourcesControllerAdd extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Resources/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Realizar la persistencia
	String url = req.getParameter("url");
	
	Resource resource = new Resource(url);
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try{
		pm.makePersistent(resource);
		}
	finally{
		pm.close();
	}
		resp.sendRedirect("/resources");
}

}
