package webapp.waveform;

import dom.waveform.Waveform;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;

/**
 *
 */
public class WaveformComponentFactory extends ComponentFactoryAbstract {

    public WaveformComponentFactory() {
        super(ComponentType.VALUE, "waveform", WaveformPanel.class);
    }

    @Override
    protected ApplicationAdvice appliesTo(IModel<?> model) {
        if (!(model instanceof ScalarModel)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        ScalarModel scalarModel = (ScalarModel) model;
        ObjectAdapter objectAdapter = scalarModel.getObject();
        return Waveform.class == objectAdapter.getSpecification().getCorrespondingClass() ? ApplicationAdvice.APPLIES : ApplicationAdvice.DOES_NOT_APPLY;
    }

    @Override
    public Component createComponent(String id, IModel<?> model) {
        return new WaveformPanel(id, (EntityModel) model);
    }
}
