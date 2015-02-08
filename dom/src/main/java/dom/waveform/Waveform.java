package dom.waveform;

import java.io.Serializable;

/**
 *
 */
public class Waveform implements Serializable {

    private int[] data;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
