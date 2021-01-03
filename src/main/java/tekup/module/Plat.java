package tekup.module;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue ( "plat")
public class Plat extends Met {

}
