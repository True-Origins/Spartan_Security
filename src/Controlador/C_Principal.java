package Controlador;
import Modelo.DAO.M_DAO_Empleado;
import Modelo.DAO.M_DAO_Cursos_Categorias;
import Modelo.DAO.M_DAO_Socursal;
import Modelo.DAO.M_DAO_Medio_Pago;
import Modelo.DAO.M_DAO_Tipo_Servicio;
import Modelo.DAO.M_DAO_Turno;
import Modelo.DAO.M_DAO_Puesto;
import Modelo.DAO.M_DAO_Cliente_Empresa;
import Modelo.DAO.M_DAO_Cliente_Persona;
import Modelo.DAO.M_DAO_Cursos;
import Modelo.DAO.M_DAO_Cursos_Publicados;
import Modelo.DAO.M_DAO_Servicios;
import Modelo.DAO.M_DAO_Contrato_Servicios;


import Modelo.VO.M_VO_Cliente_Persona;
import Modelo.VO.M_VO_Cliente_Empresa;
import Modelo.VO.M_VO_Puesto;
import Modelo.VO.M_VO_Turno;
import Modelo.VO.M_VO_Tipo_Servicio;
import Modelo.VO.M_VO_Empleado;
import Modelo.VO.M_VO_Cursos_Categoria;
import Modelo.VO.M_VO_Socursal;
import Modelo.VO.M_VO_Medio_pago;
import Modelo.VO.M_VO_Cursos;
import Modelo.VO.M_VO_Cursos_Publicados;
import Modelo.VO.M_VO_Servicios;
import Modelo.VO.M_VO_Contrato_Servicio;

