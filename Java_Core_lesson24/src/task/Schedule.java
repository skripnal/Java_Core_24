package task;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
	private Set<Seance> seances = new TreeSet<>();

    public void addSeance(Seance seance) {
        seances.add(seance);
    }

    public void removeSeance(Seance seance) {
        seances.remove(seance);
    }

    @Override
    public String toString() {
        StringBuilder scheduleStr = new StringBuilder();
        for (Seance seance : seances) {
            scheduleStr.append(seance).append("\n");
        }
        return scheduleStr.toString();
    }

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seances);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(seances, other.seances);
	}
    
    
}
