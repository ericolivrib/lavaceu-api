package br.com.erico.lavanderia.model.horario;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = HorarioValidator.class)
public @interface HorarioValido {

    String message() default "Hora Invalida| Deve estar entre 06:00 e 00:00";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
