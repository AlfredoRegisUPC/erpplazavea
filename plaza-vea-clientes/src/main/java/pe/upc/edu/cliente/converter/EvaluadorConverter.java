package pe.upc.edu.cliente.converter;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pe.upc.edu.alquiler.mbeans.EvaluacionMBean;
import pe.upc.edu.alquiler.mbeans.EvaluadorMBean;
import pe.upc.edu.alquiler.model.Evaluador;


@FacesConverter("evaluadorConverter")
public class EvaluadorConverter implements Converter{

	 
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
    	
    	final String beanName = "evaluadorMBean";
        
        Object bean;
     
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }
        Evaluador result = null;
        if (bean != null) {
        	EvaluadorMBean evaluador = (EvaluadorMBean)bean;
            for (int i = 0; i < evaluador.getListaEvaluadores().size(); i++) {
            	Evaluador evaluadorb = evaluador.getListaEvaluadores().get(i);
                if (evaluadorb.getIdEvaluador().equals(new Long(value))) {
                    result = evaluadorb;
                    break;
                }
            }
        }
      
        return result;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Evaluador) object).getIdEvaluador());
        }
        else {
            return null;
        }
    }   
	
}
