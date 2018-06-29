package controller.resources;

import java.io.IOException;
import javax.servlet.http.*;
import controller.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import model.entity.*;

@SuppressWarnings("serial")
public class ResourcesControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String id = req.getParameter("id");
		Long idLong = Long.parseLong(id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Query query = pm.newQuery(Resource.class);
			query.setFilter("id == idParam");
			query.declareParameters("Long idParam");
			query.deletePersistentAll(idLong);
			query.closeAll();
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/resources");
	}


}
