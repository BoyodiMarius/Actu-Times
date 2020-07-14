/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.services;

import fr.marcop.ActuTimes.dao.BaseDaoBeanLocal;
import fr.marcop.ActuTimes.dao.likesDaoBeanLocal;
import fr.marcorp.ActuTimes.entities.likes;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class likesServiceBean extends BaseServiceBean<likes, Long> implements likesServiceBeanLocal {
 
    @EJB
    private likesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<likes, Long> getDao(){
        return this.dao;
    }
    
}
