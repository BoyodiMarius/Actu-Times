/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.services;

import fr.marcop.ActuTimes.dao.BaseDaoBeanLocal;
import fr.marcop.ActuTimes.dao.categoriesDaoBeanLocal;
import fr.marcorp.ActuTimes.entities.categories;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class categoriesServiceBean extends BaseServiceBean<categories, Long> implements categoriesServiceBeanLocal {
 
    @EJB
    private categoriesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<categories, Long> getDao(){
        return this.dao;
    }
    
}
