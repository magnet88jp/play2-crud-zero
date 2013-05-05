package models;
 
import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.Id;
 
import play.db.ebean.Model;
 
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.avaje.ebean.validation.NotNull;
 
@Entity
public class Sentence extends Model {
 
  @Id
  public Long id;

  @NotNull
  public String name;

  @CreatedTimestamp
  public Date createDate;

  @UpdatedTimestamp
  public Date updateDate;

  public String toString() {
    return "Sentence [id=" + id + ", name=" + name + ", createDate="
      + createDate + ", updateDate=" + updateDate + "]";
  }

  public static Finder<Long,Sentence> find = new Finder<Long,Sentence>(Long.class, Sentence.class); 

}