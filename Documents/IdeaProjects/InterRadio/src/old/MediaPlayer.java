package old;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.internal.libvlc_state_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.player.condition.Condition;

/**
 * Created by Alex on 11/27/2016.
 */
public class MediaPlayer {
    private EmbeddedMediaPlayerComponent ourMediaPlayer;

    public MediaPlayer() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        NativeLibrary.addSearchPath("mediainfo", "/Users/jaimekvaternik/Downloads/MediaInfoLib");

        ourMediaPlayer = new EmbeddedMediaPlayerComponent();
    }

    public void start(String mediaPath) {
        ourMediaPlayer.getMediaPlayer().playMedia(mediaPath);
    }

    public void stream(String mediaPath) {
        ourMediaPlayer.getMediaPlayer().playMedia(mediaPath, ":file{dst=E:/Documents/Music/stream.mp3}");
    }

    public void play() {
        ourMediaPlayer.getMediaPlayer().play();
    }

    public void pause() {
        ourMediaPlayer.getMediaPlayer().pause();
    }

    public void setVolume(int vol) {
        ourMediaPlayer.getMediaPlayer().setVolume(vol);
    }

    public EmbeddedMediaPlayerComponent getOurMediaPlayer() {
        return ourMediaPlayer;
    }

}
