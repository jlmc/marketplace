package org.xine.marketplace.report.impl.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.xine.marketplace.model.reports.RequisitionReportItem;

public final class RequisitionReportItemFactory {

    public static final List<RequisitionReportItem> createBeanCollection() {

        // Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        final List<RequisitionReportItem> itens = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            itens.add(new RequisitionReportItem(Long.valueOf(i + 1), "AAAAAA", "BBBBBbbbbb", Calendar.getInstance().getTime(), new BigDecimal(i + 1)));
        }

        return itens;
    }
}
