package webapp.waveform;

import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.components.scalars.ComponentFactoryScalarAbstract;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import dom.waveform.Waveform;

/**
 *
 */
public class WaveformComponentFactory extends ComponentFactoryScalarAbstract {

    public WaveformComponentFactory() {
        super(WaveformPanel.class, Waveform.class);
    }

    @Override
    public ApplicationAdvice appliesTo(IModel<?> model) {
        ApplicationAdvice applies = super.appliesTo(model);
        if (ApplicationAdvice.APPLIES == applies) {
            ScalarModel scalarModel = (ScalarModel) model;
            if (scalarModel.getKind() == ScalarModel.Kind.PARAMETER) {
                return ApplicationAdvice.DOES_NOT_APPLY;
            }
        }

        return applies;
    }

    @Override
    protected Component createComponent(String id, ScalarModel scalarModel) {
        return new WaveformPanel(id, scalarModel);
    }
}
