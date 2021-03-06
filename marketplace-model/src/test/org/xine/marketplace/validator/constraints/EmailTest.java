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
 * The Class SKUTest.
 */
@SuppressWarnings("static-method")
public class EmailTest {

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
        final EmailStubModel stub = new EmailStubModel("funny8086@gmail.com");

        final Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator
                .validate(stub);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testSucessEmpty() {
        // fail("Not yet implemented");
        final EmailStubModel stub = new EmailStubModel("");

        final Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator
                .validate(stub);

        assertEquals(0, constraintViolations.size());
    }

    /**
     * Test invalid.
     */
    @Test
    public void testInvalid() {
        // fail("Not yet implemented");
        final EmailStubModel stub = new EmailStubModel("invalidEmail.com");

        final Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator
                .validate(stub);

        assertEquals(1, constraintViolations.size());
        assertEquals("{org.xine.marketplace.validator.constraints.Email.message}",
                constraintViolations.iterator().next().getMessage());
    }

}
