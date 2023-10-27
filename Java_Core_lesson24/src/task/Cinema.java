package task;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Cinema {
	
	private TreeMap<Days, Schedule> schedules = new TreeMap<>();
    private ArrayList<Movie> moviesLibrary = new ArrayList<>();
    private Time open;
    private Time close;
	
    public Cinema(Time open, Time close) {
        this.open = open;
        this.close = close;
        for (Days day : Days.values()) {
            schedules.put(day, new Schedule());
        }
    }
    
    public void addMovie(Movie movie) {
        moviesLibrary.add(movie);
    }
    
    public void showMoviesLibrary() {
    	moviesLibrary.stream().forEach(System.out::println);
    }
    
    public void addSeance(Seance seance, String day) {
        Days selectedDay = Days.valueOf(day);
        Schedule schedule = schedules.get(selectedDay);
        if (isWithinOperatingHours(seance.getStartTime(), seance.getEndTime())) {
            schedule.addSeance(seance);
            System.out.println("Сеанс додано!");
        } else {
            System.out.println("Неможливо додати сенс в не робочі години!");
        }
    }
    
    public void removeMovie(Movie movie) {
        moviesLibrary.remove(movie);
        schedules.values().forEach(schedule -> {
            Set<Seance> seancesToRemove = schedule.getSeances().stream()
                    .filter(seance -> seance.getMovie().equals(movie))
                    .collect(Collectors.toSet());
            schedule.getSeances().removeAll(seancesToRemove);
        });
    }
	
    public void removeSeance(Seance seance, String day) {
        Days selectedDay = Days.valueOf(day);
        Schedule schedule = schedules.get(selectedDay);
        schedule.removeSeance(seance);
    }
    
    public boolean isWithinOperatingHours(Time startTime, Time endTime) {
        return startTime.compareTo(open) >= 0 && endTime.compareTo(close) <= 0;
    }
    
    public Optional<Seance> findSeanceByMovieTitleAndDay(Schedule schedule, String movieTitle) {
        return schedule.getSeances().stream()
                .filter(seance -> seance.getMovie().getTitle().equals(movieTitle))
                .findFirst();
    }

	public TreeMap<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(TreeMap<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpen() {
		return open;
	}

	public void setOpen(Time open) {
		this.open = open;
	}

	public Time getClose() {
		return close;
	}

	public void setClose(Time close) {
		this.close = close;
	}
    
    
}
