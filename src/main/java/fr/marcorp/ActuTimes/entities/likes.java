/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "likes")
public class likes extends BaseEntity {
    @EmbeddedId
    private LikePK idLike;
    @Column(name = "DATE_LIKE")
    @Temporal(TemporalType.DATE)
    private Date dateLike;

    public likes() {
    }

    public likes(LikePK idLike, Date dateLike) {
        this.idLike = idLike;
        this.dateLike = dateLike;
    }

    public LikePK getIdLike() {
        return idLike;
    }

    public void setIdLike(LikePK idLike) {
        this.idLike = idLike;
    }

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
