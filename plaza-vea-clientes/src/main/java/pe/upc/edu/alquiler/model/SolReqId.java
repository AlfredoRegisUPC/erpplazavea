package pe.upc.edu.alquiler.model;

import java.util.Locale.Category;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SolReqId implements java.io.Serializable {

	@ManyToOne
	private Solicitud solicitud;
	@ManyToOne
    private Requisito requisito;

	
	
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolReqId that = (SolReqId) o;

        if (solicitud != null ? !solicitud.equals(that.solicitud) : that.solicitud != null) return false;
        if (requisito != null ? !requisito.equals(that.requisito) : that.requisito != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (solicitud != null ? solicitud.hashCode() : 0);
        result = 31 * result + (requisito != null ? requisito.hashCode() : 0);
        return result;
    }
    
}
