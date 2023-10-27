package task;

import java.util.Objects;

public class Seance implements Comparable<Seance>{
	private Movie movie;
    private Time startTime;
    private Time endTime;

    public Seance(Movie movie, Time startTime) {
    	this.movie = movie;
        this.startTime = startTime;
        int totalMinutes = startTime.getHour() * 60 + startTime.getMin() + movie.getDuration().getHour() * 60 + movie.getDuration().getMin();
        this.endTime = new Time(totalMinutes / 60, totalMinutes % 60);
    }

    @Override
    public String toString() {
        return movie + " at " + startTime + " - " + endTime;
    }

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Seance o) {
		return this.startTime.compareTo(o.startTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, movie, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seance other = (Seance) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(movie, other.movie)
				&& Objects.equals(startTime, other.startTime);
	}
	
    
}
