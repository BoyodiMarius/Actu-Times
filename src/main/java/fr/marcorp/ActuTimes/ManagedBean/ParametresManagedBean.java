/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import fr.marcorp.ActuTimes.entities.utilisateurs;
import fr.marcorp.ActuTimes.services.utilisateurServiceBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class ParametresManagedBean implements Serializable {
    
    @EJB
    private utilisateurServiceBeanLocal utilisateurService;
    private List<utilisateurs> listeUtilisateurs;
    private utilisateurs formObjectUtilisateur;
    
    @ManagedProperty(value = "#{ConnexionMBean}")
    private ConnexionMBean recuparation;
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    
    private boolean modifPseudo=false, modifMotDePasse=true, enregistrePseudo=true;
    private String ancienMdp, newMdp, confirmMdp,verificationAncienMdp,img1, classe;
    
    private UploadedFile image1 = null;

    
    public ParametresManagedBean() {
        this.image1 = null;
    }
    
     
    @PostConstruct
    public void chargerElement() {
        
	rafraichir();
        activation.remiseAzero();
        activation.setParametres("active");
    }
    
    public void rafraichir(){
        this.image1 = null;
        this.activation.chargerElement();
        this.activation.setParametres("active");
        
        this.ancienMdp="";
        this.newMdp="";
        this.confirmMdp="";
        
        this.listeUtilisateurs = new ArrayList();
        this.listeUtilisateurs = this.utilisateurService.selectionnerTout();
        this.formObjectUtilisateur = new utilisateurs();
        this.modifPseudo = false;
        this.modifMotDePasse = true;
        this.enregistrePseudo = true;
        List<utilisateurs> listU = this.utilisateurService.selectionnerTout("pseudo", recuparation.getInfo());
                for (utilisateurs u : listU) {
                    if(u.getPseudo().equals(recuparation.getInfo())){
                        formObjectUtilisateur = u;       
                        break;
                    }
                }
        this.verificationAncienMdp = formObjectUtilisateur.getMotDePasse();
        for (utilisateurs u : listeUtilisateurs) {
             if(u.equals(formObjectUtilisateur)){
                 listeUtilisateurs.remove(u);
             }
         }
    }
    
    public void effectuerModificationPseudo() {
        
        this.modifPseudo = true;
        this.enregistrePseudo = false;
    }
    public void effectuerModificationMdp() {
        this.modifMotDePasse = false;
    }
    
    public void enregistrerPseudo() throws IOException{
        boolean verif1=false, verif2=false;
        if(formObjectUtilisateur.getPseudo().trim().length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner votre pseudo"));
             verif1=true;
        }
        if(listeUtilisateurs.isEmpty()){
            verif2=false;
        } else {
           for(int i=0; i<listeUtilisateurs.size();i++){
                    utilisateurs verif = listeUtilisateurs.get(i);
                    if (verif.getPseudo().equals(formObjectUtilisateur.getPseudo())){
                        verif2 = true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Pseudo déjà utilisé. Veuillez choisir un autre Pseudo"));
                    }
                    
                } 
        }
        if(verif1==false && verif2==false){
            utilisateurService.modifier(formObjectUtilisateur);
            recuparation.setInfo(formObjectUtilisateur.getPseudo());
            succesModif();
            rafraichir();
            //Faces.redirect("Parametres.xhtml");
            //RequestContext.getCurrentInstance().update("parametre");
        }
        
    }
    
     public void succesModif(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modification éffectuer"));
     }
     
    public void enregistrerMdp(){
         boolean verif1=false, verif2=false,verif3=false, verif4=false,verif5=false;
         if(ancienMdp.trim().length()==0){
            verif1 = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Veuillez rensigner l'ancien Mot de passe"));                    
         }
          if(newMdp.trim().length()==0){
            verif2 = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Veuillez rensigner le nouveau mot de passe"));                    
         }
         if(confirmMdp.trim().length()==0){
            verif1 = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Veuillez confirmer le nouveau mot de passe"));                    
         }
         if(verif1==false && verif2==false && verif3==false){
             
             if(verificationAncienMdp.equals(new Sha256Hash(ancienMdp.trim()).toHex())){
                 verif4=true;
             }else {
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Ancien mot de passe incorrect"));
             }
             if(newMdp.trim().equals(confirmMdp.trim())){
                 verif5=true;
             } else {
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "le nouveau mot de passe est différent la confirmation de mot de passe"));            
             }
             
              if(verif4==true && verif5==true){
                  System.out.println("ok");
                  formObjectUtilisateur.setMotDePasse(new Sha256Hash(newMdp.trim()).toHex());
                  utilisateurService.modifier(formObjectUtilisateur);
                  succesModif();
                  rafraichir();
              }
             
         }
        
         
    }
    
    public void annulerModification(){
        rafraichir();
    }
    
    public void uploaderImg1() {
        try {
             
            if (image1!= null) {
                InputStream in = image1.getInputstream();
                String image = String.valueOf((int) (Math.random() * 10000000));
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "assets" + File.separator + "images"+ File.separator + image + image1.getFileName();
                File f = new File(newFileName);
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                img1 = "resources/assets/images/" +image+ image1.getFileName();
                formObjectUtilisateur.setPhotProfil(img1);
                recuparation.setProfil(img1);
                out.close();
                in.close();

                utilisateurService.modifier(formObjectUtilisateur);
            } else {
                System.out.println("image null");
            }
           
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
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

    public ConnexionMBean getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(ConnexionMBean recuparation) {
        this.recuparation = recuparation;
    }

    public boolean isModifPseudo() {
        return modifPseudo;
    }

    public void setModifPseudo(boolean modifPseudo) {
        this.modifPseudo = modifPseudo;
    }

    public boolean isModifMotDePasse() {
        return modifMotDePasse;
    }

    public void setModifMotDePasse(boolean modifMotDePasse) {
        this.modifMotDePasse = modifMotDePasse;
    }

    public boolean isEnregistrePseudo() {
        return enregistrePseudo;
    }

    public void setEnregistrePseudo(boolean enregistrePseudo) {
        this.enregistrePseudo = enregistrePseudo;
    }

    public String getAncienMdp() {
        return ancienMdp;
    }

    public void setAncienMdp(String ancienMdp) {
        this.ancienMdp = ancienMdp;
    }

    public String getNewMdp() {
        return newMdp;
    }

    public void setNewMdp(String newMdp) {
        this.newMdp = newMdp;
    }

    public String getConfirmMdp() {
        return confirmMdp;
    }

    public void setConfirmMdp(String confirmMdp) {
        this.confirmMdp = confirmMdp;
    }

    public String getVerificationAncienMdp() {
        return verificationAncienMdp;
    }

    public void setVerificationAncienMdp(String verificationAncienMdp) {
        this.verificationAncienMdp = verificationAncienMdp;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public UploadedFile getImage1() {
        return image1;
    }

    public void setImage1(UploadedFile image1) {
        this.image1 = image1;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }
    
    
}
