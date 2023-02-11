package com.crm.operis_app.model.authUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="IMAGES_USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ImageUser implements Serializable {
    @Id
    @Column(name = "IMAGE_USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Lob
    @Column(name="PIC" )

    private byte[] pic;

    @OneToOne(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "imageUser")
    @JsonIgnore
    private User imageUser;





    public static ImageUser fromId(Long imageUserId) {
        ImageUser imageUser = new ImageUser();
        imageUser.id = imageUserId;
        return imageUser;
    }


    public ImageUser(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public User getImageUser() {
        return imageUser;
    }

    public void setImageUser(User imageUser) {
        this.imageUser = imageUser;
    }
}
