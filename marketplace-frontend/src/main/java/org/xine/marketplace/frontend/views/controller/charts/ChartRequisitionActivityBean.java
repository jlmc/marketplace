package org.xine.marketplace.frontend.views.controller.charts;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.xine.marketplace.business.services.charts.vo.RequisitionActivity;
import org.xine.marketplace.business.services.requisitions.RequisitionActivityService;
import org.xine.marketplace.model.filters.RequisitionActivityFilter;
import org.xine.marketplace.repository.helpers.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * import javax.enterprise.context.RequestScoped;
 * javax.faces.view.ViewScoped
 * The Class ChartRequisitionActivityBean.
 */
@Named
// @javax.faces.view.ViewScoped
@RequestScoped
public class ChartRequisitionActivityBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    //
    // business services
    //
    // -------------------------------------------------------------------------
    /** The requisition activity service. */
    @Inject
    private RequisitionActivityService requisitionActivityService;

    // -------------------------------------------------------------------------
    //
    // Model properties
    //
    // -------------------------------------------------------------------------

    /** The line model2. */
    private LineChartModel lineModel2;

    /** The date model. */
    private LineChartModel dateModel;

    private boolean shouldRender = false;

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------

    /**
     * Inits the.
     */
    // @PostConstruct
    // private void init() {
    // execute();
    // }

    // --------------------------------------------------------------------------
    //
    // Event handlers
    //
    // --------------------------------------------------------------------------

    /**
     * Creates the date model.
     */
    @SuppressWarnings("boxing")
    public void execute() {
        final RequisitionActivity requisitionActivity = this.requisitionActivityService
                .getRequisitionActivity(new RequisitionActivityFilter());

        this.dateModel = new LineChartModel();

        final LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Totais Globais");

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (requisitionActivity != null && requisitionActivity.getGlobalTotals() != null) {
            final Map<Date, BigDecimal> map = requisitionActivity.getGlobalTotals();

            map.keySet().forEach(dv -> series1.set(sdf.format(dv), map.get(dv)));
        }

        this.dateModel.addSeries(series1);

        this.dateModel.setTitle("Requisition Activity");

        this.dateModel.setZoom(true);
        this.dateModel.getAxis(AxisType.Y).setLabel("Values");
        this.dateModel.getAxis(AxisType.Y).setMin(0);

        final DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);

        axis.setMax(sdf.format(DateUtils.asDate(LocalDate.now().plusDays(1))));
        axis.setTickFormat("%b %#d, %y");

        this.dateModel.getAxes().put(AxisType.X, axis);
        this.shouldRender = true;
    }

    // --------------------------------------------------------------------------
    //
    // Getters and Setter
    //
    // --------------------------------------------------------------------------

    /**
     * Gets the line model2.
     * @return the lineModel2
     */
    public LineChartModel getLineModel2() {
        return this.lineModel2;
    }

    /**
     * Gets the date model.
     * @return the dateModel
     */
    public LineChartModel getDateModel() {
        return this.dateModel;
    }

    /**
     * @return the shouldRender
     */
    public boolean isShouldRender() {
        return this.shouldRender;
    }

}
