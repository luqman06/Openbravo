package org.wirabumi.mobileinfra.service.UserAccountService;

import java.io.Writer;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.ad.access.User;
import org.openbravo.service.web.WebService;
 
/**
 * Implementation of example webservice querying for all sales orders on the basis of a product.
 * 
 * @author mtaal
 */
public class ValidateUserAccount implements WebService {
 
  public void doGet(String path, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
	  
	  OBContext.setOBContext(request);
	  String returnvalue="";
	  User user = OBContext.getOBContext().getUser();
	  if (user!=null){
		  returnvalue= OBContext.getOBContext().getUser().getId();
	  }
	  
	  // write to the response
	  response.setContentType("text/xml");
	  response.setCharacterEncoding("utf-8");
	  final Writer w = response.getWriter();
	  w.write(returnvalue);
	  w.close();
  }
 
  public void doDelete(String path, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
  }
 
  public void doPost(String path, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
  }
 
  public void doPut(String path, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
  }
}