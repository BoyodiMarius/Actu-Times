
import fr.marcorp.ActuTimes.ManagedBean.ActiveMBean;
import fr.marcorp.ActuTimes.ManagedBean.ConnexionMBean;
import fr.marcorp.ActuTimes.entities.LikePK;
import fr.marcorp.ActuTimes.entities.articles;
import fr.marcorp.ActuTimes.entities.categories;
import fr.marcorp.ActuTimes.entities.likes;
import fr.marcorp.ActuTimes.services.articlesServiceBeanLocal;
import fr.marcorp.ActuTimes.services.categoriesServiceBeanLocal;
import fr.marcorp.ActuTimes.services.likesServiceBeanLocal;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class AcceuilMBean implements Serializable{
    @EJB
    private articlesServiceBeanLocal articlesService;
    private List<articles> listeArticles, ListesArticlesDujour, ListeActualite, ListePolitique,ListeSport;
    private articles formObjectArticles;
    
    @EJB
    private categoriesServiceBeanLocal categoriesService;
    private List<categories> listeCategories;
    
    @EJB
    private likesServiceBeanLocal likeService;
    private List<likes> listeLike;
    
    private LikePK idLike;
    private likes formObjectLike;
    
    private Date dateduJour;
    private String actualite="", date="";
    
    public AcceuilMBean() {
         this.formObjectArticles = new articles();
         this.formObjectLike = new likes();
    }
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    @ManagedProperty(value = "#{ConnexionMBean}")
    private ConnexionMBean info;
    
    @PostConstruct
    public void chargerElement() {
	this.listeArticles = new ArrayList();
        this.listeCategories = new ArrayList();
        this.ListesArticlesDujour = new ArrayList();
        this.ListeActualite = new ArrayList();
        this.ListePolitique = new ArrayList();
        this.ListeSport = new ArrayList();
        this.listeLike = new ArrayList();
        
        this.listeArticles = this.articlesService.selectionnerTout();
        this.listeCategories = this.categoriesService.selectionnerTout();
        this.listeLike = this.likeService.selectionnerTout();
       
        dateduJour =  new Date();
        date = dateToString(dateduJour);
        int n=listeArticles.size();
        if(n!=0){
            for(int i=0;i<n;i++){
                formObjectArticles = listeArticles.get(i);
                if(formObjectArticles.getPriorite().equals("A la une") && dateToString(formObjectArticles.getDatePublication()).equals(date)){
                    ListesArticlesDujour.add(formObjectArticles);
                    actualite = actualite.concat("  ***  "+formObjectArticles.getTitreArticle());
                }
                if(dateToString(formObjectArticles.getDatePublication()).equals(date)){
                   ListeActualite.add(formObjectArticles);
                   if(formObjectArticles.getIdCategorie().getLibelleCategorie().equals("Politique")){
                       ListePolitique.add(formObjectArticles);
                   }
                   if(formObjectArticles.getIdCategorie().getLibelleCategorie().equals("Sport")){
                       ListeSport.add(formObjectArticles);
                   }
                }
                
            }
        }
        
       this.activation.remiseAzero();
       this.activation.setLaUne("active");
    }
    
    public static String dateToString(Date date) {
        String jour = "" + date.getDate();
        String mois = "" + (date.getMonth() + 1);
        String annee = "" + (date.getYear() + 1900);
        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return (jour + "/" + mois + "/" + annee);
    }

    public void Like(ActionEvent event){        
        HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
        String Id = (String) button.getStyleClass();
        boolean verif1=false,verif2=false, verif3=false;
        if(info.getUtilisateurConnecter()==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Aucun utilisateur n'est connecté"));
             verif1=true;
        }
        if("".equals(Id)){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez selectionner un article"));
             verif2=true;
        }
        if(verif1==false && verif2==false){
            long idArticle = Long.parseLong(Id);
            long idUser =  info.getUtilisateurConnecter().getIdUtilisateurs();
            int n = listeLike.size();
            if(n!=0){
                for(int i=0;i<n;i++){
                    if(listeLike.get(i).getIdLike().getIdArticles()==idArticle &&listeLike.get(i).getIdLike().getIdUtilisateurs()==idUser){
                        verif3=true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avvertissement!", "Vous avez déjà liké cet article"));
                    }
                }
            }
            if(verif3==false){
                idLike = new LikePK();
                idLike.setIdArticles(idArticle);
                idLike.setIdUtilisateurs(idUser);
                this.formObjectLike = new likes();
                formObjectLike.setIdLike(idLike);
                formObjectLike.setDateLike(dateduJour);
                likeService.ajouter(formObjectLike);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Article Liké"));
                chargerElement();
            }
            
        }
        
        
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

    public List<articles> getListesArticlesDujour() {
        return ListesArticlesDujour;
    }

    public void setListesArticlesDujour(List<articles> ListesArticlesDujour) {
        this.ListesArticlesDujour = ListesArticlesDujour;
    }

    public articles getFormObjectArticles() {
        return formObjectArticles;
    }

    public void setFormObjectArticles(articles formObjectArticles) {
        this.formObjectArticles = formObjectArticles;
    }

    public Date getDateduJour() {
        return dateduJour;
    }

    public void setDateduJour(Date dateduJour) {
        this.dateduJour = dateduJour;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }

    public String getActualite() {
        return actualite;
    }

    public void setActualite(String actualite) {
        this.actualite = actualite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<articles> getListeActualite() {
        return ListeActualite;
    }

    public void setListeActualite(List<articles> ListeActualite) {
        this.ListeActualite = ListeActualite;
    }

    public List<articles> getListePolitique() {
        return ListePolitique;
    }

    public void setListePolitique(List<articles> ListePolitique) {
        this.ListePolitique = ListePolitique;
    }

    public List<articles> getListeSport() {
        return ListeSport;
    }

    public void setListeSport(List<articles> ListeSport) {
        this.ListeSport = ListeSport;
    }

    public likesServiceBeanLocal getLikeService() {
        return likeService;
    }

    public void setLikeService(likesServiceBeanLocal likeService) {
        this.likeService = likeService;
    }

    public LikePK getIdLike() {
        return idLike;
    }

    public void setIdLike(LikePK idLike) {
        this.idLike = idLike;
    }

    public likes getFormObjectLike() {
        return formObjectLike;
    }

    public void setFormObjectLike(likes formObjectLike) {
        this.formObjectLike = formObjectLike;
    }

    public ConnexionMBean getInfo() {
        return info;
    }

    public void setInfo(ConnexionMBean info) {
        this.info = info;
    }
    
    
    
}
