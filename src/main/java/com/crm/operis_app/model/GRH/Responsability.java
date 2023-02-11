package com.crm.operis_app.model.GRH;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "RESPONSABILITY")

public class Responsability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESPONSABILITY_ID")
    private Long id;

    @Column(name = "RESPONSABILITY_NAME")
    private String responsabiliyName ;

    @Column(name = "COMMENT")
    private String comment ;


    //----------------------POST-----------------//
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id" , nullable = false)
    private Post post;
    //---------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsabiliyName() {
        return responsabiliyName;
    }

    public void setResponsabiliyName(String responsabiliyName) {
        this.responsabiliyName = responsabiliyName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
}
