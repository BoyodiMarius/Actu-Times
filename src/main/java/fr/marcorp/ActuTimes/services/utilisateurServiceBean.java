/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.services;

import fr.marcop.ActuTimes.dao.BaseDaoBeanLocal;
import fr.marcop.ActuTimes.dao.utilisateurDaoBeanLocal;
import fr.marcorp.ActuTimes.entities.utilisateurs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class utilisateurServiceBean extends BaseServiceBean<utilisateurs, Long> implements utilisateurServiceBeanLocal {
 
    @EJB
    private utilisateurDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<utilisateurs, Long> getDao(){
        return this.dao;
    }
    
}
