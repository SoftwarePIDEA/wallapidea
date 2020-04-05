/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "VALORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v")
    , @NamedQuery(name = "Valoracion.findByValId", query = "SELECT v FROM Valoracion v WHERE v.valId = :valId")
    , @NamedQuery(name = "Valoracion.deleteByProduct", query = "DELETE FROM Valoracion v WHERE v.productId = :productId")
    , @NamedQuery(name = "Valoracion.findByNota", query = "SELECT v FROM Valoracion v WHERE v.nota = :nota")
    , @NamedQuery(name = "Valoracion.findByComentario", query = "SELECT v FROM Valoracion v WHERE v.comentario = :comentario")
    , @NamedQuery(name = "Valoracion.findByFechayhora", query = "SELECT v FROM Valoracion v WHERE v.fechayhora = :fechayhora")})
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VAL_ID")
    private Integer valId;
    @Column(name = "NOTA")
    private Integer nota;
    @Size(max = 200)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "FECHAYHORA")
    @Temporal(TemporalType.DATE)
    private Date fechayhora;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private Producto productId;

    public Valoracion() {
    }

    public Valoracion(Integer valId) {
        this.valId = valId;
    }

    public Integer getValId() {
        return valId;
    }

    public void setValId(Integer valId) {
        this.valId = valId;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(Date fechayhora) {
        this.fechayhora = fechayhora;
    }

    public Producto getProductId() {
        return productId;
    }

    public void setProductId(Producto productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valId != null ? valId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.valId == null && other.valId != null) || (this.valId != null && !this.valId.equals(other.valId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wallapidea.entity.Valoracion[ valId=" + valId + " ]";
    }
    
}
