package controller.users;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import controller.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import model.entity.*;

@SuppressWarnings("serial")
public class UsersControllerIndex extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(User.class);
		List<User> users = (List<User>)query.execute("select from User");
		
		req.setAttribute("users", users);
	
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}

}
