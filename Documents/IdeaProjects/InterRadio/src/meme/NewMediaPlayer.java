package meme;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibC;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.version.LibVlcVersion;

/**
 * Created by Alex on 12/3/2016.
 */
public class NewMediaPlayer {
    protected HeadlessMediaPlayer ourMediaPlayer;

    public NewMediaPlayer() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir") + "/lib/win/");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir") + "/lib/mac/lib/");
        if (System.getProperty("os.name").contains("Mac")) {
            try {
                LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", System.getProperty("user.dir") + "/lib/mac/plugins", 1);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        //setupLibVLC();

        MediaPlayerFactory factory = new MediaPlayerFactory();
        ourMediaPlayer = factory.newHeadlessMediaPlayer();
        ourMediaPlayer.setPlaySubItems(true);
    }

    private void setupLibVLC() {

        new NativeDiscovery().discover();

        // discovery()'s method return value is WRONG on Linux
        LibVlcVersion.getVersion();
    }

    public void start(String mediaPath)  {
        ourMediaPlayer.playMedia(mediaPath);
    }

    public void stream(String mediaPath) {
        ourMediaPlayer.playMedia(mediaPath, ":file{dst=E:/Documents/Music/stream.mp3}");
    }

    public void play() {
        ourMediaPlayer.play();
    }

    public void pause() {
        ourMediaPlayer.pause();
    }

    public void setVolume(int vol) {
        ourMediaPlayer.setVolume(vol);
    }

    public HeadlessMediaPlayer getOurMediaPlayer() {
        return ourMediaPlayer;
    }

}
