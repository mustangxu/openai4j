/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class Model {
    String id;
    String object;
    Long created;
    @JsonProperty("owned_by")
    String ownedBy;
    List<Map<String, String>> permission;
    String root;
    String parent;
}
