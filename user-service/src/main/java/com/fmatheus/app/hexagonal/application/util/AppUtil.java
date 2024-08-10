package com.fmatheus.app.hexagonal.application.util;

import com.fmatheus.app.hexagonal.application.domain.exception.ValidationNotNullException;

import java.util.Objects;

public class AppUtil {

    private AppUtil() {
        throw new IllegalStateException(getClass().getName());
    }

    public static void isNotNull(String attribute, String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationNotNullException(String.format("O atributo [%s] não pode ser nulo ou vazio", attribute));
        }
    }

    /**
     * Converte o primeiro caracter de cada palavra em maiusculo.
     *
     * @param value String
     * @return {@link String}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String convertFirstUppercaseCharacter(String value) {
        return Objects.nonNull(value) ? capitalizeWords(Objects.requireNonNull(removeDuplicateSpace(value))).trim() : null;
    }

    private static String capitalizeWords(String value) {
        StringBuilder result = new StringBuilder(value.length());
        String[] words = value.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    /**
     * Remove duplicidade de espaco em uma string.
     *
     * @param value Valor a ser removido as duplicidades.
     * @return {@link String}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String removeDuplicateSpace(String value) {
        return Objects.nonNull(value) ? value.replaceAll("\\s+", " ").trim() : null;
    }

    public static String removeSpecialCharacters(String value) {
        return Objects.nonNull(value) ? value.replaceAll("[^a-zA-Z0-9]", "") : null;
    }

    /**
     * Remove todos os espacos de uma string.
     *
     * @param value Valor a ser removido todos os espacos.
     * @return {@link String}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String removeAllSpaces(String value) {
        return Objects.nonNull(value) ? value.replace(" ", "") : null;
    }

    /**
     * Converter todos caracteres de uma string em maiusculo.
     *
     * @param value Valor a ser convertido.
     * @return {@link String}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String convertAllUppercaseCharacters(String value) {
        return Objects.nonNull(value) ? value.toUpperCase().trim() : null;
    }

    /**
     * Converter todos caracteres de uma string em minusculo.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String convertAllLowercaseCharacters(String value) {
        return Objects.nonNull(value) ? value.toLowerCase().trim() : null;
    }

}
