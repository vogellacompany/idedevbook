package com.example.e4.rcp.todo.services.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

import com.example.e4.rcp.todo.model.ITodoService;

@Component(service = IContextFunction.class, property = "service.context.key=com.example.e4.rcp.todo.model.ITodoService")
public class TodoServiceContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		ITodoService impl = ContextInjectionFactory.make(MyTodoServiceImpl.class, context);
		MApplication application = context.get(MApplication.class);
		application.getContext().set(ITodoService.class, impl);

		// in case the ITodoService is also needed in the OSGi layer, e.g.
		// by other OSGi services, register the instance also in the OSGi service layer
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		BundleContext bundleContext = bundle.getBundleContext();
		bundleContext.registerService(ITodoService.class, impl, null);
			
		return impl;
	}
}
