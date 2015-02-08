package webapp.waveform;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.Model;

import dom.waveform.Waveform;
import dom.waveform.WaveformObject;

import java.util.Locale;

/**
 *
 */
public class WaveformPanel extends FormComponentPanel<ObjectAdapter> {

    private final HiddenField<String> waveformField;
    private final WaveformConverter converter;

    public WaveformPanel(String id, ScalarModel model) {
        super(id, model);

        this.converter = new WaveformConverter();

        ObjectAdapter objectAdapter = model.getObject();
        WaveformObject waveformObject = (WaveformObject) objectAdapter.getObject();
        String string = converter.convertToString(waveformObject.getWave(), Locale.ENGLISH);
        this.waveformField = new HiddenField<>("waveform", Model.of(string));
    }

    @Override
    public void updateModel() {
        String input = waveformField.getModelObject();
        Waveform waveform = converter.convertToObject(input, Locale.ENGLISH);
        ObjectAdapter objectAdapter = getModelObject();
        WaveformObject waveformObject = (WaveformObject) objectAdapter.getObject();
        waveformObject.setWave(waveform);
    }
}
