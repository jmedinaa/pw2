package controller.users;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import model.entity.*;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Role.class);
		
		List<Role> roles = (List<Role>)query.execute("select from Role");
		
		req.setAttribute("roles", roles);
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Users/add.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	
	String email = req.getParameter("email");
	//idRole +nameRole
	String idNameRole = req.getParameter("idRole");
	//separacion
	Long idRole=Long.parseLong(idNameRole.substring(0,16));
	String nameRole=idNameRole.substring(16,idNameRole.length());
	
	String birth = req.getParameter("birth");
	String gender = req.getParameter("gender");
	
	User user = new User(idRole,nameRole, email, ParseFecha(birth), new Boolean(gender));
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try{
		pm.makePersistent(user);
		}
	finally{
		pm.close();
	}
		resp.sendRedirect("/users");
}
	
	public static Date ParseFecha(String fecha){
	     SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
	     Date fechaDate = null;
	     try {
	         fechaDate = formato.parse(fecha);
	     } 
	     catch (ParseException ex) 
	     {
	         System.out.println(ex);
	     }
	     return fechaDate;
	 }
	public static String obtenerId(String cadena){
			return cadena.substring(0,16); 
	}
}
