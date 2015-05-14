package edu.tce.cse.obe.service;

import com.sun.jersey.api.json.JSONJAXBContext;

import edu.tce.cse.obe.model.Assessment;
import edu.tce.cse.obe.model.Course;
import edu.tce.cse.obe.model.CourseOutcome;
import edu.tce.cse.obe.model.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

@Provider
public class MyJAXBContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {Question.class,Assessment.class,CourseOutcome.class,Course.class};

    public MyJAXBContextResolver() throws Exception {
        Map props = new HashMap<String, Object>();
        props.put(JSONJAXBContext.JSON_NOTATION, JSONJAXBContext.JSONNotation.MAPPED);
        props.put(JSONJAXBContext.JSON_ROOT_UNWRAPPING, Boolean.TRUE);
        
    //    props.put(JSONJAXBContext.JSON_ARRAYS, new HashSet<String>(1){{ add("coList");}});
        props.put(JSONJAXBContext.JSON_ARRAYS, new HashSet<String>(1) {{ add("question");add("coList");add("assessment"); add("CourseOutcome"); add("course");  }} );
        //props.put(JSONJAXBContext.JSON_NON_STRINGS, new HashSet<String>(1){{add("pages"); add("tonerRemaining");}});
        this.context = new JSONJAXBContext(types, props);
    }

    public JAXBContext getContext(Class<?> objectType) {
        return (types[0].equals(objectType)) ? context : null;
    }
}
