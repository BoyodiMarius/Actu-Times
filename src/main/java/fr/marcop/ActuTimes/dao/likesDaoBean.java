/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcop.ActuTimes.dao;

import fr.marcorp.ActuTimes.entities.likes;
import javax.ejb.Stateless;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class likesDaoBean extends BaseDaoBean<likes, Long> implements likesDaoBeanLocal {
  
    public likesDaoBean(){
        super(likes.class);
    }
    
}
