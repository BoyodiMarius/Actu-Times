/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean(name="ActiveMBean")
@SessionScoped
public class ActiveMBean implements Serializable {
    
    private String laUne,inscription,connexions,parametres,meslikes,publications;
     
    public ActiveMBean() {
        this.laUne = "";
        this.inscription = "";
        this.connexions= "";
        this.parametres= "";
        this.meslikes= "";
        this.publications="";
    }

    @PostConstruct
    public void chargerElement() {
        this.laUne = "";
        this.inscription = "";
        this.connexions= "";
        this.parametres= "";
        this.meslikes= "";
        this.publications="";
    }
    
    public void remiseAzero(){
        this.laUne = "";
        this.inscription = "";
        this.connexions= "";
        this.parametres= "";
        this.meslikes= "";
        this.publications="";
    }
    
    public String getLaUne() {
        return laUne;
    }

    public void setLaUne(String laUne) {
        this.laUne = laUne;
    }

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }

    public String getConnexions() {
        return connexions;
    }

    public void setConnexions(String connexions) {
        this.connexions = connexions;
    }

    public String getParametres() {
        return parametres;
    }

    public void setParametres(String parametres) {
        this.parametres = parametres;
    }

    public String getMeslikes() {
        return meslikes;
    }

    public void setMeslikes(String meslikes) {
        this.meslikes = meslikes;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }
    
    
}
