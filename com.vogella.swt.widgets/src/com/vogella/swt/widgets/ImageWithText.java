package com.vogella.swt.widgets;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ImageWithText extends Canvas {

	public ImageWithText(Composite parent, int style) {
		super(parent, style);
		addListener();
	}

	private void addListener() {
		addPaintListener(e -> {
			GC gc = e.gc;

			Bundle bundle = FrameworkUtil.getBundle(getClass());
			URL imageUrl = FileLocator.find(bundle, new Path("image/vogella.jpg"), null);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(imageUrl);
			Image image = imageDescriptor.createImage();

			gc.drawImage(image, 0, 0);

			gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));

			gc.drawText("Advanced Trainings", 150, 10);

			// free resource again
			image.dispose();
		});
	}

	@Override
	public Point computeSize(int wHint, int hHint) {
		checkWidget();
		// return image dimensions
		return new Point(300, 99);
	}
}