import Vista.V_Principal;
import Vista.V_Medio_Pago;
import Vista.V_Sucursal;
import Vista.V_Empleado;
import Vista.V_Cursos_Categoria;
import Vista.V_Tipo_Servicios;
import Vista.V_Turno;
import Vista.V_Puesto;
import Vista.V_Cliente_Empresa;
import Vista.V_Cliente_Persona;
import Vista.V_ComboBox;
import Vista.V_Cursos;
import Vista.V_Cursos_Publicado;
import Vista.V_Servicios;
import Vista.V_Contrato_Servicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class C_Principal implements ActionListener{
    V_Principal MDI;
    V_Empleado Empleado = null;
    V_Cursos_Categoria C_Categoria=null;
    V_Sucursal Socursal=null;
    V_Medio_Pago medio_pago=null;
    V_Tipo_Servicios Tipo_Servicios=null;
    V_Turno turno=null;
    V_Puesto puesto=null;
    V_Cliente_Empresa empresa=null;
    V_Cliente_Persona persona=null;
    V_ComboBox com=null;
    V_Cursos curso = null;
    V_Cursos_Publicado cursosPublicados = null;
    V_Servicios Servicios=null;
    V_Contrato_Servicio ContratoServicio=null;
    public C_Principal(V_Principal MDI) {
        this.MDI = MDI;
        this.MDI.sub_mnu_Categoria_Cursos.addActionListener(this);
        this.MDI.sub_mnu_Socursal.addActionListener(this);
        this.MDI.sub_mnu_Medio_Pago.addActionListener(this);
        this.MDI.sub_mnu_Empleado.addActionListener(this);
        this.MDI.sub_mnu_Tipo_Servicios.addActionListener(this);
        this.MDI.sub_mnu_Turno.addActionListener(this);
        this.MDI.sub_mnu_Puesto.addActionListener(this);
        this.MDI.sub_mnu_Persona.addActionListener(this);
        this.MDI.sub_mnu_Empresa.addActionListener(this);
        this.MDI.sub_mnu_Cursos.addActionListener(this);
        this.MDI.sub_mnu_Cursos_Publicados.addActionListener(this);
        this.MDI.sub_mnu_Servicios.addActionListener(this);
        this.MDI.sub_mnu_Tipo_Contrato_Servicios.addActionListener(this);

        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CerrarFormulariosAbiertos();
        if (e.getSource() == MDI.sub_mnu_Empleado) {
            AbreCatalago_Empleado();            
        }
        if (e.getSource() == MDI.sub_mnu_Categoria_Cursos) {
            AbreCatalago_Categoria_Cursos();            
        }
        if (e.getSource() == MDI.sub_mnu_Socursal) {
            AbreCatalago_Socursal();            
        }
        if (e.getSource() == MDI.sub_mnu_Medio_Pago) {
            AbreCatalago_Medio_Pago();            
        }
        if (e.getSource() == MDI.sub_mnu_Tipo_Servicios) {
            AbreCatalago_Tipo_Servicios();            
        }
        if (e.getSource() == MDI.sub_mnu_Turno) {
            AbreCatalago_Turno();            
        }
        if (e.getSource() == MDI.sub_mnu_Puesto) {
            AbreCatalago_Puesto();            
        }
        if (e.getSource() == MDI.sub_mnu_Persona) {
            AbreCatalago_persona();            
        }
        if (e.getSource() == MDI.sub_mnu_Empresa) {
            AbreCatalago_empresa();            
        }
        if (e.getSource() == MDI.sub_mnu_Cursos) {
            AbreCatalago_cursos();            
        }
        if (e.getSource() == MDI.sub_mnu_Cursos_Publicados) {
            AbreCatalago_cursosPublicados();            
        }
        if (e.getSource() == MDI.sub_mnu_Servicios) {
            AbreCatalago_Servicios();            
        }
        if (e.getSource() == MDI.sub_mnu_Tipo_Contrato_Servicios) {
            AbreCatalago_Contrato_Servicios();            
        }
    }
    public void AbreCatalago_Contrato_Servicios() {
        if (ContratoServicio == null || ContratoServicio.isClosed()) {
            ContratoServicio = new V_Contrato_Servicio();
            M_DAO_Contrato_Servicios Mo=new M_DAO_Contrato_Servicios();
            M_VO_Contrato_Servicio vo = new M_VO_Contrato_Servicio();
            V_ComboBox com=new V_ComboBox();
            C_Contrato_Servicio ctrl = new C_Contrato_Servicio(Mo, ContratoServicio, vo, com);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            ContratoServicio.setSize(x, y);

            MDI.jDesktopPane1.add(ContratoServicio);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        ContratoServicio.setVisible(true);
    }
    public void AbreCatalago_Servicios() {
        if (Servicios == null || Servicios.isClosed()) {
            Servicios = new V_Servicios();
            M_DAO_Servicios Mo=new M_DAO_Servicios();
            M_VO_Servicios vo = new M_VO_Servicios();
            V_ComboBox com=new V_ComboBox();
            C_Servicios ctrl = new C_Servicios(Mo, Servicios, vo, com);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Servicios.setSize(x, y);

            MDI.jDesktopPane1.add(Servicios);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        Servicios.setVisible(true);
    }
    public void AbreCatalago_empresa() {
        if (empresa == null || empresa.isClosed()) {
            empresa = new V_Cliente_Empresa();
            M_DAO_Cliente_Empresa Mo=new M_DAO_Cliente_Empresa();
            M_VO_Cliente_Empresa vo = new M_VO_Cliente_Empresa();
            C_Cliente_Empresa ctrl = new C_Cliente_Empresa(Mo, empresa, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            empresa.setSize(x, y);

            MDI.jDesktopPane1.add(empresa);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        empresa.setVisible(true);
    }
    public void AbreCatalago_persona() {
        if (persona == null || persona.isClosed()) {
            persona = new V_Cliente_Persona();
            M_DAO_Cliente_Persona Mo=new M_DAO_Cliente_Persona();
            M_VO_Cliente_Persona vo = new M_VO_Cliente_Persona();
            C_Cliente_Persona ctrl = new C_Cliente_Persona(Mo, persona, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            persona.setSize(x, y);

            MDI.jDesktopPane1.add(persona);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        persona.setVisible(true);
    }
    public void AbreCatalago_Puesto() {
        if (puesto == null || puesto.isClosed()) {
            puesto = new V_Puesto();
            M_DAO_Puesto Mo=new M_DAO_Puesto();
            M_VO_Puesto vo = new M_VO_Puesto();
            C_Puesto ctrl = new C_Puesto(Mo, puesto, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            puesto.setSize(x, y);

            MDI.jDesktopPane1.add(puesto);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        puesto.setVisible(true);
    }
    public void AbreCatalago_Turno() {
        if (turno == null || turno.isClosed()) {
            turno = new V_Turno();
            M_DAO_Turno Mo=new M_DAO_Turno();
            M_VO_Turno vo = new M_VO_Turno();
            C_Turno ctrl = new C_Turno(Mo, turno, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            turno.setSize(x, y);

            MDI.jDesktopPane1.add(turno);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        turno.setVisible(true);

    }
    public void AbreCatalago_Tipo_Servicios() {
        if (Tipo_Servicios == null || Tipo_Servicios.isClosed()) {
            Tipo_Servicios = new V_Tipo_Servicios();
            M_DAO_Tipo_Servicio Mo=new M_DAO_Tipo_Servicio();
            M_VO_Tipo_Servicio vo = new M_VO_Tipo_Servicio();
            C_Tipo_Servicios ctrl = new C_Tipo_Servicios(Mo, Tipo_Servicios, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Tipo_Servicios.setSize(x, y);

            MDI.jDesktopPane1.add(Tipo_Servicios);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        Tipo_Servicios.setVisible(true);

    }
    public void AbreCatalago_Categoria_Cursos() {
        if (C_Categoria == null || C_Categoria.isClosed()) {
            C_Categoria = new V_Cursos_Categoria();
            M_DAO_Cursos_Categorias Mo=new M_DAO_Cursos_Categorias();
            M_VO_Cursos_Categoria vo_c_categoria = new M_VO_Cursos_Categoria();
            C_Cursos_Categorias ctrl = new C_Cursos_Categorias(Mo, C_Categoria, vo_c_categoria);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            C_Categoria.setSize(x, y);

            MDI.jDesktopPane1.add(C_Categoria);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        C_Categoria.setVisible(true);

    }
    public void AbreCatalago_Empleado() {
        if (Empleado == null || Empleado.isClosed()) {
            Empleado = new V_Empleado();
            M_DAO_Empleado Mo=new M_DAO_Empleado();
            M_VO_Empleado vo_linea = new M_VO_Empleado();
            V_ComboBox com=new V_ComboBox();
            C_Empleado ctrl = new C_Empleado(Mo, Empleado, vo_linea,com);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Empleado.setSize(x, y);
            MDI.jDesktopPane1.add(Empleado);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Empleado.setVisible(true);
    }
    public void AbreCatalago_Socursal() {
        if (Socursal == null || Socursal.isClosed()) {
            Socursal = new V_Sucursal();
            M_DAO_Socursal Mo=new M_DAO_Socursal();
            M_VO_Socursal vo_linea = new M_VO_Socursal();
            C_Socursal ctrl = new C_Socursal(Mo, Socursal, vo_linea);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Socursal.setSize(x, y);
            MDI.jDesktopPane1.add(Socursal);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Socursal.setVisible(true);
    }
    public void AbreCatalago_Medio_Pago() {
        if (medio_pago == null || medio_pago.isClosed()) {
            medio_pago = new V_Medio_Pago();
            M_DAO_Medio_Pago Mo=new M_DAO_Medio_Pago();
            M_VO_Medio_pago vo = new M_VO_Medio_pago();
            C_Medio_Pago ctrl = new C_Medio_Pago(Mo, medio_pago, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            medio_pago.setSize(x, y);
            MDI.jDesktopPane1.add(medio_pago);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        medio_pago.setVisible(true);
    }
    
    public void AbreCatalago_cursos() {
        if (curso == null || curso.isClosed()) {
            curso = new V_Cursos();
            M_DAO_Cursos Mo=new M_DAO_Cursos();
            M_VO_Cursos vo = new M_VO_Cursos();
            V_ComboBox com=new V_ComboBox();
            C_Cursos ctrl = new C_Cursos(Mo, curso, vo, com);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            curso.setSize(x, y);

            MDI.jDesktopPane1.add(curso);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        curso.setVisible(true);
    }
    
    public void AbreCatalago_cursosPublicados() {
        if (cursosPublicados == null || cursosPublicados.isClosed()) {
            cursosPublicados = new V_Cursos_Publicado();
            M_DAO_Cursos_Publicados Mo=new M_DAO_Cursos_Publicados();
            M_VO_Cursos_Publicados vo = new M_VO_Cursos_Publicados();
            V_ComboBox com=new V_ComboBox();
            C_Cursos_Publicado ctrl = new C_Cursos_Publicado(Mo, cursosPublicados, vo, com);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            cursosPublicados.setSize(x, y);

            MDI.jDesktopPane1.add(cursosPublicados);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto");
        }
        cursosPublicados.setVisible(true);
    }
    
    public void CerrarFormulariosAbiertos(){
        if (cursosPublicados != null)
            cursosPublicados.dispose();
        if (Empleado != null)
            Empleado.dispose();
        if (C_Categoria != null)
            C_Categoria.dispose();
        if (Socursal != null)
            Socursal.dispose();
        if (medio_pago != null)
            medio_pago.dispose();
        if (Tipo_Servicios != null)
            Tipo_Servicios.dispose();
        if (turno != null)
            turno.dispose();
        if (puesto != null)
            puesto.dispose();
        if (empresa != null)
            empresa.dispose();
        if (persona != null)
            persona.dispose();
        if (curso != null)
            curso.dispose();
        if (cursosPublicados != null)
            cursosPublicados.dispose();
        if (Servicios != null)
            Servicios.dispose();
        if (ContratoServicio != null)
            ContratoServicio.dispose();
    }
}
