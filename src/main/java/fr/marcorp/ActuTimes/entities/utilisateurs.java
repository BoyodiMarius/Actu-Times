/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "utilisateurs")
public class utilisateurs extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTILISATEUR")
    private Long idUtilisateurs;
    @Column(name = "Email")
    private String email;
    @Column(name = "Pseudo")
    private String pseudo;
    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;
    @Column(name = "PRIVILEGE")
    private String privilege;
    @Column(name = "PHOTO_PROFIL")
    private String photProfil;

    public utilisateurs() {
    }

    public utilisateurs(Long idUtilisateurs, String email, String pseudo, String motDePasse, String privilege, String photProfil) {
        this.idUtilisateurs = idUtilisateurs;
        this.email = email;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.privilege = privilege;
        this.privilege = photProfil;

    }

    public String getPhotProfil() {
        return photProfil;
    }

    public void setPhotProfil(String photProfil) {
        this.photProfil = photProfil;
    }

    public Long getIdUtilisateurs() {
        return idUtilisateurs;
    }

    public void setIdUtilisateurs(Long idUtilisateurs) {
        this.idUtilisateurs = idUtilisateurs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

   

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
