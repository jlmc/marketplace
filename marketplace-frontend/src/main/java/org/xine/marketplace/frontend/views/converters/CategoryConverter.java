package org.xine.marketplace.frontend.views.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.xine.marketplace.frontend.views.util.cdi.CDIServiceLocator;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.repository.daos.CategorysRepository;


/**
 * The Class CategoryConverter.
 */
@FacesConverter(forClass=Category.class)
public class CategoryConverter implements Converter{
	
	private final CategorysRepository repository;
	
	public CategoryConverter(){
		//using this because the CDI don't works in FacesConverter
		this.repository = CDIServiceLocator.getBean(CategorysRepository.class);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Category result = null;

		if (value != null) {
			Long id = new Long(value);
			result = this.repository.getById(id);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			//use the id 
			return ((Category) value).getId().toString();
		}

		return "";
	}
}
