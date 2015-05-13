package org.xine.marketplace.report.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.xine.marketplace.model.reports.RequisitionReportItem;

/**
 * The Class RequisitionExecuter.
 */
public class RequisitionExecuter {

    /** The Constant JASPER_FILE. */
    private static final String JASPER_FILE = "/reports/requisition_issue_resport.jasper";

    /**
     * Generate.
     * @param requisitions
     *            the requisitions
     * @param locale
     *            the locale
     * @return the byte[]
     */
    public byte[] generate(final List<RequisitionReportItem> requisitions, final Locale locale) {
        byte[] result = null;
        try (InputStream inputStream = getClass().getResourceAsStream(JASPER_FILE)) {

            final Map<String, Object> parameters = new HashMap<>();
            if (locale != null) {
                parameters.put(JRParameter.REPORT_LOCALE, locale);
            }

            final JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, new JRBeanCollectionDataSource(requisitions));

            final ByteArrayOutputStream OutputReport = new ByteArrayOutputStream();
            final Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(OutputReport));
            exporter.exportReport();
            result = OutputReport.toByteArray();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
