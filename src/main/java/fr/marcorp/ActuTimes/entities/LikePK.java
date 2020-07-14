/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Embeddable
public class LikePK implements Serializable {
    
    @Column(name = "ID_ARTICLES")
    private Long idArticles;
    @Column(name = "ID_UTILISATEUR")
    private Long idUtilisateurs;

    public LikePK() {
    }

    public LikePK(Long idArticles, Long idUtilisateurs) {
        this.idArticles = idArticles;
        this.idUtilisateurs = idUtilisateurs;
    }

    public Long getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(Long idArticles) {
        this.idArticles = idArticles;
    }

    public Long getIdUtilisateurs() {
        return idUtilisateurs;
    }

    public void setIdUtilisateurs(Long idUtilisateurs) {
        this.idUtilisateurs = idUtilisateurs;
    }
    
    
    
}
