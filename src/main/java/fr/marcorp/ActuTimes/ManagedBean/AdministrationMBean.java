/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import fr.marcorp.ActuTimes.entities.articles;
import fr.marcorp.ActuTimes.entities.categories;
import fr.marcorp.ActuTimes.services.articlesServiceBeanLocal;
import fr.marcorp.ActuTimes.services.categoriesServiceBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.omnifaces.util.Ajax;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class AdministrationMBean implements Serializable {
    @EJB
    private articlesServiceBeanLocal articlesService;
    private List<articles> listeArticles;
    private articles formObjectArticles, selectObjectArticles;
    
    @EJB
    private categoriesServiceBeanLocal categoriesService;
    private List<categories> listeCategories;
    private categories formObjectCategories, categorie;
    
    private UploadedFile imageArticles = null, imageArticlesModifier =null;
    
    private Date datePublication;
    
    private String niveau, titre, contenue, auteur;
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    
     public AdministrationMBean() {
        this.formObjectArticles = new articles();
        this.formObjectCategories = new categories();
        categorie =  new categories();
        datePublication =  null;
        niveau = "";
        titre="";
        contenue="";
        auteur="";
    }
    
  
    
    @PostConstruct
    public void chargerElement() {
	this.listeArticles = new ArrayList();
        this.listeArticles = this.articlesService.selectionnerTout();
        this.listeCategories = new ArrayList();
        this.listeCategories = this.categoriesService.selectionnerTout();
        this.imageArticles = null;
        this.imageArticlesModifier = null;
        categorie =  new categories();
        datePublication =  null;
        niveau = "";
        titre="";
        contenue="";
        auteur = "";
       this.activation.remiseAzero();
       this.activation.setLaUne("active");
    }

    public void rafraichir(){
        this.formObjectArticles = new articles();
        this.selectObjectArticles = new articles();
        this.formObjectCategories = new categories();
        this.imageArticles = null;
        this.imageArticlesModifier = null;
        categorie =  new categories();
        datePublication =  null;
        niveau = "";
        titre="";
        contenue="";
        auteur = "";
    }
    
     public void ajouter(){
        try {
            uploaderImg1();
            formObjectArticles.setPriorite(niveau);
            formObjectArticles.setIdCategorie(categorie);
            formObjectArticles.setDatePublication(datePublication);
            formObjectArticles.setTitreArticle(titre);
            formObjectArticles.setContenuArticle(contenue);
            formObjectArticles.setAuteurArticle(auteur);
            this.articlesService.ajouter(formObjectArticles);
            succes();
            rafraichir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void enregistrer(){
        boolean verif1=false,verif2=false,verif3=false,verif4=false,verif5=false,verif6=false;
        if(niveau.trim().equals("niveau")){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le niveau de l'article"));
             verif1=true;
        }
        if(categorie==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la catégorie de l'article"));
             verif2=true;
        }
        if(datePublication==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la date de publication de l'article"));
             verif3=true;
        }
        if(titre.trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le titre de l'article"));
             verif4=true;
        }
        if(contenue.trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le contenu de l'article"));
             verif5=true;
        }
        if(auteur.trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner l'auteur de l'article"));
             verif6=true;
        }
        if(verif1==false && verif2 == false && verif3 == false && verif4 == false && verif5 == false && verif6 == false){
            ajouter();
        }
    }
    
    public void modifier(){
        boolean verif1=false,verif2=false,verif3=false,verif4=false,verif5=false,verif6=false;
        if(selectObjectArticles.getPriorite().trim().equals("niveau")){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le niveau de l'article"));
             verif1=true;
        }
        if(selectObjectArticles.getIdCategorie()==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la catégorie de l'article"));
             verif2=true;
        }
        if(selectObjectArticles.getDatePublication()==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la date de publication de l'article"));
             verif3=true;
        }
        if(selectObjectArticles.getTitreArticle().trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le titre de l'article"));
             verif4=true;
        }
        if(selectObjectArticles.getContenuArticle().trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le contenu de l'article"));
             verif5=true;
        }
        if(selectObjectArticles.getAuteurArticle().trim().length()==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner l'auteur de l'article"));
             verif6=true;
        }
        if(verif1==false && verif2 == false && verif3 == false && verif4 == false && verif5 == false&& verif6 == false){
            
            try {
            uploaderImgModification();            
            this.articlesService.modifier(selectObjectArticles);
            succes();
            rafraichir();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        }
    }
    
    public void enregistrerCategorie(){
         boolean verif1=false;
         if(formObjectCategories.getLibelleCategorie().trim().length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la designation de la categorie"));
            verif1=true;
         }
         if(verif1==false){
            try {
                this.categoriesService.ajouter(formObjectCategories);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Catégorie enrégistrer"));
            } catch (Exception e) {
                e.printStackTrace();
            }
         }
    }
     public void succes(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Article publier"));
     }
    
    public void uploaderImg1() {
        try {
             String img1="";
            if (this.imageArticles.getFileName().equals("")) {
                //formObjectArticles.setImageArticles(img1);
            } else {
                InputStream in = this.imageArticles.getInputstream();
                String image = String.valueOf((int) (Math.random() * 10000000));
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "assets" + File.separator  + "images"+ File.separator + image + this.imageArticles.getFileName();
                File f = new File(newFileName);
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                img1 = "resources/assets/images/" +image+ this.imageArticles.getFileName();
                formObjectArticles.setImageArticles(img1);
                out.close();
                in.close();
            }
           
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void uploaderImgModification() {
        try {
             String img1="";
            if (this.imageArticlesModifier.getFileName().equals("")) {
                System.out.println("null");
            } else {
                InputStream in = this.imageArticlesModifier.getInputstream();
                String image = String.valueOf((int) (Math.random() * 10000000));
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "assets" + File.separator + "images"+ File.separator + image + this.imageArticlesModifier.getFileName();
                File f = new File(newFileName);
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                img1 = "resources/assets/images/" +image+ this.imageArticlesModifier.getFileName();
                selectObjectArticles.setImageArticles(img1);
                out.close();
                in.close();
            }
           
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void rowSelect(){
        Ajax.oncomplete("PF('modifDlg').show()");
    }
    
    
    
    public articlesServiceBeanLocal getArticlesService() {
        return articlesService;
    }

    public void setArticlesService(articlesServiceBeanLocal articlesService) {
        this.articlesService = articlesService;
    }

    public List<articles> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<articles> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public articles getFormObjectArticles() {
        return formObjectArticles;
    }

    public void setFormObjectArticles(articles formObjectArticles) {
        this.formObjectArticles = formObjectArticles;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }

    public UploadedFile getImageArticles() {
        return imageArticles;
    }

    public void setImageArticles(UploadedFile imageArticles) {
        this.imageArticles = imageArticles;
    }

    public categoriesServiceBeanLocal getCategoriesService() {
        return categoriesService;
    }

    public void setCategoriesService(categoriesServiceBeanLocal categoriesService) {
        this.categoriesService = categoriesService;
    }

    public List<categories> getListeCategories() {
        return listeCategories;
    }

    public void setListeCategories(List<categories> listeCategories) {
        this.listeCategories = listeCategories;
    }

    public categories getFormObjectCategories() {
        return formObjectCategories;
    }

    public void setFormObjectCategories(categories formObjectCategories) {
        this.formObjectCategories = formObjectCategories;
    }

    public categories getCategorie() {
        return categorie;
    }

    public void setCategorie(categories categorie) {
        this.categorie = categorie;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public articles getSelectObjectArticles() {
        return selectObjectArticles;
    }

    public void setSelectObjectArticles(articles selectObjectArticles) {
        this.selectObjectArticles = selectObjectArticles;
    }

    public UploadedFile getImageArticlesModifier() {
        return imageArticlesModifier;
    }

    public void setImageArticlesModifier(UploadedFile imageArticlesModifier) {
        this.imageArticlesModifier = imageArticlesModifier;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    
}
