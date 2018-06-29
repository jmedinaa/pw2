package controller.access;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import model.entity.*;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Role.class);
		List<Role> roles = (List<Role>)query.execute("select from Role");
		req.setAttribute("roles", roles);
		Query query2 = pm.newQuery(Resource.class);
		List<Resource> resources = (List<Resource>)query2.execute("select from Resource");
		req.setAttribute("resources", resources);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Access/add.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	//	idRole +nameRole
	String idNameRole = req.getParameter("idRole");
	//separacion
	String idRole= idNameRole.substring(0,16);
	String nameRole=idNameRole.substring(16,idNameRole.length());
	
	// idResource + urlResource
	String idUrlResource = req.getParameter("idResource");
	//separacion
	String idResource = idUrlResource.substring(0,16);
	String urlResource= idUrlResource.substring(16,idUrlResource.length());
	
	Access access = new Access(Long.parseLong(idRole),nameRole,Long.parseLong(idResource),urlResource);
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try{
		pm.makePersistent(access);
		}
	finally{
		pm.close();
	}
		resp.sendRedirect("/access");
}
	

}
