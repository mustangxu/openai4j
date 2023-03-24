/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @author Jay Xu @2023
 */
@Data
@ToString
public class ModelList {
    String object;
    List<Model> data;
}
