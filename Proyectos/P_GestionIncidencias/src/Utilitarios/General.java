package Utilitarios;

import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class General {
	public static void addIFrame(JDesktopPane jdp, JInternalFrame jif){
		boolean flag = false;
		
		try {
			//jif.setSelected(false);
			JInternalFrame[] children = jdp.getAllFrames();
			
			for(JInternalFrame j : children){
				if(j.getTitle() == jif.getTitle()){
					flag = true;
					//jif.setSelected(true);
					break;
				}
			}
			if(flag == false)
				jdp.add(jif);
			//if(jif.isSelected()==false)
				
			jif.setVisible(true);
			jif.setLocation(0,0);
			jif.moveToFront();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/***
	 * Establece el estado del boton seteado : Habilitado/Deshabilitado
	 * @param button
	 * @param activo
	 */
	public static void habilitar(JButton[] button,Boolean estado){
		try {
			for(JButton jb : button){
				if(jb.isEnabled() == false)
					jb.setEnabled(estado);
				else
					jb.setEnabled(!estado);
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String parsearDatetoString(Date fecha){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String sDate = df.format(fecha);
		return sDate;
	}
	
	public static Date parseStringtoDate(String fecha){
		Date parseDate = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			parseDate = fmt.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseDate;
	}
	
	public static Boolean validateJTableisEmpty(int rowcount, int r){
		if(rowcount == 0 || r == -1){
			JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
			return true;
		}
		else 
			return false;
	}
	/***
	 * Cambia el indice del TabbedPane. Habilita/deshabilita el panel
	 * @param tabbedPane Ingrese el TabbedPane que desea cambiar su estado
	 * @param estado Ingrese True si el indice que desea que aparezca sera el 1. False si el indice que desea que aparezca sera el 0
	 */
	public static void changePanel(JTabbedPane tabbedPane,boolean estado) {
		if(estado == true){
			tabbedPane.setSelectedIndex(1);
			tabbedPane.setEnabledAt(0, !estado);
			tabbedPane.setEnabledAt(1, estado);
		}else{
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setEnabledAt(0, estado);
			tabbedPane.setEnabledAt(1, !estado);
		}
	}

	public static void cambiarTitulo(JButton[] button){
		try {
			for(JButton jb : button){
				if(jb.getText() == "Registrar")
					jb.setText("Guardar");
				
				if(jb.getText() == "Salir")
					jb.setText("Volver");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void limpiar(Component component) {
        if (component instanceof JTextField) {
                JTextField text = (JTextField) component;
                text.setText("");
        }
         
        if (component instanceof JComboBox){
        	JComboBox combo = (JComboBox) component;
        	combo.setSelectedIndex(0);
        }
        
        if (component instanceof Container){
     	   for(Component c : ((Container) component).getComponents()){
     		   limpiar(c);
     	   }
        }


	}
}
