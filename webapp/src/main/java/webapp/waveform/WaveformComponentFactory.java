package webapp.waveform;

import dom.waveform.Waveform;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.components.scalars.ComponentFactoryScalarAbstract;

/**
 *
 */
public class WaveformComponentFactory extends ComponentFactoryScalarAbstract {

    public WaveformComponentFactory() {
        super(WaveformPanel.class, Waveform.class);
    }

    @Override
    protected Component createComponent(String id, ScalarModel scalarModel) {
        return new WaveformPanel(id, scalarModel);
    }
}
