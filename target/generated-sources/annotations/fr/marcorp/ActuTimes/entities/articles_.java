package fr.marcorp.ActuTimes.entities;

import fr.marcorp.ActuTimes.entities.categories;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-21T11:31:59")
@StaticMetamodel(articles.class)
public class articles_ extends BaseEntity_ {

    public static volatile SingularAttribute<articles, String> titreArticle;
    public static volatile SingularAttribute<articles, Date> datePublication;
    public static volatile SingularAttribute<articles, String> priorite;
    public static volatile SingularAttribute<articles, String> imageArticles;
    public static volatile SingularAttribute<articles, categories> idCategorie;
    public static volatile SingularAttribute<articles, String> auteurArticle;
    public static volatile SingularAttribute<articles, String> contenuArticle;
    public static volatile SingularAttribute<articles, Long> idArticles;

}