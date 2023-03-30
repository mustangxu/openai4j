/**
 *
 */
package com.jayxu.openai4j.model;

/**
 * @author Jay Xu @2023
 */
public enum ModelType {
    GPT_35_TURBO("gpt-3.5-turbo"),
    TEXT_DAVINCI_003("text-davinci-003");

    private final String value;

    ModelType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
