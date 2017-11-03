package com.cnpc.admin.entity;

import javax.persistence.*;

@Table(name = "base_server")
public class ServerManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "store_house")
    private String storeHouse;

    @Column(name = "image_name")
    private String imageName;

    private String tag;

    private String enabled;

    private String description;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return server_name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return store_house
     */
    public String getStoreHouse() {
        return storeHouse;
    }

    /**
     * @param storeHouse
     */
    public void setStoreHouse(String storeHouse) {
        this.storeHouse = storeHouse;
    }

    /**
     * @return image_name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return enabled
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}