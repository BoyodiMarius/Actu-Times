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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Faces;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean(name="ConnexionMBean")
@SessionScoped
public class ConnexionMBean implements Serializable {
    @EJB
    private utilisateurServiceBeanLocal utilisateurService;
    private utilisateurs utilisateurConnecter;
   
    private String login, pwd, info,profil;
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    
    public ConnexionMBean() {
        
    }
    
    @PostConstruct
    public void chargerElement() {
	rafraichir();
        List<utilisateurs> listAdmin = this.utilisateurService.selectionnerTout("pseudo", "Admin");
        if (listAdmin.isEmpty()) {
             utilisateurs ad = new utilisateurs();
             ad.setEmail("admin@admin.com");
             ad.setPseudo("Admin");
             ad.setMotDePasse(new Sha256Hash("admin").toHex());
             ad.setPrivilege("ADMIN");
             ad.setPhotProfil("resources/assets/images/profil_vide.jpg");
             this.utilisateurService.ajouter(ad);
        }
           
    }
    
    public void rafraichir(){
        System.out.println("connexion");
        this.login = "";
        this.pwd = "";
        this.activation.remiseAzero();
        this.activation.setConnexions("active");
    }
    
    public void submit() throws IOException {
        boolean controle1 = false;
        utilisateurs user = new utilisateurs();
      
        if(login != null && pwd != null){
            
            controle1 = true;
        }
        if(controle1 == true){
            try{
                List<utilisateurs> listU = this.utilisateurService.selectionnerTout("email", login);
                for (utilisateurs u : listU) {
                    if(u.getEmail().equals(login)){
                        user = u;       
                        break;
                    }
                }
                String privilege1="ADMIN";
                String privilege2="Utilisateur Simple";
                //System.out.println("Utilisateur : " + user.getEmail() + " - " + user.getMotDePasse()); 
                if(user != null){
                    if (user.getMotDePasse().equalsIgnoreCase(new Sha256Hash(pwd).toHex()) && user.getPrivilege().equalsIgnoreCase(privilege1)) {
                        info = user.getPseudo();
                        profil = user.getPhotProfil();
                        utilisateurConnecter = user;
                        Faces.redirect("AcceuilAdministrateur.xhtml");
                    } else  if (user.getMotDePasse().equalsIgnoreCase(new Sha256Hash(pwd).toHex()) && user.getPrivilege().equalsIgnoreCase(privilege2)) {
                        Faces.redirect("AcceuilClient.xhtml");
                        info = user.getPseudo();
                        profil = user.getPhotProfil();
                        utilisateurConnecter = user;
                    }  else {
                    System.out.println("ok");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification : Login ou mot de Passe incorrect "));
                    }
                } else {
                    System.out.println("ok");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification : Login ou mot de Passe incorrect "));
                }
               
            } catch(NullPointerException e){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification: Login ou mot de Passe incorrect  "));
                
            }
             
        }
        if(controle1 == false){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification "));
        }
    } 

    public void deconnexion() throws IOException{
         Faces.redirect("AcceuilOfficiel.xhtml");
    }

    public utilisateurServiceBeanLocal getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(utilisateurServiceBeanLocal utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }

    public utilisateurs getUtilisateurConnecter() {
        return utilisateurConnecter;
    }

    public void setUtilisateurConnecter(utilisateurs utilisateurConnecter) {
        this.utilisateurConnecter = utilisateurConnecter;
    }
    
    
    
}
