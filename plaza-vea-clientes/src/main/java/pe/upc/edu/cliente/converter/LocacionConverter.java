package pe.upc.edu.cliente.converter;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import pe.upc.edu.alquiler.mbeans.LocacionMBean;
import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.cliente.mbeans.LocalMBean;
import pe.upc.edu.cliente.model.Local;




@FacesConverter("locacionConverter")
public class LocacionConverter implements Converter{

	 
	    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        /*if(value != null && value.trim().length() > 0) {
	            try {
	                LocacionMBean service = (LocacionMBean) fc.getExternalContext().getApplicationMap().get("locacionMBean");
	                return service.getListaLocales().get(Integer.parseInt(value));
	            } catch(NumberFormatException e) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
	            }
	        }
	        else {
	            return null;
	        }*/
	    	
	    	final String beanName = "locacionMBean";
	        
	        Object bean;
	     
	        try {
	            ELContext elContext = fc.getELContext();
	            bean = elContext.getELResolver().getValue(elContext, null, beanName);
	        } catch (RuntimeException e) {
	            throw new FacesException(e.getMessage(), e);
	        }
	        Locacion result = null;
	        if (bean != null) {
	        	LocacionMBean local = (LocacionMBean)bean;
	            for (int i = 0; i < local.getListaLocaciones().size(); i++) {
	            	Locacion localb = local.getListaLocaciones().get(i);
	                if (localb.getIdLocacion().equals(new Long(value))) {
	                    result = localb;
	                    break;
	                }
	            }
	        }
	      
	        return result;
	    }
	 
	    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	        if(object != null) {
	            return String.valueOf(((Locacion) object).getIdLocacion());
	        }
	        else {
	            return null;
	        }
	    }   
	
}
