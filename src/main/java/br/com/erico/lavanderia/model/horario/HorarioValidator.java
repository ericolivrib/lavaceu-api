package br.com.erico.lavanderia.model.horario;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class HorarioValidator implements ConstraintValidator<HorarioValido, LocalTime> {

    @Override
    public boolean isValid(LocalTime horario, ConstraintValidatorContext constraintValidatorContext) {
        return !horario.isAfter(LocalTime.of(6, 0))
                && !horario.isBefore(LocalTime.of(23, 59, 59));
    }
}
