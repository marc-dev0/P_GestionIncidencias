package reportes;

import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;

public abstract class AbstractJasperReports {

	private static JasperReport report;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;

	public static void createReport(String path, Map<String, Object> parametros) {
		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(path);
			reportFilled = JasperFillManager.fillReport(report, parametros,
					new JREmptyDataSource());

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void showViewer() {
		viewer = new JasperViewer(reportFilled);
		viewer.setVisible(true);
		// viewer.viewReport(reportFilled, false);
	}
}
