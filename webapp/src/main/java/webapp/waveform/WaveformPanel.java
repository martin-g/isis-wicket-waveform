package webapp.waveform;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

import dom.waveform.Waveform;

/**
 *
 */
public class WaveformPanel extends PanelAbstract<ScalarModel> {

    public WaveformPanel(String id, ScalarModel model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ScalarModel model = getModel();
        ObjectAdapter objectAdapter = model.getObject();
        Waveform waveform = (Waveform) objectAdapter.getObject();
        TextField<Waveform> editor = new TextField<Waveform>("editor", Model.of(waveform)) {
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                return (type.isAssignableFrom(Waveform.class)) ? (IConverter<C>) new WaveformConverter() : super.getConverter(type);
            }
        };
        add(editor);
    }
}
