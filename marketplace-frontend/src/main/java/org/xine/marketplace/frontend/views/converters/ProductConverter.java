package org.xine.marketplace.frontend.views.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.xine.marketplace.frontend.views.util.cdi.CDIServiceLocator;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.daos.ProductsRepository;


/**
 * The Class CategoryConverter.
 */
@FacesConverter(forClass=Product.class)
public class ProductConverter implements Converter{
	
	private final ProductsRepository repository;
	
	public ProductConverter(){
		//using this because the CDI don't works in FacesConverter
		this.repository = CDIServiceLocator.getBean(ProductsRepository.class);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Product result = null;

		if (value != null) {
			Long id = new Long(value);
			System.out.println("-----------------------------------------------------------------");
			System.out.println("ProductConverter getAsObject");
			System.out.println("-----------------------------------------------------------------");
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
				Product p = (Product) value;
				//use the id 
				return p.getId() == null ? null : p.getId().toString();
		}
		return "";
	}
}
