/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcop.ActuTimes.dao;

import fr.marcorp.ActuTimes.entities.utilisateurs;
import javax.ejb.Local;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Local
public interface utilisateurDaoBeanLocal extends BaseDaoBeanLocal<utilisateurs, Long> {
    
}
