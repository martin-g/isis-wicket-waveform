package webapp.waveform;

import dom.waveform.Waveform;

import java.util.Locale;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;

/**
 *
 */
public class WaveformConverter implements IConverter<Waveform> {

    @Override
    public Waveform convertToObject(String value, Locale locale) throws ConversionException {
        Waveform waveform = new Waveform();
        String[] integers = Strings.split(value, ',');
        int[] data = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            data[i] = Integer.parseInt(integers[i], 10);
        }
        waveform.setData(data);
        return waveform;
    }

    @Override
    public String convertToString(Waveform value, Locale locale) {
        int[] data = value.getData();
        StringBuilder asString = new StringBuilder(data.length);
        for (int integer : data) {
            asString.append(integer);
        }
        return asString.toString();
    }
}
