package controller.resources;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import controller.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import model.entity.*;

@SuppressWarnings("serial")
public class ResourcesControllerIndex extends HttpServlet{
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Resource.class);
		
		List<Resource> resources = (List<Resource>)query.execute("select from Resource");
		
		req.setAttribute("resources", resources);
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Resources/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}

}
