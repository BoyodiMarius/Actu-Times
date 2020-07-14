/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "articles")
public class articles extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARTICLES")
    private Long idArticles;
    @Column(name = "PRIORITE")
    private String priorite;
    @Column(name = "TITRE_ARTICLE")
    private String titreArticle;
    @Column(name = "CONTENU_ARTICLE", length = 2000)
    private String contenuArticle;
    @Column(name = "IMAGE_ARTICLE")
    private String imageArticles;
    @Column(name = "AUTEUR_ARTICLE")
    private String auteurArticle;
    @Column(name = "DATE_PUBLICATION")
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_CATEGORIE", name="ID_CATEGORIE")
    private categories idCategorie;

    public articles() {
    }

    public articles(Long idArticles, String priorite, String titreArticle, String contenuArticle, String imageArticles, String auteurArticle, Date datePublication, categories idCategorie) {
        this.idArticles = idArticles;
        this.priorite = priorite;
        this.titreArticle = titreArticle;
        this.contenuArticle = contenuArticle;
        this.imageArticles = imageArticles;
        this.auteurArticle = auteurArticle;
        this.datePublication = datePublication;
        this.idCategorie = idCategorie;
    }

  

    public Long getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(Long idArticles) {
        this.idArticles = idArticles;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getContenuArticle() {
        return contenuArticle;
    }

    public void setContenuArticle(String contenuArticle) {
        this.contenuArticle = contenuArticle;
    }

    public String getImageArticles() {
        return imageArticles;
    }

    public void setImageArticles(String imageArticles) {
        this.imageArticles = imageArticles;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public categories getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(categories idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getAuteurArticle() {
        return auteurArticle;
    }

    public void setAuteurArticle(String auteurArticle) {
        this.auteurArticle = auteurArticle;
    }
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
