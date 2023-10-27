package task;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean isWork = true;
		int key;
		Cinema cinema = new Cinema(new Time(8, 0), new Time(22, 0));
		
		System.out.println("1 - Додати фільм до фільмотеки");
		System.out.println("2 - Вивести фільмотеку");
		System.out.println("3 - Додати сеанс");
		System.out.println("4 - Видалити фільм");
		System.out.println("5 - Видалити сеанс");
		System.out.println("6 - Показати розклад на день");
		System.out.println("0 - Завершити виконання програми");
		
		while(isWork) {
			System.out.print("Введіть команду: ");
			
			try {
				key = scanner.nextInt();
				scanner.nextLine();
				switch (key) {
				case 1:
					System.out.print("Введіть назву фільму: ");
					String title = scanner.nextLine();
					System.out.println("Введіть тривалість фільму: ");
					System.out.print("Години: ");
					int hour = scanner.nextInt();
					System.out.print("Хвилини: ");
					int min = scanner.nextInt();
					Time duration = new Time(hour, min);
					cinema.addMovie(new Movie(title, duration));
					System.out.println("Фільм додано!");
					break;
				case 2:
					System.out.println("Бібліотека фільмів:");
					cinema.showMoviesLibrary();
					break;
				case 3:
					System.out.print("Введіть назву фільму для сеансу: ");
                    String movieTitle = scanner.nextLine();
                    Movie selectedMovie = null;
                    for (Movie movie : cinema.getMoviesLibrary()) {
                        if (movie.getTitle().equals(movieTitle)) {
                            selectedMovie = movie;
                            break;
                        }
                    }
                    if (selectedMovie == null) {
                        System.out.println("Фільм не знайдено.");
                        break;
                    }
                    System.out.print("Введіть день для сеансу (MONDAY, TUESDAY, і т.д.): ");
                    String day = scanner.next().toUpperCase();
                    System.out.print("Введіть години початку сеансу: ");
                    int startHour = scanner.nextInt();
                    System.out.print("Введіть хвилини початку сеансу: ");
                    int startMinute = scanner.nextInt();
                    Seance newSeance = new Seance(selectedMovie, new Time(startHour, startMinute));
                    cinema.addSeance(newSeance, day);
					break;
				case 4:
					System.out.print("Введіть назву фільму для видалення: ");
					String movieToRemove = scanner.nextLine();
					Optional<Movie> movieToDelete = cinema.getMoviesLibrary().stream()
					        .filter(movie -> movie.getTitle().equals(movieToRemove))
					        .findFirst();

					if (movieToDelete.isPresent()) {
					    cinema.removeMovie(movieToDelete.get());
					} else {
					    System.out.println("Фільм не знайдено.");
					}
					break;
				case 5:
					System.out.print("Введіть назву фільму для видалення сеансу: ");
				    String movieTitleToRemove = scanner.nextLine();
				    System.out.print("Введіть день для видалення сеансу (MONDAY, TUESDAY, і т.д.): ");
				    String dayToRemove = scanner.next().toUpperCase();

				    Days selectedDayToRemove = Days.valueOf(dayToRemove);
				    Schedule scheduleToRemove = cinema.getSchedules().get(selectedDayToRemove);
				    
				    Optional<Seance> seanceOptional = cinema.findSeanceByMovieTitleAndDay(scheduleToRemove, movieTitleToRemove);

				    if (seanceOptional.isPresent()) {
				        cinema.removeSeance(seanceOptional.get(), dayToRemove);
				        System.out.println("Сеанс видалено!");
				    } else {
				        System.out.println("Сеанс не знайдено.");
				    }
					break;
				case 6:
					System.out.print("Введіть день для перегляду розкладу (MONDAY, TUESDAY, і т.д.): ");
                    String selectedDay = scanner.next().toUpperCase();
                    Schedule schedule = cinema.getSchedules().get(Days.valueOf(selectedDay));
                    System.out.println("Розклад на " + selectedDay + ":");
                    System.out.println(schedule);
					break;
				case 0:
					isWork = false;
					break;
				default:
					System.out.println("Під цим номером команди не існує!");
					break;	
				}
			} catch (InputMismatchException | IllegalArgumentException e) {
				scanner.nextLine();
				e.printStackTrace();
				continue;
			}
			
			
		}
		
	}

}
