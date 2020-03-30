/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProductId", query = "SELECT p FROM Producto p WHERE p.productId = :productId")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByFoto", query = "SELECT p FROM Producto p WHERE p.foto = :foto")
    , @NamedQuery(name = "Producto.findByFechayhora", query = "SELECT p FROM Producto p WHERE p.fechayhora = :fechayhora")
    , @NamedQuery(name = "Producto.findByValoracionmedia", query = "SELECT p FROM Producto p WHERE p.valoracionmedia = :valoracionmedia")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    //@Size(max = 50)
    @Column(name = "TITULO", length=50)
    private String titulo;
    //@Size(max = 200)
    @Column(name = "DESCRIPCION", length=200)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    //@Size(max = 500)
    @Column(name = "FOTO" , length=500)
    private String foto;
    @Column(name = "FECHAYHORA")
    @Temporal(TemporalType.DATE)
    private Date fechayhora;
    @Column(name = "VALORACIONMEDIA")
    private Double valoracionmedia;
    @JoinTable(name = "PRODPALABRAS", joinColumns = {
        @JoinColumn(name = "PROD_ID", referencedColumnName = "PRODUCT_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PAL_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Palabraclave> palabraclaveList;
    @OneToMany(mappedBy = "productId")
    private List<Valoracion> valoracionList;
    @JoinColumn(name = "CAT_ID", referencedColumnName = "CAT_ID")
    @ManyToOne(optional = false)
    private Categoria catId;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public Producto() {
    }

    public Producto(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(Date fechayhora) {
        this.fechayhora = fechayhora;
    }

    public Double getValoracionmedia() {
        return valoracionmedia;
    }

    public void setValoracionmedia(Double valoracionmedia) {
        this.valoracionmedia = valoracionmedia;
    }

    @XmlTransient
    public List<Palabraclave> getPalabraclaveList() {
        return palabraclaveList;
    }

    public void setPalabraclaveList(List<Palabraclave> palabraclaveList) {
        this.palabraclaveList = palabraclaveList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    public Categoria getCatId() {
        return catId;
    }

    public void setCatId(Categoria catId) {
        this.catId = catId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PRODUCTO: " + productId + ". ";
    }
    
}
