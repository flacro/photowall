package org.flacro.resources;

import org.flacro.MyApplication;
import org.restlet.ext.freemarker.FreemarkerConverter;
import org.restlet.resource.ServerResource;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

/**
 * Common behaviour of server resources.
 */
public class BaseResource extends ServerResource {

    @Override
    public MyApplication getApplication() {
        return (MyApplication) super.getApplication();
    }

    /**
     * Return a templated representation.
     * 
     * @param map
     *            The map of parameters.
     * @param templateName
     *            The name of the template.
     * @param mediaType
     *            The media type of the representation.
     * @return A templated representation.
     */
    protected Representation toRepresentation(Map<String, Object> map,
            String templateName, MediaType mediaType) {
        return new TemplateRepresentation(templateName,getApplication().getConfiguration(), map, mediaType);
    }
}
