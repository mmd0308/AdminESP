package com.cnpc.admin.entity;

import javax.persistence.*;

@Table(name = "base_dict")
public class Dict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String code;
    @Column(name = "level_code")
    private String levelCode;
    private String lspell;
    private String initials;
    private String uspell;
    @Column(name = "parent_id")
    private String parentId;
    private String enabled;
    private String description;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLspell() {
        return lspell;
    }

    public void setLspell(String lspell) {
        this.lspell = lspell;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUspell() {
        return uspell;
    }

    public void setUspell(String uspell) {
        this.uspell = uspell;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}