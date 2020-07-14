/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.marcorp.ActuTimes.ManagedBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class calcul implements Serializable {
    private float prix, result, reduc, taxe;
    private int quantite;
    private String etat="tx";
    
    public float calcul(){
        this.result= this.prix * this.quantite;
        if(this.result>1000){
            this.reduc = this.result*3/100;
            this.result = this.result - this.reduc;
        }
        else if(this.result>5000){
            this.reduc = this.result*5/100;
            this.result = this.result - this.reduc;
        }
        else if(this.result>7000){
            this.reduc = this.result*7/100;
            this.result = this.result - this.reduc;
        }
        else if(this.result>10000){
            this.reduc = this.result*10/100;
            this.result = this.result - this.reduc;
        }
        else if(this.result>50000){
            this.reduc = this.result*15/100;
            this.result = this.result - this.reduc;
        }
        
        if("tx".equals(this.etat)){
        this.taxe= this.result * (new Float(6.25)/100);
        this.result = this.result+taxe;
            System.out.println("ok");
        }
        if("ut".equals(this.etat)){
        this.taxe= this.result * (new Float(6.85)/100);
        this.result = this.result+taxe;
        }
        if("nt".equals(this.etat)){
        this.taxe= this.result * (new Float(8.0)/100);
        this.result = this.result+taxe;
        }
        if("al".equals(this.etat)){
        this.taxe= this.result * (new Float(4.0)/100);
        this.result = this.result+taxe;
        }
        if("ca".equals(this.etat)){
        this.taxe= this.result * (new Float(8.25)/100);
        this.result = this.result+taxe;
        }
        return this.result;
    }

    public float getReduc() {
        return reduc;
    }

    public void setReduc(float reduc) {
        this.reduc = reduc;
    }

    public float getTaxe() {
        return taxe;
    }

    public void setTaxe(float taxe) {
        this.taxe = taxe;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    
    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
}
