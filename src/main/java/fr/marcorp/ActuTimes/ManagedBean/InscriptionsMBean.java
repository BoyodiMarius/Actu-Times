/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import fr.marcorp.ActuTimes.entities.utilisateurs;
import fr.marcorp.ActuTimes.services.utilisateurServiceBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Faces;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class InscriptionsMBean implements Serializable {
    
    @EJB
    private utilisateurServiceBeanLocal utilisateurService;
    private List<utilisateurs> listeUtilisateurs;
    private utilisateurs formObjectUtilisateur, verif;
    
    private String pseudo, email, pwd, confirmMdp;
    
    public InscriptionsMBean() {
        this.formObjectUtilisateur = new utilisateurs();
    }
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    
    @PostConstruct
    public void chargerElement() {
	this.listeUtilisateurs = new ArrayList();
        this.listeUtilisateurs = this.utilisateurService.selectionnerTout();
        pseudo = "";
        email = "";
        pwd = "";
        confirmMdp = "";
       this.activation.remiseAzero();
       this.activation.setInscription("active");
    }
    
    public void ajouter() {
        try {
            formObjectUtilisateur.setPseudo(pseudo);
            formObjectUtilisateur.setEmail(email);
            formObjectUtilisateur.setMotDePasse(new Sha256Hash(pwd).toHex());
            formObjectUtilisateur.setPrivilege("Utilisateur Simple");
            formObjectUtilisateur.setPhotProfil("resources/images/profil_vide.jpg");
            this.utilisateurService.ajouter(formObjectUtilisateur);
            succes();
            rafraichir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void enregistrer(){
       boolean verif1=false,verif2=false,verif3=false,verif4=false,verif5=false, verifMail=false;
       if(pseudo.trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner votre pseudo"));
             verif1=true;
         }
       if(email.trim().length()==0){ 
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner votre email"));
             verif2=true;
        }
      
        if(verif1==false && verif2==false){
            Pattern rfc2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
            if (rfc2822.matcher(email.trim()).matches()) {
                verifMail=true;
            } else {
                verifMail=false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "L'email n'est pas valide "));
            }
            if (pwd.equals(confirmMdp)){
               verif3=true;
            } else {
                verif3=false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Mot de passe non conforme"));
            }
            if(listeUtilisateurs.isEmpty()){
                verif4=true;
                verif5=true;
            }else {
                verif4=true;
                verif5=true;
                for(int i=0; i<listeUtilisateurs.size();i++){
                    verif = listeUtilisateurs.get(i);
                    if (verif.getPseudo().equals(pseudo)){
                        verif4 = false;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Pseudo déjà utilisé. Veuillez choisir un autre Pseudo"));
                    }
                    if (verif.getEmail().equals(email)){
                        verif5 = false;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Cet email est dèjà utilisé. Veuillez choisir une autre adresse mail"));
                    } 
                }
            }
        }
        if(verif3==true && verif4==true && verif5==true && verifMail==true){
            ajouter();
        }
    }
     public void succes(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Inscription éffectuer"));
     }
     
     public void succesModif(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modification éffectuer"));
     }
     
     public void rafraichir() {
        this.chargerElement();
        formObjectUtilisateur = new utilisateurs();
    }

    public utilisateurServiceBeanLocal getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(utilisateurServiceBeanLocal utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<utilisateurs> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void setListeUtilisateurs(List<utilisateurs> listeUtilisateurs) {
        this.listeUtilisateurs = listeUtilisateurs;
    }

    public utilisateurs getFormObjectUtilisateur() {
        return formObjectUtilisateur;
    }

    public void setFormObjectUtilisateur(utilisateurs formObjectUtilisateur) {
        this.formObjectUtilisateur = formObjectUtilisateur;
    }

    public String getConfirmMdp() {
        return confirmMdp;
    }

    public void setConfirmMdp(String confirmMdp) {
        this.confirmMdp = confirmMdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public utilisateurs getVerif() {
        return verif;
    }

    public void setVerif(utilisateurs verif) {
        this.verif = verif;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }

    
     
     
}
