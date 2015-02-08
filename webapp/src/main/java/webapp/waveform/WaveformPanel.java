package webapp.waveform;

import dom.waveform.Waveform;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;

/**
 *
 */
public class WaveformPanel extends PanelAbstract<EntityModel> {

    public WaveformPanel(String id, EntityModel model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        EntityModel model = getModel();
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
