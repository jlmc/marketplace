package org.xine.marketplace.validator.constraints;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class SKUTest.
 */
public class EmailTest {

	/** The validator. */
	private static Validator validator;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test sucess.
	 */
	@Test
	public void testSucess() {
		//fail("Not yet implemented");
		EmailStubModel stub = new EmailStubModel("funny8086@gmail.com");
		
		 Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator.validate( stub );
		
		 assertEquals( 0, constraintViolations.size() );
	}
	
	@Test
	public void testSucessEmpty() {
		//fail("Not yet implemented");
		EmailStubModel stub = new EmailStubModel("");
		
		Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator.validate( stub );
		
		assertEquals( 0, constraintViolations.size() );
	}
	
	/**
	 * Test invalid.
	 */
	@Test
	public void testInvalid() {
		//fail("Not yet implemented");
		EmailStubModel stub = new EmailStubModel("invalidEmail.com");
		
		Set<ConstraintViolation<EmailStubModel>> constraintViolations = validator.validate( stub );
		
		assertEquals( 1, constraintViolations.size() );
		 assertEquals(
		         "{org.xine.marketplace.validator.constraints.Email.message}",
		         constraintViolations.iterator().next().getMessage());
	}

}
