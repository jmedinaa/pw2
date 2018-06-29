package controller.roles;

import java.io.IOException;
import javax.jdo.PersistenceManager;

import javax.servlet.http.*;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class RolesControllerEdit extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Role role = pm.getObjectById(Role.class,idLong);
			req.setAttribute("role",role);
			
			
			req.getRequestDispatcher("/WEB-INF/Views/Roles/edit.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
			
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String status = req.getParameter("status");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			Role role = pm.getObjectById(Role.class,idLong);
			role.setName(name);
			role.setStatus(new Boolean(status));
			
			
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/roles");
	}
}
