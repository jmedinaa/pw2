package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try {
				
				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 
				Access access = pm.getObjectById(Access.class,idLong);
				req.setAttribute("access",access);
				
				//roles 
				Query query = pm.newQuery(Role.class);
				List<Role> roles = (List<Role>)query.execute("select from Role");
				req.setAttribute("roles", roles);
				
				Query query2 = pm.newQuery(Resource.class);
				List<Resource> resources = (List<Resource>)query2.execute("select from Resource");
				req.setAttribute("resources", resources);
				
				req.getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp").forward(req, resp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				pm.close();
			}
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
				
			String id = req.getParameter("id");
			
//			idRole +nameRole
			String idNameRole = req.getParameter("idRole");
			//separacion
			String idRole= idNameRole.substring(0,16);
			String nameRole=idNameRole.substring(16,idNameRole.length());
			
			// idResource + urlResource
			String idUrlResource = req.getParameter("idResource");
			//separacion
			String idResource = idUrlResource.substring(0,16);
			String urlResource= idUrlResource.substring(16,idUrlResource.length());
			
			String status = req.getParameter("status");
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try{
				Long idLong = Long.parseLong(id);
				
				Access access = pm.getObjectById(Access.class,idLong);
				access.setIdRole(Long.parseLong(idRole));
				access.setNameRole(nameRole);
				access.setIdResourse(Long.parseLong(idResource));
				access.setUrlResource(urlResource);
				access.setStatus(new Boolean(status));
				
				
				
			}
			catch(Exception e){
				System.out.println("Se produjo un Error");
			}
			finally{
				pm.close();
			}
				resp.sendRedirect("/access");
		}
	}



