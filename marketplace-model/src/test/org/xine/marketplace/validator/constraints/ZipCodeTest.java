package org.xine.marketplace.validator.constraints;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * The Class ZipCodeTest.
 */
@SuppressWarnings({"unchecked", "static-method" })
public class ZipCodeTest {

    /** The validator. */
    private static Validator validator;

    /**
     * Sets the up before class.
     * @throws Exception
     *             the exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test sucess.
     */
    @Test
    public void testSucess() {
        // fail("Not yet implemented");
        final ZipCodeStubModel stub = new ZipCodeStubModel();
        stub.setZipCode("3130-541");

        final Set<ConstraintViolation<ZipCodeStubModel>> constraintViolations = validator
                .validate(stub);

        assertEquals(0, constraintViolations.size());
    }

    /**
     * Test sucess empty.
     */

    @Test
    public void testSucessEmpty() {
        final ZipCodeStubModel stub = new ZipCodeStubModel();
        stub.setZipCode("3-130541");

        final Set<ConstraintViolation<ZipCodeStubModel>> constraintViolations = validator
                .validate(stub);

        assertEquals(1, constraintViolations.size());

        final ConstraintViolation<ZipCodeStubModel>[] violations = constraintViolations
                .toArray(new ConstraintViolation[0]);

        assertEquals("{org.xine.marketplace.validator.constraints.ZipCode.message}",
                violations[0].getMessage());

    }
}
