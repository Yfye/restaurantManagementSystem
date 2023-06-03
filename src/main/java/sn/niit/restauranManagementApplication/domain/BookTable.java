package sn.niit.restauranManagementApplication.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;

@Entity
public class BookTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @NotEmpty
    // @Column
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String message;

    public BookTable(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Long getTableId() {
        return tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
