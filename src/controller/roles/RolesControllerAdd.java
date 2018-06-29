package controller.roles;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;

import javax.jdo.PersistenceManager;
import model.entity.*;

@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	User currentUser = UserServiceFactory.getUserService().getCurrentUser();
	
	if(currentUser==null){
		RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
		dip.forward(req, resp);
	}
	else{
		PersistenceManager pm =PMF.get().getPersistenceManager();
		String query = "select from "+ model.entity.User.class.getName()+
				" where email=='"+currentUser.getEmail()+"'"+
				" && status==true";
		List<model.entity.User>uSearch=(List<model.entity.User>)pm.newQuery(query).execute();
		if(uSearch.isEmpty()){
			RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
			dip.forward(req, resp);
		}
		else{
			System.out.println(req.getServletPath());
			
			String query2 = "select from " + Resource.class.getName()+
					" where url=='"+req.getServletPath()+"'"+
					" && status==true";
			List<Resource>rSearch=(List<Resource>)pm.newQuery(query2).execute();
			if(rSearch.isEmpty()){
				RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
				dip.forward(req, resp);
			}
			else{
				String query3 = "select from "+Access.class.getName()+
						" where idRole=="+uSearch.get(0).getIdRole()+
						" && idResourse=="+rSearch.get(0).getId()+
						" && status==true";
				List<Access>aSearch=(List<Access>)pm.newQuery(query3).execute();
				if(aSearch.isEmpty()){
					RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
					dip.forward(req, resp);
				}
				else{
					
					try {
						req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
					} catch (ServletException e) {
						
						e.printStackTrace();
					}
	
				}
				
			}
		}
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
