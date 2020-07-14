package fr.marcorp.ActuTimes.entities;

import fr.marcorp.ActuTimes.entities.LikePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-21T11:31:59")
@StaticMetamodel(likes.class)
public class likes_ extends BaseEntity_ {

    public static volatile SingularAttribute<likes, LikePK> idLike;
    public static volatile SingularAttribute<likes, Date> dateLike;

}