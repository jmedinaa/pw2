package controller.resources;

import java.io.IOException;
import javax.jdo.PersistenceManager;

import javax.servlet.http.*;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class ResourcesControllerEdit extends HttpServlet{
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Resource resource = pm.getObjectById(Resource.class,idLong);
			req.setAttribute("resource",resource);
			
			
			req.getRequestDispatcher("/WEB-INF/Views/Resources/edit.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
			
		String id = req.getParameter("id");
		String url = req.getParameter("url");
		String status = req.getParameter("status");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			Resource resource = pm.getObjectById(Resource.class,idLong);
			resource.setUrl(url);
			resource.setStatus(new Boolean(status));
			
			
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/resources");
	}
}


