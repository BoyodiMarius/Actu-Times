/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.services;

import fr.marcop.ActuTimes.dao.BaseDaoBeanLocal;
import fr.marcop.ActuTimes.dao.articlesDaoBeanLocal;
import fr.marcorp.ActuTimes.entities.articles;
import javax.ejb.EJB;
import javax.ejb.Stateless;



/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class articlesServiceBean extends BaseServiceBean<articles, Long> implements articlesServiceBeanLocal {
 
    @EJB
    private articlesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<articles, Long> getDao(){
        return this.dao;
    }
}
