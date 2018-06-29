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
public class AccessControllerIndex extends HttpServlet{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Access.class);
		
		List<Access> access = (List<Access>)query.execute("select from Access");
		
		req.setAttribute("access", access);
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}
}
