package Window;

/**
 * Created by alexr on 12/13/2016.
 */
//A class containing all of the properties for the StationPanel, allowing each station to be diplayed with correct states inside the CountryForm
public class StationPanelProperties {
    protected String[] station;
    protected PlayButton playButton = new PlayButton();
    protected StarButton starButton = new StarButton();
    protected InfoButton infoButton = new InfoButton();
    protected boolean bigButtons = false;
    protected boolean empty = false;

    public StationPanelProperties(String[] s) {
        station = s;
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public StarButton getStarButton() {
        return starButton;
    }

    public InfoButton getInfoButton() {
        return infoButton;
    }

    public String getStationName() {
        return station[1];
    }

    public String[] getStation() {
        return station;
    }

    public boolean isBigButtons() {
        return bigButtons;
    }

    public boolean isFavorited() {
        return starButton.favorited;
    }

    public void setFavorited(boolean f) {
        starButton.favorited = f;
    }

    public void setBigButtons(boolean b) {
        bigButtons = b;
        playButton.setBig(b);
        starButton.setBig(b);
        infoButton.setBig(b);
    }
}
