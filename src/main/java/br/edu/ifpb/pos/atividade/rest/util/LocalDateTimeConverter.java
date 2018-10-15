
package br.edu.ifpb.pos.atividade.rest.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Edilva
 */

@FacesConverter("localDateConverter")
public class LocalDateTimeConverter implements Converter {
    
    private final String pattern = "dd/MM/yyyy";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()) return null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate data = LocalDate.parse(value, dtf);
        return data;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) return "";
        LocalDate data = (LocalDate) value;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return data.format(dtf);
    }
    
}
