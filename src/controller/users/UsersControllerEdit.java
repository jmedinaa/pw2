package controller.users;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class UsersControllerEdit extends HttpServlet{
	
@SuppressWarnings("unchecked")
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			User user = pm.getObjectById(User.class,idLong);
			req.setAttribute("user",user);
			
			//roles 
			Query query = pm.newQuery(Role.class);
			List<Role> roles = (List<Role>)query.execute("select from Role");
			req.setAttribute("roles", roles);
			
			req.getRequestDispatcher("/WEB-INF/Views/Users/edit.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
			
		String id = req.getParameter("id");
		//idRole + nameRole 
		String idNameRole = req.getParameter("idRole");
		//separacion
		String idRole= idNameRole.substring(0,16);
		String nameRole= idNameRole.substring(16,idNameRole.length());
		
		
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		String status = req.getParameter("status");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			User user = pm.getObjectById(User.class,idLong);
			user.setIdRole(Long.parseLong(idRole));
			user.setNameRole(nameRole);
			user.setEmail(email);
			user.setBirth(ParseFecha(birth));
			user.setGender(new Boolean(gender));
			user.setStatus(new Boolean(status));
			
			
			
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
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
}


