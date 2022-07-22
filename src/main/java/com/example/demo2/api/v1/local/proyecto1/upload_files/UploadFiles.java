
package com.example.demo2.api.v1.local.proyecto1.upload_files;


public class UploadFiles {
    
    private String name;
    private String url;

    public UploadFiles(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
