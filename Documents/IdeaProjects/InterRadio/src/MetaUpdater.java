import com.abercap.mediainfo.api.MediaInfo;
import com.sun.jna.NativeLibrary;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by alexr on 11/29/2016.
 */
public class MetaUpdater {
    protected String artist = "";
    protected String lastArtist = "";
    protected String title = "";
    protected String lastTitle = "";
    protected String sURL = "";
    protected URL url = new URL("http://cbc_r1_tor.akacast.akamaistream.net/7/632/451661/v1/rc.akacast.akamaistream.net/cbc_r1_tor");

    public MetaUpdater() throws MalformedURLException, IOException {
        NativeLibrary.addSearchPath("mediainfo", "C:/Program Files/MediaInfo/");
        NativeLibrary.addSearchPath("mediainfo", "/Users/jaimekvaternik/Downloads/MediaInfoLib");
        Thread metaLoop = new Thread() {
            public void run() {
                while (true) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is = null;
                byte[] byteChunk = new byte[0];
                int count = 0;
                try {
                    is = url.openStream();
                    byteChunk = new byte[4096];
                    int n;
                    int run = 0;

                    while ((n = is.read(byteChunk)) > 0 && count < 2) {
                        baos.write(byteChunk, 0, n);
                        count++;
                    }
                } catch (IOException e) {
                    System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
                    e.printStackTrace();
                    // Perform any other exception handling that's appropriate.
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                FileOutputStream fos;
                File a = new File(System.getProperty("user.dir") + "/1.txt");
                try {
                    fos = new FileOutputStream(a);
                    fos.write(baos.toByteArray());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MediaInfo info = new MediaInfo();
                info.open(a);
                String myString = info.inform();

                Scanner scanner = new Scanner(myString);
                while (scanner.hasNextLine())

                {
                    String line = scanner.nextLine();
                    if (line.contains("artist") || line.contains("Artist")) {
                        int s = 0;
                        int e = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ':') {
                                s = i + 2;
                            }
                            if (i == line.length() - 1) {
                                e = i + 1;
                            }
                        }
                        artist = line.substring(s, e);
                    }
                    if (line.contains("title") || line.contains("Title")) {
                        int s = 0;
                        int e = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ':') {
                                s = i + 2;
                            }
                            if (i == line.length() - 1) {
                                e = i + 1;
                            }
                        }
                        title = line.substring(s, e);
                    }
                }
                scanner.close();
                if (!lastArtist.equals(artist) || !lastTitle.equals(title))

                {
                    System.out.println(title + " by " + artist);
                }

                lastTitle = title;
                lastArtist = artist;
            }
        }
    };
    metaLoop.start();
}

    public void setURL(String URL) throws MalformedURLException {
        sURL = URL;
        url = new URL(sURL);
    }

    public static void main(String[] args) throws IOException {
        MetaUpdater meta = new MetaUpdater();
    }
}
