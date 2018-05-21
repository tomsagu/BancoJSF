/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByDomicilio", query = "SELECT u FROM Usuario u WHERE u.domicilio = :domicilio")
    , @NamedQuery(name = "Usuario.findByCuenta", query = "SELECT u FROM Usuario u WHERE u.cuenta = :cuenta")
    , @NamedQuery(name = "Usuario.findBySaldo", query = "SELECT u FROM Usuario u WHERE u.saldo = :saldo")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")
    , @NamedQuery(name = "Usuario.findByEmpleado", query = "SELECT u FROM Usuario u WHERE u.empleado = :empleado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "Dni")
    private String dni;
    @Size(max = 50)
    @Column(name = "Contrasena")
    private String contrasena;
    @Size(max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "Apellidos")
    private String apellidos;
    @Column(name = "Telefono")
    private Integer telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @Size(max = 50)
    @Column(name = "Domicilio")
    private String domicilio;
    @Column(name = "Cuenta")
    private Integer cuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Saldo")
    private Double saldo;
    @Column(name = "Estado")
    private Short estado;
    @Column(name = "Empleado")
    private Short empleado;
    @JoinTable(name = "peticion", joinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario1", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Usuario> usuarioList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
    private List<Movimiento> movimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario1")
    private List<Movimiento> movimientoList1;
    @OneToMany(mappedBy = "usuarioidUsuario")
    private List<Usuario> usuarioList2;
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuarioidUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Short getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Short empleado) {
        this.empleado = empleado;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList1() {
        return usuarioList1;
    }

    public void setUsuarioList1(List<Usuario> usuarioList1) {
        this.usuarioList1 = usuarioList1;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList1() {
        return movimientoList1;
    }

    public void setMovimientoList1(List<Movimiento> movimientoList1) {
        this.movimientoList1 = movimientoList1;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList2() {
        return usuarioList2;
    }

    public void setUsuarioList2(List<Usuario> usuarioList2) {
        this.usuarioList2 = usuarioList2;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.ejb.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
