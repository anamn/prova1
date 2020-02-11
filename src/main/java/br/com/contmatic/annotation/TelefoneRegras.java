package br.com.contmatic.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Pattern(regexp = "([(]?)([1][1-9])([)]?)([0-9]{8,9})|([(]?)([2][1-2])([)]?)([0-9]{8,9})|([(]?)([2][4-8])([)]?)([0-9]{8,9})|([(]?)([3][1-5])([)]?)([0-9]{8,9})|([(]?)([3][7-8])([)]?)([0-9]{8,9})|([(]?)([4][1-9])([)]?)([0-9]{8,9})|([(]?)([5][1])([)]?)([0-9]{8,9})|([(]?)([5][3-5])([)]?)([0-9]{8,9})|([(]?)([6][1-9])([)]?)([0-9]{8,9})|([(]?)([7][1])([)]?)([0-9]{8,9})|([(]?)([7][3-5])([)]?)([0-9]{8,9})|([(]?)([7][7])([)]?)([0-9]{8,9})|([(]?)([7][9])([)]?)([0-9]{8,9})|([(]?)([8][1-9])([)]?)([0-9]{8,9})|([(]?)([9][1-9])([)]?)([0-9]{8,9})")
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TelefoneRegras {

    String message() default "{org.hibernate.validator.constraints.br.Telefone.message}";

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface ListT {
        TelefoneRegras[] value();
    }
}
