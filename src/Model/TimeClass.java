package Model;

public class TimeClass {

    private int seconds;
    private int minutes;
    private int hours;

    public TimeClass(int totalSeconds) {
        if (totalSeconds >= 0) {
            this.hours = totalSeconds / 3600;
            this.minutes = (totalSeconds % 3600) / 60;
            this.seconds = totalSeconds - (this.hours * 3600) - (this.minutes * 60);
        } else {
            this.hours = 0;
            this.minutes = 0;
            this.seconds = 0;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

}
