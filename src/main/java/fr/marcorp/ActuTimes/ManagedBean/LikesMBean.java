/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import fr.marcorp.ActuTimes.entities.articles;
import fr.marcorp.ActuTimes.entities.likes;
import fr.marcorp.ActuTimes.services.articlesServiceBeanLocal;
import fr.marcorp.ActuTimes.services.likesServiceBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean(name="LikesMBean")
@ViewScoped
public class LikesMBean implements Serializable {
    @EJB
    private likesServiceBeanLocal likesService;
    private List<likes> listeLikesTotal, listeLikesUtilisateur;
    private likes formObjectLikes, verif;
    
    @EJB
    private articlesServiceBeanLocal articleService;
    private articles formObjectArticle;
    private  List<articles> ListeArticleLike;
    
    @ManagedProperty(value = "#{ActiveMBean}")
    private ActiveMBean activation;
    
    @ManagedProperty(value = "#{ConnexionMBean}")
    private ConnexionMBean info;
    
    public LikesMBean() {
        this.formObjectLikes = new likes();
        formObjectArticle = new articles();
        this.verif = new likes();
    }
    
    @PostConstruct
    public void chargerElement() {
        System.out.println("mes likes");
        this.listeLikesTotal = new ArrayList();
        this.listeLikesUtilisateur = new ArrayList();
        this.ListeArticleLike = new ArrayList();
        this.listeLikesTotal = this.likesService.selectionnerTout();
	rafraichir();
        
        int n=listeLikesTotal.size();
        if(n!=0){
            for(int i=0;i<n;i++){
                if(listeLikesTotal.get(i).getIdLike().getIdUtilisateurs().equals(info.getUtilisateurConnecter().getIdUtilisateurs())){
                    listeLikesUtilisateur.add(listeLikesTotal.get(i));
                    formObjectArticle = new articles();
                    formObjectArticle = articleService.selectionner(listeLikesTotal.get(i).getIdLike().getIdArticles());
                    ListeArticleLike.add(formObjectArticle);
                    
                }
            }
        }
    }
    
    public void rafraichir(){
        this.formObjectLikes = new likes();
        this.verif = new likes();
        this.activation.chargerElement();
        this.activation.setConnexions("active");
    }

    
    
    public likesServiceBeanLocal getLikesService() {
        return likesService;
    }

    public void setLikesService(likesServiceBeanLocal likesService) {
        this.likesService = likesService;
    }

    public List<likes> getListeLikesTotal() {
        return listeLikesTotal;
    }

    public void setListeLikesTotal(List<likes> listeLikesTotal) {
        this.listeLikesTotal = listeLikesTotal;
    }

    public List<likes> getListeLikesUtilisateur() {
        return listeLikesUtilisateur;
    }

    public void setListeLikesUtilisateur(List<likes> listeLikesUtilisateur) {
        this.listeLikesUtilisateur = listeLikesUtilisateur;
    }

    public likes getFormObjectLikes() {
        return formObjectLikes;
    }

    public void setFormObjectLikes(likes formObjectLikes) {
        this.formObjectLikes = formObjectLikes;
    }

    public likes getVerif() {
        return verif;
    }

    public void setVerif(likes verif) {
        this.verif = verif;
    }

    public ActiveMBean getActivation() {
        return activation;
    }

    public void setActivation(ActiveMBean activation) {
        this.activation = activation;
    }

    public ConnexionMBean getInfo() {
        return info;
    }

    public void setInfo(ConnexionMBean info) {
        this.info = info;
    }

    public articlesServiceBeanLocal getArticleService() {
        return articleService;
    }

    public void setArticleService(articlesServiceBeanLocal articleService) {
        this.articleService = articleService;
    }

    public List<articles> getListeArticleLike() {
        return ListeArticleLike;
    }

    public void setListeArticleLike(List<articles> ListeArticleLike) {
        this.ListeArticleLike = ListeArticleLike;
    }
    
    
   
}
