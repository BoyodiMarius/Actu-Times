/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import fr.marcorp.ActuTimes.entities.articles;
import fr.marcorp.ActuTimes.services.articlesServiceBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class PublicationMBean implements Serializable {
     @EJB
    private articlesServiceBeanLocal articlesService;
    private List<articles> listeArticles;
    
}
