package pe.upc.edu.cliente.converter;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pe.upc.edu.cliente.mbeans.LocalMBean;
import pe.upc.edu.cliente.model.Local;


@FacesConverter("localConverter")
public class LocalConverter implements Converter{

	 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        if(value != null && value.trim().length() > 0) {
//            try {
//                LocalMBean service = (LocalMBean) fc.getExternalContext().getApplicationMap().get("localMBean");
//                return service.getListaLocales().get(Integer.parseInt(value));
//            } catch(NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
//            }
//        }
//        else {
//            return null;
//        }
    	
    	final String beanName = "localMBean";
        
        Object bean;
     
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }
        Local result = null;
        if (bean != null) {
        	LocalMBean local = (LocalMBean)bean;
            for (int i = 0; i < local.getListaLocales().size(); i++) {
            	Local localb = local.getListaLocales().get(i);
                if (localb.getIdLocal().equals(new Long(value))) {
                    result = localb;
                    break;
                }
            }
        }
      
        return result;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Local) object).getIdLocal());
        }
        else {
            return null;
        }
    }   
	
}
