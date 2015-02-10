package webapp.waveform;

import java.util.Locale;
import java.util.Map;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.collect.Maps;

import dom.waveform.Waveform;
import dom.waveform.WaveformObject;

/**
 *
 */
public class WaveformPanel extends FormComponentPanel<ObjectAdapter> {

    private final WaveformConverter converter;

    public WaveformPanel(String id, ScalarModel model) {
        super(id, model);

        this.converter = new WaveformConverter();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        WebMarkupContainer waveformContainer = new WebMarkupContainer("waveformContainer");
        ChartTemplatePanel chartTemplatePanel = new ChartTemplatePanel("chartTemplate");
        addOrReplace(waveformContainer, chartTemplatePanel);

//        ObjectAdapter objectAdapter = getModelObject();
//        Waveform waveform = (Waveform) objectAdapter.getObject();
//        String string = converter.convertToString(waveform, Locale.ENGLISH);
    }

    @Override
    public void updateModel() {
//        String input = waveformField.getModelObject();
//        Waveform waveform = converter.convertToObject(input, Locale.ENGLISH);
//        ObjectAdapter objectAdapter = getModelObject();
//        Waveform waveformObject = (Waveform) objectAdapter.getObject();
//        waveformObject.setWave(waveform);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(WaveformPanel.class, "res/chart-rx.css")));
        response.render(CssHeaderItem.forReference(new CssResourceReference(WaveformPanel.class, "res/jquery.bootstrap-touchspin.css")));
        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(WaveformPanel.class, "res/ractive.js")));
        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(WaveformPanel.class, "res/jquery.bootstrap-touchspin.js")));

        final PackageTextTemplate shoppingRx = new PackageTextTemplate(WaveformPanel.class, "res/chart-rx.js");

        final Map<String, Object> variables = Maps.newHashMap();
//        variables.put("kitPrice", kitPrice / 100);
//        variables.put("discount", discount);
//        final GsonBuilder gsonBuilder = new GsonBuilder();
//        if (getApplication().usesDevelopmentConfig()) {
//            gsonBuilder.setPrettyPrinting();
//        }
//
//        final Gson gson = gsonBuilder.create();
//        final String orderJson = gson.toJson(orderRepr);
//        variables.put("order", orderJson);
        final String js = shoppingRx.asString(variables);
        response.render(JavaScriptHeaderItem.forScript(js, "chart-rx"));
    }
}
