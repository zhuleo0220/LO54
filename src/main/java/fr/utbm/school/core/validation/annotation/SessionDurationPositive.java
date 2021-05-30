package fr.utbm.school.core.validation.annotation;

import fr.utbm.school.core.validation.SessionDurationPositiveValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { SessionDurationPositiveValidator.class })
@Documented
public @interface SessionDurationPositive {
    String message() default "End date must be after the start date of the course session";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
