package org.xine.marketplace.report.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.report.impl.utils.RequisitionReportItemFactory;

public class RequisitionExecuterTest {

    @Test
    @Ignore
    public void testGenerate() throws FileNotFoundException {
        final byte[] result = new RequisitionExecuter().generate(RequisitionReportItemFactory.createBeanCollection(), new Locale("pt", "PT"));

        try (FileOutputStream fos = new FileOutputStream("C:/Users/jcosta/RESULT.pdf")) {

            fos.write(result);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}
