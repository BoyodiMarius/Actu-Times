/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.services;

import fr.marcorp.ActuTimes.entities.articles;
import javax.ejb.Local;


/**
 *
 * @author BOYODI Wiyow Marius
 */
@Local
public interface articlesServiceBeanLocal extends BaseServiceBeanLocal<articles, Long> {
    
}
