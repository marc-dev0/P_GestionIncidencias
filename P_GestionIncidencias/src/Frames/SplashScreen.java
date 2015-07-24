package Frames;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {
	int duracion = 0;

	public SplashScreen(int duracion) {
		this.duracion = duracion;

		JPanel contentPane = (JPanel) getContentPane();
		ImageIcon img = new ImageIcon(
				SplashScreen.class
						.getResource("/imagenesx16/logoGestionIncidencias.png"));

		contentPane.add(new JLabel(img), BorderLayout.CENTER);
		setSize(img.getIconWidth(), img.getIconHeight());
		setVisible(true);
		setLocationRelativeTo(null);

		try {
			Thread.sleep(duracion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(false);
	}

}
