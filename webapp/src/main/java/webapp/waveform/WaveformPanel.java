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

    private final HiddenField<String> waveformField;
    private final WaveformConverter converter;

    public WaveformPanel(String id, ScalarModel model) {
        super(id, model);

        WebMarkupContainer waveformContainer = new WebMarkupContainer("waveformContainer");
        add(waveformContainer);

        ChartTemplatePanel chartTemplatePanel = new ChartTemplatePanel("chartTemplate");
        add(chartTemplatePanel);

        this.converter = new WaveformConverter();

        ObjectAdapter objectAdapter = model.getObject();
        Waveform waveform = (Waveform) objectAdapter.getObject();
        String string = converter.convertToString(waveform, Locale.ENGLISH);
        waveformField = new HiddenField<>("waveform", Model.of(string));
        add(waveformField);
    }

    @Override
    public void updateModel() {
        String input = waveformField.getModelObject();
        Waveform waveform = converter.convertToObject(input, Locale.ENGLISH);
        ObjectAdapter objectAdapter = getModelObject();
        WaveformObject waveformObject = (WaveformObject) objectAdapter.getObject();
        waveformObject.setWave(waveform);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(WaveformPanel.class, "res/chart-rx.css")));
        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(WaveformPanel.class, "res/ractive.js")));

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
