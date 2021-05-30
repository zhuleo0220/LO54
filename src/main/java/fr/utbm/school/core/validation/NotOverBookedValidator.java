package fr.utbm.school.core.validation;

import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.validation.annotation.NotOverBooked;
import fr.utbm.school.core.validation.annotation.SessionDurationPositive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public class NotOverBookedValidator implements ConstraintValidator<NotOverBooked, CourseSession> {

    /**
     *
     * @param notOverBooked
     */
    @Override
    public void initialize(NotOverBooked notOverBooked) {
    }


    /**
     *
     * @param courseSession
     * @param context
     * @return
     */
    @Override
    public boolean isValid(CourseSession courseSession, ConstraintValidatorContext context) {
        if ( courseSession == null || courseSession.getMaxStudent() == null) {
            return true;
        }

        return (courseSession.getRegisteredClients().size() / courseSession.getMaxStudent()) <= 1;
    }

}
