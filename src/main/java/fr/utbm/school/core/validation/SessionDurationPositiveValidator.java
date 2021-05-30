package fr.utbm.school.core.validation;

import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.validation.annotation.SessionDurationPositive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public class SessionDurationPositiveValidator implements ConstraintValidator<SessionDurationPositive, CourseSession> {

    /**
     *
     * @param sessionDurationPositive
     */
    @Override
    public void initialize(SessionDurationPositive sessionDurationPositive) {
    }

    /**
     *
     * @param courseSession
     * @param context
     * @return
     */
    @Override
    public boolean isValid(CourseSession courseSession, ConstraintValidatorContext context) {
        if ( courseSession == null ) {
            return true;
        }

        return courseSession.getStartDate().compareTo(courseSession.getEndDate()) < 0;
    }
}
