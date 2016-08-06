package pe.upc.edu.cliente.converter;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pe.upc.edu.alquiler.mbeans.RequisitoMBean;
import pe.upc.edu.alquiler.model.Requisito;

@FacesConverter(forClass = Requisito.class, value = "reqConverter")
public class ReqConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent component,
            String value) {

    	final String beanName = "requisitoMBean";
        
        Object bean;
     
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }
        Requisito result = null;
        if (bean != null) {
        	RequisitoMBean req = (RequisitoMBean)bean;
            for (int i = 0; i < req.getListaRequisitos().size(); i++) {
            	Requisito localb = req.getListaRequisitos().get(i);
                if (localb.getIdRequisito().equals(new Long(value))) {
                    result = localb;
                    break;
                }
            }
        }
      
        return result;
    }

    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

        try {
        	Requisito proDTO = (Requisito) value;
            return String.valueOf(proDTO.getIdRequisito());
        } catch (NullPointerException e) {
            return "";
        }
    }
}