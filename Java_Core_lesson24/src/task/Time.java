package task;

import java.util.Objects;

public class Time implements Comparable<Time>{
	private int hour;
    private int min;

    public Time(int hour, int min) throws IllegalArgumentException{
        if (hour >= 0 && hour < 24 && min >= 0 && min < 60) {
            this.hour = hour;
            this.min = min;
        } else {
            throw new IllegalArgumentException("Invalid time values.");
        }
    }
    
    @Override
    public int compareTo(Time other) {
        if (this.hour == other.hour) {
            return Integer.compare(this.min, other.min);
        }
        return Integer.compare(this.hour, other.hour);
    }
    
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, min);
    }

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hour, min);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return hour == other.hour && min == other.min;
	}
    
    
}
