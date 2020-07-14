package fr.marcorp.ActuTimes.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-21T11:31:59")
@StaticMetamodel(utilisateurs.class)
public class utilisateurs_ extends BaseEntity_ {

    public static volatile SingularAttribute<utilisateurs, String> motDePasse;
    public static volatile SingularAttribute<utilisateurs, Long> idUtilisateurs;
    public static volatile SingularAttribute<utilisateurs, String> privilege;
    public static volatile SingularAttribute<utilisateurs, String> photProfil;
    public static volatile SingularAttribute<utilisateurs, String> pseudo;
    public static volatile SingularAttribute<utilisateurs, String> email;

}