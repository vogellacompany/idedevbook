
package com.vogella.resources;

import javax.annotation.PostConstruct;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class ResourceChangeAddon {

	@PostConstruct
	public void listenToResources() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(event -> {
			IResource res = event.getResource();
			switch (event.getType()) {
			case IResourceChangeEvent.PRE_CLOSE:
				System.out.print("Project ");
				System.out.print(res.getFullPath());
				System.out.println(" is about to close.");
				break;
			case IResourceChangeEvent.PRE_DELETE:
				System.out.print("Project ");
				System.out.print(res.getFullPath());
				System.out.println(" is about to be deleted.");
				break;
			case IResourceChangeEvent.POST_CHANGE:
				System.out.println("Resources have changed.");
				try {
					event.getDelta().accept(new DeltaPrinter());
				} catch (CoreException e) {
					e.printStackTrace();
				}
				break;
			case IResourceChangeEvent.PRE_BUILD:
				System.out.println("Build about to run.");
				try {
					event.getDelta().accept(new DeltaPrinter());
				} catch (CoreException e) {
					e.printStackTrace();
				}
				break;
			case IResourceChangeEvent.POST_BUILD:
				System.out.println("Build complete.");
				try {
					event.getDelta().accept(new DeltaPrinter());
				} catch (CoreException e) {
					e.printStackTrace();
				}
				break;
			}
		});
	}

}
