package tekup.module;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Plat")
@Getter
@Setter
@NoArgsConstructor
public class Plat extends Met{

	@Override
	public String toString() {
		return super.toString();
	}
}
