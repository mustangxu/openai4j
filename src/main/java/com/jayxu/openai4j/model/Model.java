/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class Model {
    String id;
    String object;
    Long created;
    @SerializedName("owned_by")
    String ownedBy;
    List<Map<String, String>> permission;
    String root;
    String parent;
}
