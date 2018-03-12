package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import fr.rtgrenoble.velascof.simtelui.Util;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputControl;
import org.controlsfx.control.decoration.Decoration;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class ParamControllerBase implements Initializable {

    private final ValidationSupport validationSupport;
    private final List<BaseValidator> validators;

    protected ParamControllerBase() {
        validationSupport = new ValidationSupport();
        validators = new ArrayList<>();
        validationSupport.setValidationDecorator(new CustomValidationDecoration());
    }

    public final ValidationSupport getValidationSupport() {
        return validationSupport;
    }

    public final void registerDoubleValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validateDouble());
        validators.add(new CheckBoxValidator(b, c, Util::isDouble));
    }

    public final void registerPositiveDoubleValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validatePositiveDouble());
        validators.add(new CheckBoxValidator(b, c, Util::isPositiveDouble));
    }

    public final void registerIntegerValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validateInteger());
        validators.add(new CheckBoxValidator(b, c, Util::isInteger));
    }

    public final void registerPositiveIntegerValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validatePositiveInteger());
        validators.add(new CheckBoxValidator(b, c, Util::isPositiveInteger));
    }

    public final void registerNotEmptyValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validateNotEmpty());
        validators.add(new CheckBoxValidator(b, c, Util::isNotEmpty));
    }

    public final void registerBinaryStringValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validateBinaryString());
        validators.add(new CheckBoxValidator(b, c, Util::isBinaryString));
    }

    public final void registerOrderValidator(TextInputControl c, CheckBox... b) {
        validationSupport.registerValidator(c, validateOrder());
        validators.add(new CheckBoxValidator(b, c, Util::isOrder));
    }

    public final void registerDoubleValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validateDouble());
        validators.add(new RadioButtonValidator(b, c, Util::isDouble));
    }

    public final void registerPositiveDoubleValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validatePositiveDouble());
        validators.add(new RadioButtonValidator(b, c, Util::isPositiveDouble));
    }

    public final void registerIntegerValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validateInteger());
        validators.add(new RadioButtonValidator(b, c, Util::isInteger));
    }

    public final void registerPositiveIntegerValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validatePositiveInteger());
        validators.add(new RadioButtonValidator(b, c, Util::isPositiveInteger));
    }

    public final void registerNotEmptyValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validateNotEmpty());
        validators.add(new RadioButtonValidator(b, c, Util::isNotEmpty));
    }

    public final void registerBinaryStringValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validateBinaryString());
        validators.add(new RadioButtonValidator(b, c, Util::isBinaryString));
    }

    public final void registerOrderValidator(TextInputControl c, RadioButton... b) {
        validationSupport.registerValidator(c, validateOrder());
        validators.add(new RadioButtonValidator(b, c, Util::isOrder));
    }

    public final void registerModulationValidator(TextInputControl c, RadioButton b) {
        validationSupport.registerValidator(c, validateDouble());
        validators.add(new ModulationValidator(b, c));
    }

    public final void registerDoubleValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validateDouble());
        validators.add(new BaseValidator(c, Util::isDouble));
    }

    public final void registerPositiveDoubleValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validatePositiveDouble());
        validators.add(new BaseValidator(c, Util::isPositiveDouble));
    }

    public final void registerIntegerValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validateInteger());
        validators.add(new BaseValidator(c, Util::isInteger));
    }

    public final void registerPositiveIntegerValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validatePositiveInteger());
        validators.add(new BaseValidator(c, Util::isPositiveInteger));
    }

    public final void registerNotEmptyValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validateNotEmpty());
        validators.add(new BaseValidator(c, Util::isNotEmpty));
    }

    public final void registerBinaryStringValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validateBinaryString());
        validators.add(new BaseValidator(c, Util::isBinaryString));
    }

    public final void registerOrderValidator(TextInputControl c) {
        validationSupport.registerValidator(c, validateOrder());
        validators.add(new BaseValidator(c, Util::isOrder));
    }

    public static Validator<String> validateDouble() {
        return Validator.createPredicateValidator(Util::isDouble, "Vous devez entrer un nombre.");
    }

    public static Validator<String> validatePositiveDouble() {
        return Validator.createPredicateValidator(Util::isPositiveDouble, "Vous devez entrer un nombre positif.");
    }

    public static Validator<String> validateInteger() {
        return Validator.createPredicateValidator(Util::isInteger, "Vous devez entrer un nombre entier.");
    }

    public static Validator<String> validatePositiveInteger() {
        return Validator.createPredicateValidator(Util::isPositiveInteger, "Vous devez entrer un nombre entier positif.");
    }

    public static Validator<String> validateNotEmpty() {
        return Validator.createPredicateValidator(Util::isNotEmpty, "Il est déconseillé de laisser le champ vide s'il est activé.", Severity.WARNING);
    }

    public static Validator<String> validateBinaryString() {
        return Validator.createPredicateValidator(Util::isBinaryString, "Vous devez entrer une chaîne constituée uniquement de 0 et de 1.");
    }

    public static Validator<String> validateOrder() {
        return Validator.createPredicateValidator(Util::isOrder, "Vous devez entrer un nombre entier positif de type 2^n et supérieur à 1 (ex : 2, 4, 8, 16).");
    }

    public boolean validate() {
        return validators.stream().allMatch(BaseValidator::validate);
    }

    public abstract void toJson(JSONObject json);

    public abstract void fromJson(JSONObject json) throws JSONException;

    private static class CustomValidationDecoration extends GraphicValidationDecoration {

        @Override
        protected Collection<Decoration> createRequiredDecorations(Control target) {
            return Collections.emptySet();
        }

    }

    private static class BaseValidator {

        final TextInputControl c;
        final Predicate<TextInputControl> p;

        BaseValidator(TextInputControl c, Predicate<TextInputControl> p) {
            this.c = c;
            this.p = p;
        }

        public boolean validate() {
            return p.test(c);
        }
    }

    public static class CheckBoxValidator extends BaseValidator {

        final CheckBox[] b;

        private CheckBoxValidator(CheckBox[] b, TextInputControl c, Predicate<TextInputControl> p) {
            super(c, p);
            this.b = b;
        }

        @Override
        public boolean validate() {
            return Stream.of(b).anyMatch(b0 -> !b0.isSelected()) || p.test(c);
        }
    }

    public static class RadioButtonValidator extends BaseValidator {

        final RadioButton[] b;

        private RadioButtonValidator(RadioButton[] b, TextInputControl c, Predicate<TextInputControl> p) {
            super(c, p);
            this.b = b;
        }

        @Override
        public boolean validate() {
            return Stream.of(b).anyMatch(b0 -> !b0.isSelected()) || p.test(c);
        }
    }

    public static class ModulationValidator extends RadioButtonValidator {

        private ModulationValidator(RadioButton b, TextInputControl c) {
            super(new RadioButton[]{b}, c, Util::isDouble);
        }

        @Override
        public boolean validate() {
            return c.getText().isEmpty() || Stream.of(b).anyMatch(b0 -> !b0.isSelected()) || p.test(c);
        }
    }
}
