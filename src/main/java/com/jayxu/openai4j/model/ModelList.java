/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class ModelList {
    String object;
    List<Model> data;
}
