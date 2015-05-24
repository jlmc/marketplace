package org.xine.marketplace.validator.constraints;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class NotBlankTest {

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

    @Test
    public void testNegative() {
        // fail("Not yet implemented");
        final UserStub stub = new UserStub("   ");

        final Set<ConstraintViolation<UserStub>> constraintViolations = validator.validate(stub);

        assertEquals(1, constraintViolations.size());
        assertEquals("{org.xine.marketplace.validator.constraints.NotBlank.message}",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testPositive() {
        // fail("Not yet implemented");
        final UserStub stub = new UserStub(" hjk s  ");

        final Set<ConstraintViolation<UserStub>> constraintViolations = validator.validate(stub);

        assertEquals(0, constraintViolations.size());
    }

}

class UserStub {

    private String name;

    public UserStub(final String name) {
        super();
        this.name = name;
    }

    @NotBlank
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
