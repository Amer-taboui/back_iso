package com.crm.operis_app.model.GRH;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostPersonnelCount {
     String posteName;
     Long id;
     Long personnelCount;

    public PostPersonnelCount(String posteName, Long id, Long personnelCount) {
        this.posteName = posteName;
        this.id = id;
        this.personnelCount = personnelCount;
    }

    public String getPosteName() {
        return posteName;
    }

    public void setPosteName(String posteName) {
        this.posteName = posteName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonnelCount() {
        return personnelCount;
    }

    public void setPersonnelCount(Long personnelCount) {
        this.personnelCount = personnelCount;
    }

// getters and setters
}